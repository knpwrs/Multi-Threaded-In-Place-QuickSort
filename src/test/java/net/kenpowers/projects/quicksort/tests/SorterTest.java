package net.kenpowers.projects.quicksort.tests;

import net.kenpowers.projects.quicksort.Sorter;
import org.junit.BeforeClass;
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
    @Test
    public void testSort() {
        Integer[] values = {0, 9, 2, 5, 1, 5, 2, 7, 9, 2, 5, 0, 5, 4, 1};
        Integer[] sortedValues = new Integer[values.length];
        System.arraycopy(values, 0, sortedValues, 0, values.length);
        Arrays.sort(sortedValues);
        Sorter.quicksort(values);
        assertArrayEquals(sortedValues, values);
    }

    @Test
    public void largeTestSort() {
        Random random = new Random(System.currentTimeMillis());
        Integer[] values = new Integer[1000000];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt();
        }
        Integer[] sortedValues = new Integer[1000000];
        System.arraycopy(values, 0, sortedValues, 0, values.length);
        Arrays.sort(sortedValues);
        Sorter.quicksort(values);
        assertArrayEquals(sortedValues, values);
    }
}
