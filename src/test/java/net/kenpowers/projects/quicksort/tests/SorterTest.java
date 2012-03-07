package net.kenpowers.projects.quicksort.tests;

import net.kenpowers.projects.quicksort.Sorter;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for the sorter.
 *
 * @author Kenneth Powers
 */
public class SorterTest {
    /**
     * Tests the Sorter implementation on small set of values.
     */
    @Test
    public void testSort() {
        // Define some values to sort.
        Integer[] values = {0, 9, 2, 5, 1, 5, 2, 7, 9, 2, 5, 0, 5, 4, 1};
        // Sort a copy of the values with the built in Java sorting implementation for comparison.
        Integer[] sortedValues = new Integer[values.length];
        System.arraycopy(values, 0, sortedValues, 0, values.length);
        Arrays.sort(sortedValues);
        // Sort the original values with the multi-threaded in-place quicksort.
        Sorter.quicksort(values);
        // Assert equality.
        assertArrayEquals(sortedValues, values);
    }

    /**
     * Tests the Sorter implementation on a larger set of values.
     */
    @Test
    public void largeTestSort() {
        // Generate an array of one million random integers.
        Random random = new Random(System.currentTimeMillis());
        Integer[] values = new Integer[1000000];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt();
        }
        // Sort a copy of the values with the built in Java sorting implementation for comparison.
        Integer[] sortedValues = new Integer[1000000];
        System.arraycopy(values, 0, sortedValues, 0, values.length);
        Arrays.sort(sortedValues);
        // Sort the original values with the multi-threaded in-place quicksort.
        Sorter.quicksort(values);
        // Assert equality.
        assertArrayEquals(sortedValues, values);
    }
}
