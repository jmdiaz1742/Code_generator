echo off
@cd /D %~dp0

title Generate doxygen documentation

ECHO Using doxygen...
doxygen Doxyfile
IF errorlevel 1 (
  ECHO Problem vuilding HTML documentation!
  exit 1
)

ECHO Generating pdf...
doc\latex\make.bat
IF errorlevel 1 (
  ECHO Problem vuilding PDF documentation!
  exit 1
)

ECHO Documentation generated successfully!
