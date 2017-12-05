TITLE="Generate doxygen documentation"

# The first parameter is the Doxygen file name
fileName="$1"

# Check input parameters
if [ -z $fileName ]
then
    echo "No file name specified, using Doxyfile..."
    fileName="Doxyfile"
fi

# Generate HTML documentation
echo "Using Doxygen..."
doxygen $fileName \
&& (echo "HTML documentation completed...") \
|| (echo "Problem building HTML documentation!"; exit 1)

# Generate PDF documentation
echo Generating pdf...
cd doc/latex
make \
&& (echo "PDF documentation completed...") \
|| (echo "Problem building PDF documentation!"; exit 1)
