import java.util.*;
import java.time.*;

public class QuickSorter
{
    public enum PivotStrategy
    {
        FIRST_ELEMENT, // First element as pivot
        RANDOM_ELEMENT, // Randomly choosing the pivot element
        MEDIAN_OF_THREE_RANDOM_ELEMENTS, // Choosing the median of 3 randomly chosen elements as the pivot
        MEDIAN_OF_THREE_ELEMENTS // Median of first, center and last element (book method?)
    }

    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrategy) throws NullPointerException
    {
        // We need to check if either List or PivotStrategy is null, and exit out immediately.
        if (list == null)
        {
            throw new NullPointerException("List is null!");
        }
        if (pivotStrategy == null)
        {
            throw new NullPointerException("PivotStrategy is null!");
        }

        // Start recording the time of the QuickSort algorithm.
        Instant start = Instant.now();

        // Start quicksort
        quickSort(list, 0, list.size() - 1, pivotStrategy);

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

    public static ArrayList<Integer> generateAlmostSortedList(int size)
    {
        if (size < 0)
        {
            throw new IllegalArgumentException("Size must be non-negative");
        }

        ArrayList<Integer> list = new ArrayList<>(generateRandomList(size));
        Collections.sort(list);
        Random rand = new Random();

        int swaps = (int) (size * 0.1);
        for (int i = 0; i < swaps; i++)
        {
            int a = rand.nextInt(size);
            int b = rand.nextInt(size);
            swap(list, a, b);
        }
        return list;
    }

    private static <E extends Comparable<E>> void quickSort(List<E> list, int left, int right, PivotStrategy strategy)
    {
        if (left >= right)
        {
            return;
        }

        int pivotIndex = choosePivotIndex(list, left, right, strategy);
        swap(list, pivotIndex, right);

        int store = partition(list, left, right);
        swap(list, store, right);

        quickSort(list, left, store - 1, strategy);
        quickSort(list, store + 1, right, strategy);
    }

    private static <E extends Comparable<E>> int partition(List<E> list, int left, int right) 
    {
        E pivotValue = list.get(right);
        int storeIndex = left;
        for (int i = left; i < right; i++) 
        {
            if (list.get(i).compareTo(pivotValue) <= 0) 
            {
                swap(list, i, storeIndex++);
            }
        }
        return storeIndex;
    }

    private static <E> void swap(List<E> list, int i, int j) 
    {
        E tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    private static <E extends Comparable<E>> int choosePivotIndex(List<E> list, int left, int right, PivotStrategy strategy) 
    {
        Random random = new Random();
        switch (strategy)
        {
            case FIRST_ELEMENT:
                return left;

            case RANDOM_ELEMENT:
                return left + random.nextInt(right - left + 1);

            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
                int i1 = left + random.nextInt(right - left + 1);
                int i2 = left + random.nextInt(right - left + 1);
                int i3 = left + random.nextInt(right - left + 1);
                return medianIndex(list, i1, i2, i3);

            case MEDIAN_OF_THREE_ELEMENTS:
                // median of first, middle, last
                int mid = left + (right - left) / 2;
                return medianIndex(list, left, mid, right);

            default:
                throw new AssertionError("Unknown PivotStrategy: " + strategy);
        }
    }

    private static <E extends Comparable<E>> int medianIndex(List<E> list, int i, int j, int k)
    {
        E x = list.get(i), y = list.get(j), z = list.get(k);
        // return index of the median of (x,y,z)
        if (x.compareTo(y) <= 0)
        {
            if (y.compareTo(z) <= 0)
            {
                return j;
            }
            else if (x.compareTo(z) <= 0)
            {
                return k;
            }
            else
            {
                return i;
            }
        }
        else
        {
            if (x.compareTo(z) <= 0)
            {
                return i;
            }
            else if (y.compareTo(z) <= 0)
            {
                return k;
            }
            else
            {
                return j;
            }
        }
    }
}