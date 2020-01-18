@echo off
set dir=%~dp0
echo project directory: %dir%
echo the app is about to launch
java -classpath %dir%\tunnel-test-1.0.0.jar;%dir%\lib\* org.platform.tunnel.test.App
echo application started
pause