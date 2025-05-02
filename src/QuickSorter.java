import java.util.*;
import java.time.*;

public class QuickSorter
{
    public enum PivotStrategy
    {
        FIRST_ELEMENT, // First element as pivot
        RANDOM_ELEMENT, // Randomly choosing the pivot element
        MEDIAN_OF_TWO_ELEMENTS, // Choosing the median of 3 randomly chosen elements as the pivot
        MEDIAN_OF_THREE_ELEMENTS // Median of first, center and last element (book method?)
    } // Use for enum: PivotStrategy myVar = PivotStrategy.FIRST_ELEMENT;


    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrategy) throws NullPointerException
    {
        // We need to check if either List or PivotStrategy is null, and exit out immediately.
        if (list.isEmpty())
        {
            throw new NullPointerException("List is empty!");
        }
        if (pivotStrategy == null)
        {
            throw new NullPointerException("PivotStrategy is null!");
        }

        // Start recording the time of the QuickSort algorithm.
        Instant start = Instant.now();
        // … call your quickSort implementation here …

        // End of QuickSort. Record the ending timestamp.
        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    public static ArrayList<Integer> generateRandomList(int arraySize) throws IllegalArgumentException
    {
        // We need to check if array size is valid.
        if (arraySize <= 0)
        {
            throw new IllegalArgumentException("Array size must be greater than 0!");
        }

        ArrayList<Integer> randomList = new ArrayList<>();
        Random rand = new Random();

        for (int j = 0; j < arraySize; j++)
        {
            randomList.add(rand.nextInt());
        }
        return randomList;
    }
}