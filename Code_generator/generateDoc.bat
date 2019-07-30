echo off
@cd /D %~dp0

title Generate doxygen documentation

:: The first parameter is the Doxygen file name
set fileName=%1

:: Check input parameters
IF "%fileName%"=="" (
  ECHO No file name specified, using Doxyfile...
  set fileName="Doxyfile"
)

:: Generate HTML documentation
ECHO Using Doxygen...
doxygen %fileName%
IF errorlevel 1 (
  ECHO Problem building HTML documentation!
  exit 1
)
ECHO HTML documentation completed...

:: Generate PDF documentation
ECHO Generating pdf...
doc\latex\make.bat
IF errorlevel 1 (
  ECHO Problem building PDF documentation!
  exit 1
)
ECHO PDF documentation completed...
