# Build this image with the following command:
# docker build . -t goalkeeper:<version>
#
# If you intend to publish this image, instead use
# docker build . -t <username>/goalkeeper:<version>
# docker push <username>/goalkeeper:<version>

# Select the 
FROM openjdk:8u171-jre-slim-stretch

# Copy the build result to the home directory of root
COPY target/goalkeeper-0.1.0-with-dependencies.jar /usr/local/bin/goalkeeper.jar

# Add a script to automatically run Goalkeeper with correct paths
RUN echo '#!/bin/sh\njava -jar /usr/local/bin/goalkeeper.jar "$@"\n' > /usr/local/bin/gk
RUN chmod 700 /usr/local/bin/gk
