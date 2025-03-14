import java.util.Scanner;

public class OrderStatistic {
    private static int partition(int[] arr, int l, int h) {
        int pivot = arr[h];
        int i = l;

        for (int j = l; j < h; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, h);
        return i;
    }

    public static int quickSelect(int[] arr, int l, int h, int i) {
        if (l <= h) {
            int index = partition(arr, l, h);

            if (index == i) {
                return arr[index];
            } else if (index > i) {
                return quickSelect(arr, l, index - 1, i);
            } else {
                return quickSelect(arr, index + 1, h, i);
            }
        }
        return Integer.MAX_VALUE; 
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = s.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        System.out.print("Enter the order statistic (k-th smallest element): ");
        int k = s.nextInt();

        if (k > 0 && k <= n) {
            int result = quickSelect(arr, 0, n - 1, k - 1);
            System.out.println("The " + k + "-th smallest element is: " + result);
        } else {
            System.out.println("Invalid");
        }

        s.close();
    }
}
