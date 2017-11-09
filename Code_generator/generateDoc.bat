echo off
@cd /D %~dp0

title Generate doxygen documentation

ECHO Using doxygen...
doxygen Doxyfile
IF errorlevel 1 (
  ECHO Error generating documentation!
  exit 1
)

ECHO Generating pdf...
doc\latex\make.bat
IF errorlevel 1 (
  ECHO Error generating pdf!
  exit 1
)

ECHO Documentation generated successfully!
