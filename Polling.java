import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Polling
{
    static Scanner keyboard = new Scanner(System.in);
    static Random randy;
    static PrintWriter outputWriter;

    public static void main(String[] args) throws IOException
    {
        System.out.printf("Enter a seed for random\n");
        int seed = Integer.parseInt(keyboard.nextLine());
        randy = new Random(seed);

        System.out.printf("Please enter input file of causes\n");
        String inputFileName = keyboard.nextLine();
        File inputFileCauses = new File(inputFileName);
        if(!inputFileCauses.exists())
        {
            System.out.printf("Input File %s not found\n", inputFileCauses);
            System.exit(0);
        }
        ArrayList<String> causesList = readCauses(inputFileCauses);

        System.out.printf("Please enter number of users to be polled\n");
        int numPolled = Integer.parseInt(keyboard.nextLine());

        System.out.printf("Please enter output file to write poll results to:\n");
        String outputFile = keyboard.nextLine();
        outputWriter = new PrintWriter(new FileWriter(outputFile));

        int[][] pollResults = performSurvey(causesList, numPolled);
        int[] causesSum = calcCausesSums(pollResults);
        double[] avgForCause = calcAverage(causesSum, numPolled);

        recordResults(causesList, pollResults, avgForCause, causesSum);

        outputWriter.close();
        keyboard.close();

        System.out.printf("Poll Results recorded to " + outputFile);
    }

    public static ArrayList<String> readCauses(File fileName) throws IOException
    {
        ArrayList<String> causes = new ArrayList<>();
        Scanner fileScanner = new Scanner(fileName);
        while(fileScanner.hasNextLine())
        {
            String cause = fileScanner.nextLine().trim();
            if (!cause.isEmpty())
            {
                causes.add(cause);
            }
        }
        fileScanner.close();
        return causes;
    }

    public static int[][] performSurvey(ArrayList<String> causesList, int numPolled) throws IOException
    {
        int columns = numPolled;
        int rows = causesList.size();
        int[][] polling = new int[rows][columns];

        for (int count = 0; count < numPolled; count++)
        {
            outputWriter.printf("Person number %d:%n", count + 1);
            for (int causeIndex = 0; causeIndex < rows; causeIndex++)
            {
                int rank = randy.nextInt(10) + 1;
                polling[causeIndex][count] = rank;
                outputWriter.printf("%s: %d%n", causesList.get(causeIndex), rank);
            }
            outputWriter.println();//line break
        }
        return polling;
    }

    public static int[] calcCausesSums(int[][] pollResults)
    {
        int columns = pollResults[0].length;
        int rows = pollResults.length;
        int[] sums = new int[rows];
        for (int count1 = 0; count1 < rows; count1++)
        {
            int total = 0;
            for (int count2 = 0; count2 < columns; count2++)
            {
                total += pollResults[count1][count2];
            }
            sums[count1] = total;
        }
        return sums;
    }

    public static double[] calcAverage(int[] causesSums, int numPolled)
    {
        double[] avg = new double[causesSums.length];
        for (int count = 0; count < causesSums.length; count++)
        {
            avg[count] = (double) causesSums[count] / numPolled;
        }
        return avg;
    }

    public static void recordResults(ArrayList<String> causesList, int[][] pollResults, double[] avgForCause, int[] causesSum) throws IOException
    {
        outputWriter.printf("Poll Results:");
        outputWriter.println();

        outputWriter.printf("%-25s", "Cause");
        for (int count = 1; count <= pollResults[0].length; count++)
        {
            outputWriter.printf("Person%-4d", count);
        }

        outputWriter.printf("Total Average%n");
    

        for (int count1 = 0; count1<causesList.size(); count1++)
        {
            outputWriter.printf("%-25s", causesList.get(count1));
            for (int count2 = 0; count2<pollResults[0].length; count2++)
            {
                outputWriter.printf("%-8d", pollResults[count1][count2]);
            }
            outputWriter.printf("%-8d%7.2f%n", causesSum[count1], avgForCause[count1]);
        }
        
        outputWriter.println();

        int maxIndex = findMaxCause(avgForCause);
        int minIndex = findMinCause(avgForCause);

        outputWriter.printf("Cause with highest avg: %s (Average = %.2f)%n", causesList.get(maxIndex), avgForCause[maxIndex]);
        outputWriter.printf("Cause with lowest avg: %s (Average = %.2f)%n", causesList.get(minIndex), avgForCause[minIndex]);
    }
    
    public static int findMaxCause(double[] average)
    {
        int maxIndex=0;
        double maxValue = average[0];
        for (int count = 1; count<average.length; count++)
        {
            if (average[count]>maxValue)
            {
                maxValue = average[count];
                maxIndex = count;
            }
        }
        return maxIndex;
    }

    public static int findMinCause (double[] average)
    {
        int minIndex = 0;
        double minValue = average[0];
        for (int count = 1; count<average.length; count++)
        {
            if (average[count]<minValue)
            {
                minValue=average[count];
                minIndex=count;
            }
        }
        return minIndex;
    }


}
