import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        // Need to 4 command line arguments
        // Will read in a file, and output to a file

        // Check if command line arguments were provided. Otherwise do nothing and give error to user.
        if (args.length != 4)
        {
            System.out.println("Warning: It looks like there is no arguments provided. Please check your arguments.");
            System.out.println("Usage: <ARRAY_SIZE> <REPORTS_FILE> <UNSORTED_FILE> <SORTED_FILE>");
            return;
        }

        // Assuming we got the files opened and read.
        System.out.println("Arguments were successfully provided. Check the files for results.");

        int arraySize = args[0];
        String reportFile = args[1];
        String unsortedFile = args[2];
        String sortedFile = args[3];

        // Try catch block for reading and writing the files
        try (Scanner scanFile = new Scanner(new File(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile)))
        {
        // Scan each line of the input file and process those commands until we reach end of file.
            while (scanFile.hasNextLine())
            {

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Command file not found: " + inputFile);
        }
        catch (IOException e)
        {
            System.out.println("Error writing to output file: " + outputFile);
        }

    }
}
