# Define script parameters
Param(
    [string]$Username,
    [string]$Repository,
    [string]$Version,

    [switch]$ForceBuild,
    [switch]$TagAsLatest,
    [switch]$Push
)

# First, build the project if necessary or switch is specified
if ($ForceBuild -or !(Test-Path target) -or (Get-ChildItem target/*with-dependencies.jar).Length -eq 0) {
    mvn clean;
    mvn package;
    if ($LASTEXITCODE -ne 0) { Throw 'The project build has failed. Deployment cannot continue. See the above logs for details.'; }
}

# Get the binary and copy it to a known location
$binary = (Get-ChildItem target/*with-dependencies.jar)[0];
Copy-Item $binary target/release.jar

# Extract information from the binary path
$firstDash = $binary.Name.IndexOf('-');
$secondDash = $binary.Name.IndexOf('-', $firstDash + 1);

# Set parameters to default values if they are not given
if ($Repository -eq '') { $Repository = $binary.Name.Substring(0, $firstDash); }
if ($Version -eq '') { $Version = $binary.Name.Substring($firstDash + 1, $secondDash - $firstDash - 1); }

# Build a full name for the Docker image, including the username if specified
$imageName = $Repository + ':' + $Version;
if ($Username -ne '') { $imageName = $Username + '/' + $imageName; }

# Build the Docker image
docker build . -f script/Dockerfile -t $imageName
if ($LASTEXITCODE -ne 0) { Throw 'The image build has failed. Deployment cannot continue. See the above logs for details.'; }

# Push Docker image if switch is specified
if ($Push) {
    docker push $imageName;
}

# Tag the image as latest if switch is specified
if ($TagAsLatest) {
    
    # First, derive a latest image tag from the original name
    $latestImageName = $imageName.Substring(0, $imageName.IndexOf(':')) + ':latest';

    # Tag the image.
    docker tag $imageName $latestImageName;
    
    # Push Docker image if switch is specified
    if ($Push) {
        docker push $latestImageName;
    }
}
