/**
 * This class defines a quicksort method which sorts an array of comparable objects in place using multiple threads to
 * parallelize the sorting.
 *
 * @author Kenneth Powers
 */

package net.kenpowers.projects.quicksort;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Sorter {
    /**
     * Number of threads to use for sorting.
     */
    private static final int N_THREADS = Runtime.getRuntime().availableProcessors();

    /**
     * Thread pool used for executing sorting Runnables.
     */
    private static Executor pool = Executors.newFixedThreadPool(N_THREADS);

    /**
     * Main method used for sorting from clients. Input is sorted in place using multiple threads.
     *
     * @param input The array to sort.
     * @param <T>   The type of the objects being sorted, must extend Comparable.
     */
    public static <T extends Comparable<T>> void quicksort(T[] input) {
        final AtomicInteger count = new AtomicInteger(1);
        pool.execute(new QuicksortRunnable<T>(input, 0, input.length - 1, count));
        try {
            synchronized (count) {
                count.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts a section of an array using quicksort. The method used is not technically recursive as it just creates new
     * runnables and hands them off to the ThreadPoolExecutor.
     *
     * @param <T> The type of the objects being sorted, must extend Comparable.
     */
    private static class QuicksortRunnable<T extends Comparable<T>> implements Runnable {
        /**
         * The array being sorted.
         */
        private final T[] values;
        /**
         * The starting index of the section of the array to be sorted.
         */
        private final int left;
        /**
         * The ending index of the section of the array to be sorted.
         */
        private final int right;
        /**
         * The number of threads currently executing.
         */
        private final AtomicInteger count;

        /**
         * Default constructor. Sets up the runnable object for execution.
         *
         * @param values     The array to sort.
         * @param left       The starting index of the section of the array to be sorted.
         * @param right      The ending index of the section of the array to be sorted.
         * @param count      The number of currently executing threads.
         */
        public QuicksortRunnable(T[] values, int left, int right, AtomicInteger count) {
            this.values = values;
            this.left = left;
            this.right = right;
            this.count = count;
        }

        /**
         * The main logic. If the array is of two or more items this method partitions the appropriate section of the
         * array and then creates two more runnables to sort the remaining sections; otherwise, we countdown the latch.
         */
        @Override
        public void run() {
            if (left < right) {
                T pivotValue = values[right];
                int storeIndex = left;
                for (int i = left; i < right; i++) {
                    if (values[i].compareTo(pivotValue) < 0) {
                        swap(values, i, storeIndex);
                        storeIndex++;
                    }
                }
                swap(values, storeIndex, right);
                count.getAndAdd(2);
                pool.execute(new QuicksortRunnable<T>(values, left, storeIndex - 1, count));
                pool.execute(new QuicksortRunnable<T>(values, storeIndex + 1, right, count));
            }
            synchronized (count) {
                if (count.getAndDecrement() == 1)
                    count.notify();
            }
        }

        /**
         * Simple swap method.
         *
         * @param input The array to swap values in.
         * @param left  The index of the first value to swap with the second value.
         * @param right The index of the second value to swap with the first value.
         */
        private void swap(Object[] input, int left, int right) {
            Object temp = input[left];
            input[left] = input[right];
            input[right] = temp;
        }
    }
}