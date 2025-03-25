@echo off
echo Setting Java 17 for Minecraft...
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.14.7-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo Running Minecraft with Ducks Galore mod...
call gradlew runClient

echo Done!
pause 