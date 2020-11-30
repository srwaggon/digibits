call scripts\clean.bat

@echo off
echo Building...
call gradlew build
docker build --build-arg JAR_FILE=build/libs/*.jar -t swaggoner/digibits .
