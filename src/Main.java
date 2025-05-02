import java.util.*;
import java.io.*;
import java.time.*;

public class Main
{
    public static void main(String[] args)
    {
        // start timing the whole run
        Instant programStart = Instant.now();

        // ./Main_Class 100000 report.txt unsorted.txt sorted.txt
        // Check if command line arguments were provided. Otherwise, do nothing and give error to user.
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
        System.out.println("Arguments were successfully provided. Please wait until items are sorted...");

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

        // Time each pivot strategy
        Map<QuickSorter.PivotStrategy, Duration> timings = new LinkedHashMap<>();
        ArrayList<Integer> sortedList = null;

        for (QuickSorter.PivotStrategy strategy : QuickSorter.PivotStrategy.values())
        {
            // make a fresh copy so each sort is on the identical input
            ArrayList<Integer> copy = new ArrayList<>(randomList);
            Duration elapsed;
            try
            {
                elapsed = QuickSorter.timedQuickSort(copy, strategy);
            }
            catch (NullPointerException e)
            {
                System.err.println("Error: pivot strategy or list was null: " + e.getMessage());
                continue;
            }

            timings.put(strategy, elapsed);

            // capture the sorted result from the MEDIAN_OF_THREE_ELEMENTS strategy
            if (strategy == QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS)
            {
                sortedList = copy;
            }
        }

        // Write sorted list to file (from median-of-three strategy)
        if (sortedList != null)
        {
            try
            {
                FileWriter sortedWriter = new FileWriter(sortedFileName);
                sortedWriter.write(sortedList.toString());
                sortedWriter.close();
            }
            catch (IOException e)
            {
                System.err.println("An error occurred while writing the sorted file: " + e.getMessage());
            }
        }

        // Write timing report
// --- Rewrite report in two halves ---
        try (BufferedWriter reportWriter = new BufferedWriter(new FileWriter(reportFileName)))
        {
            // Header
            reportWriter.write("Array Size = " + userArraySize);
            reportWriter.newLine();
            reportWriter.newLine();

            // --- First half: ISO-8601 durations ---
            for (Map.Entry<QuickSorter.PivotStrategy, Duration> e : timings.entrySet())
            {
                String name = e.getKey().name();
                String iso  = e.getValue().toString();        // e.g. "PT0.224627921S"
                reportWriter.write(String.format("%-30s: %s%n", name, iso));
            }

            reportWriter.newLine();

            // --- Second half: millisecond timings ---
            for (Map.Entry<QuickSorter.PivotStrategy, Duration> e : timings.entrySet())
            {
                String name   = e.getKey().name();
                long   millis = e.getValue().toMillis();
                reportWriter.write(String.format("%-30s: %6d ms%n", name, millis));
            }
        }
        catch (IOException e)
        {
            System.err.println("Error writing report: " + e.getMessage());
        }

        // after writing the report file, compute total elapsed
        long totalMs = Duration.between(programStart, Instant.now()).toMillis();
        System.out.println("Done in " + totalMs + " ms. Report in '" + reportFileName + "'.");
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
        }
        catch (NumberFormatException e)
        {
            // If the argument is anything other than a number, throw exception.
            throw new IllegalArgumentException("Array size must be an integer!");
        }
        return userArraySize;
    }
}