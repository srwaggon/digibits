@echo off

echo Cleaning...
call gradlew clean

docker ps -a -q --filter="ancestor=swaggoner/digibits"
docker image rm swaggoner/digibits