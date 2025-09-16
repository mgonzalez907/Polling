#Polling
Write a simple polling program that allows users to rate any number issues from 1 (low importance) to 10 (high importance). Read in the causes from a file (you can use the causes.txt file found in  the Lesson 2 Homework file on Canvas, but please prompt for the file name - Don’t hardcode the name.). Use a one-dimensional array of type String to store the five causes.  Prompt for the number to be polled.  The ratings are automatically generated using a Random
Write the following outputs to a file (prompt for the name).   You can see a sample of all the output in the pollresults.txt file. Include the following:
a.	The line by line survey numbers as shown during the Random number generation.
b.	A tabular report with the topics down the left side and the results of the ratings for the numPolled users on the line as shown in the text file.
c.	To the right of each row show the average of the results to two decimal places.
d.	Determine which poll issue received the highest total and write out that cause and its poll average.
e.	Determine which poll issue received the lowest total and write out that cause and its poll average.

Implementation Guidance.
Create a static Scanner called keyboard outside of any method
Create a static Random variable called randy outside of any method
Create a static PrintWriter variable called outputWriter You should implement the above program with the following methods:

method main
Read in a seed for randy and then create the object randy refers to with this seed
Create an ArrayList of type String array of causes called causesList.
Set the causesList reference to the ArrayList of type String when you calls readCauses with no parameters.
Requests number (numPolled) to be polled interactively.
Interactively request an output file name, create an output File and with that the PrintWriter called outputWriter.  This variable can then be used for all outputs
Call performSurvey with the causesList and numPolled as parameters. This method returns a reference to a two-dimensional int array with the pollResults. (Can call it pollResults in main)
Call calcCausesSums with pollResults as a parameter.  Return a reference to a one-dimensional int array with the sums by row.(Can call it causesSum in main
Call calcAverage with reference to causesSum and the value of numPolled as parameters, returning a reference to a one-dimensional double array. (Can call it averageForCause)
Call reportResults with causesList, pollResults, averageForCause, causesSum references as parameters, and no return value. 

method readCauses with no parameters
Sets up a Scanner and reads from a file the causes.  Use the causes.txt file in the Lesson 2 Homework file.
The file format is one cause per line.  
Create an ArrayList of type String called causes and read in the causes one at a time from causes.txt as entries into the causes ArrayList.  place Return a reference to an ArrayList of type String with the causes.  Remember to go through the file one line at a time using while(inputReader.hasNext) for the loop.

method  performSurvey with an ArrayList of type String called causesList and int numPolled as parameters and that throws an IOException(25)
Creates a two-dimensional int array called polling with dimensions causesList.size() and numPolled as the dimensions
For each Pollee
     Print out a title indicating the pollee number using the PrintWriter outputWriter
     For each cause (remember you are going through an ArrayList not an array her.
Generate the ranking value inclusively from 1 to 10 using randy and the two-parameter nextInt method of Random. Place the result for this cause for this pollee in the polling array  
Print out the ranking using the PrintWriter int outputWriter
Return a reference to the polling two-dimensional int array

method  calcCausesSums with pollResults reference as parameter
Calculate the total for each row (cause) and return results in a one-dimensional int array.


method  calcAverage with causesSum reference and the value of numPolled as parameters
Calculate the average for each row and return a reference to a one -imensional array.

method  reportResults with causesList, pollResults, averageForCause, causesSum references as parameters and a void return. Method throws an IOEXception in header. – Note: types for each parameter should be obvious here
Use the global PrintWriter outputWriter for all outputs below.
Produce the output similar to that in my pollresults.txt file.  The average must be printed to two decimal places. Use printf and proper formatting for columns
Call findMaxCause with a reference to average as a parameter, returning an int of the array index with the highest average or the first with that average if all are the same.
Call findMinCause with a reference to average as a parameter, returning an int of the array index with the lowest average or the first with that average if all are the same.
Write out the last two lines to your file as shown in the pollresults.txt file.

methods findMaxCause and findMinCause each accept a reference to average and return the index as described above.

Sample interactive portion of program:
Please enter a seed for Random: 7
Please enter name of file to read causes from: causes.txt
Please enter number of users to be polled: 8
Please enter name of file to write poll results to: pollresults.txt
