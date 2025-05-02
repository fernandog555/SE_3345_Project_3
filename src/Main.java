import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        // Need to 4 command line arguments
        // Will read in a file, and output to a file
        // ./Main_Class 100000 report.txt unsorted.txt sorted.txt

        // Check if command line arguments were provided. Otherwise do nothing and give error to user.
        if (args.length != 4)
        {
            System.out.println("Warning: It looks like there is an incorrect amount of arguments provided. Please check your arguments.");
            System.out.println("Usage: <ARRAY_SIZE> <REPORTS_FILE> <UNSORTED_FILE> <SORTED_FILE>");
            return;
        }

        int userArraySize = getUserArraySize(args);
        String reportFileName = args[1];
        String unsortedFileName = args[2];
        String sortedFileName = args[3];

        QuickSorter quickSorter = new QuickSorter();
        ArrayList<Integer> randomList = quickSorter.generateRandomList(userArraySize);

        // Assuming we got the files opened and read.
        System.out.println("Arguments were successfully provided. Check the files for results.");
        System.out.println("Random List: " + randomList);

        // Write to unsorted file.
        try
        {
            FileWriter unsortedFile = new FileWriter(unsortedFileName);
            unsortedFile.write(String.valueOf(randomList));
            unsortedFile.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unsorted file not found!");
        }
        catch (IOException e)
        {
            System.err.println("An error occurred while trying to write to the unsorted file!");
        }

        // Time each strategy

        // Write to sorted file

        // Write to report file

    }

    private static int getUserArraySize(String[] args)
    {
        int userArraySize;

        // Validate command line arguments
        try
        {
            // Check if the input is a double. Throw exception because we only accept integers.
            double temp = Double.parseDouble(args[0]);
            if (temp % 1 != 0)
            {
                throw new IllegalArgumentException("Array size must be a whole number!");
            }

            // If the argument isn't a double, accept as an integer and move on.
            userArraySize = Integer.parseInt(args[0]);
        } catch (NumberFormatException e)
        {
            // If the argument is anything other than a number, throw exception.
            throw new IllegalArgumentException("Array size must be an integer!");
        }
        return userArraySize;
    }
}