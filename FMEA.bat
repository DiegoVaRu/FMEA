@echo off
set "PATH_TO_FX=%~dp0lib\javafx-sdk-23.0.2\lib"
java --module-path "%PATH_TO_FX%" --add-modules javafx.controls,javafx.fxml --enable-preview -jar "%~dp0bin\fmea.jar"
pause
