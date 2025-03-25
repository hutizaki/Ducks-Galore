@echo off
echo Setting Java 17 for building...
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.14.7-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo Building Ducks Galore mod...
call gradlew build

echo Done!
pause 