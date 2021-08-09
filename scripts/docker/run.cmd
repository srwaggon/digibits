call scripts\docker\build.cmd

@echo off
echo Running...
docker run --rm -d -v %cd%/database:/database -p 8080:8080 swaggoner/digibits
