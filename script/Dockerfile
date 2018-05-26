# Build this image with the following command:
# docker build . -t goalkeeper:<version>
#
# If you intend to publish this image, instead use
# docker build . -t <username>/goalkeeper:<version> --squash
# docker push <username>/goalkeeper:<version> --squash

# Select the 
FROM openjdk:8u171-jre-slim-stretch

# Copy the build result to the home directory of root
COPY target/release.jar /usr/local/bin/goalkeeper.jar

# Add a script to automatically run Goalkeeper with correct paths
RUN echo '#!/bin/sh\njava -jar /usr/local/bin/goalkeeper.jar "$@"\n' > /usr/local/bin/gk && \
    chmod 700 /usr/local/bin/gk
