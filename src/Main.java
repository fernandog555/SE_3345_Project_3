import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        int userArraySize = Integer.parseInt(args[0]);
        QuickSorter quickSorter = new QuickSorter();
        ArrayList<Integer> randomList = quickSorter.generateRandomList(userArraySize);

        System.out.println(randomList);

    }
}
        /*
        // Need to 4 command line arguments
        // Will read in a file, and output to a file
        // ./Main_Class 100000 report.txt unsorted.txt sorted.txt

        // Check if command line arguments were provided. Otherwise do nothing and give error to user.
        if (args.length != 4)
        {
            System.out.println("Warning: It looks like there is no arguments provided. Please check your arguments.");
            System.out.println("Usage: <ARRAY_SIZE> <REPORTS_FILE> <UNSORTED_FILE> <SORTED_FILE>");
            return;
        }

        // Assuming we got the files opened and read.
        System.out.println("Arguments were successfully provided. Check the files for results.");

        int arraySize = Integer.parseInt(args[0]);
        String reportFile = args[1];
        String unsortedFile = args[2];
        String sortedFile = args[3];
        */