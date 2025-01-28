package sorting;

import java.util.Random;

public class Benchmark {

    public static void main(String[] args) {
        int[] input = {5, 10, 20, 100, 1000, 5000, 10000};

        System.out.println("System Information:");
        System.out.println("Processor: Intel(R) Core(TM) i7-10510U CPU @ 1.80 GHz (8 CPUs), ~2.3 GHz");
        System.out.println("Memory: 16 GB RAM");
        System.out.println("Operating System: Windows 11 Home");
        System.out.println();

        System.out.printf("%-15s %-20s %-20s %-20s%n", "Array Size (n)", "Insertion Sort (ms)", "Selection Sort (ms)", "Bubble Sort (ms)");

        for (int n : input) {
            int[] randomArray = createRandomArray(n);
            long insertionSortTime = runtime(randomArray.clone(), Benchmark::insertionSort);
            long selectionSortTime = runtime(randomArray.clone(), Benchmark::selectionSort);
            long bubbleSortTime = runtime(randomArray.clone(), Benchmark::bubbleSort);
            System.out.printf("%-15d %-20d %-20d %-20d%n", n, insertionSortTime, selectionSortTime, bubbleSortTime);
        }
    }

    public static long runtime(int[] array, Sorting s) {
        long time = 0;
        int j = 10; // performing multiple trials 

        for (int i = 0; i < j; i++) {
            int[] copy = array.clone(); // copy of each trial
            long startTime = System.nanoTime(); 
            s.execute(copy); 
            long endTime = System.nanoTime(); 
            time += (endTime - startTime);
        }
        return time / j / 1_000_000; //average time in ms
    }
    
    @FunctionalInterface
    public interface Sorting {
        void execute(int[] array);
    }

    public static void insertionSort(int[] arr) {
        int i, n = arr.length;
        for (i=1;i<n;i++) {
            int k = arr[i];
            int j = i - 1;
            while (j>=0 && arr[j]>k) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = k;
        }
    }

    public static void selectionSort(int[] arr) {
        int i, j, n = arr.length;
        for (i=0;i<n-1;i++) {
            int min = i;
            for (j=i+1;j<n;j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    // Bubble Sort Implementation
    public static void bubbleSort(int[] arr) {
        int i, j, n = arr.length;
        for (i=0;i<n-1;i++) {
            boolean swap = false;
            for (j=0;j<n-i-1;j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap adjacent elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) break; // Exit if no swaps occurred in the current pass
        }
    }

    // Generate an array of random integers
    public static int[] createRandomArray(int size) {
        Random random = new Random();
        return random.ints(size, 0, 10_000).toArray(); // Generate integers between 0 and 9999
    }
}
