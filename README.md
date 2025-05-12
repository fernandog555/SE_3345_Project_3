Hello, welcome back! I am Fernando Flores (FXF220008). This is my submission for project 3.

I developed my program using IntelliJ, and used the Java JDK 23 to compile and run this program.
There shouldn't be any issues using any other JDK versions, as it runs just fine on OnlineGDB.

The program uses command line arguments to enter an input and outfile. On OnlineGDB, there is an
empty box that says "Command line arguments". Here, you need to write <ARRAY_SIZE> <REPORTS_FILE>
<UNSORTED_FILE> <SORTED_FILE> otherwise the program will not display output.
The link to the program on OnlineGDB is here: https://onlinegdb.com/IgJX-jw2X

My project consists of 2 Java files:

    - Main.java
    - QuickSorter.java

You can run the program without any IDE by going to OnlineGDB and pressing "Run". You may need to
add the command line arguments at the bottom. If you want to use IntelliJ, import the Java files
and input/output files, and add the command line arguments that correspond to the appropriate
file names. Then, with Main class open, click on run. If you get an error message in the console,
it means you did not provide the arguments or do not have the files. Otherwise, the program will
print to the output file.

Here are some samples of the report files. These are for the fully random lists using QuickSort:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    Array Size = 5000000

    FIRST_ELEMENT                 : PT1.4499889S (1449 ms)
    RANDOM_ELEMENT                : PT1.5660534S (1566 ms)
    MEDIAN_OF_THREE_RANDOM_ELEMENTS: PT1.5551281S (1555 ms)
    MEDIAN_OF_THREE_ELEMENTS      : PT1.3756217S (1375 ms)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

These are for the fully random lists using Collections.Sort:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    Array Size = 5000000

    COLLECTIONS_SORT              : PT1.2593587S (1259 ms)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    These are for the almost sorted lists using QuickSort:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    Array Size = 5000000

    FIRST_ELEMENT                 : PT3.6534907S (3653 ms)
    RANDOM_ELEMENT                : PT1.7135748S (1713 ms)
    MEDIAN_OF_THREE_RANDOM_ELEMENTS: PT1.7044183S (1704 ms)
    MEDIAN_OF_THREE_ELEMENTS      : PT1.5737228S (1573 ms)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    These are for the almost sorted lists using Collections.Sort:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    Array Size = 5000000

    COLLECTIONS_SORT              : PT0.7417209S (741 ms)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Here is the summary for all the data listed above:

------ FULLY RANDOM DATA ------
    - Collections.Sort was consistently the fastest sort because it uses dual pivot Quicksort.
    - The MEDIAN_OF_THREE_ELEMENTS strategy of my quicksort was slightly slower than library sort.
    - The FIRST_ELEMENT strategy was the slowest of them all.

------ ALMOST SORTED DATA ------
    - Collections.Sort still was the fastest sort out of all strategies on my quicksort.
    - The FIRST_ELEMENT strategy was terrible as it had O(n^2) time.
    - The other strategies suffered a bit more too due to the nature of the almost sorted list.

Overall, the Collections.sort was the fastest across all tests. It always outperformed my QuickSort
algorithm, no matter if it was completely random or almost sorted. However, the best pivot strategy
across all my tests was MEDIAN_OF_THREE_ELEMENTS because it avoids the O(n^2) times that FIRST_ELEMENT
had. MEDIAN_OF_THREE_ELEMENTS is the best because it balanced the partitions and avoids heavy recursion
on either sorted or almost sorted data.
