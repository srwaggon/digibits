call scripts\docker\clean.cmd

@echo off
echo Building...
wsl scripts/build.sh
docker build --build-arg JAR_FILE=build/libs/*.jar -t swaggoner/digibits .
