
TITLE="Generate doxygen documentation"

# The first parameter is the Doxygen file name
fileName="$1"

# Check input parameters
if [ -z $fileName ]
then
    echo "No file name specified, using \"Doxyfile\"..."
    fileName="Doxyfile"
fi

# Generate HTML documentation
echo "Using Doxygen..."
doxygen Doxyfile \
&& (echo "HTML documentation completed...") \
|| (echo "Problem vuilding HTML documentation!"; exit 1)

# Generate PDF documentation
cd doc/latex
echo Generating pdf...
make \
&& (echo "PDF documentation completed...") \
|| (echo "Problem vuilding PDF documentation!"; exit 1)
