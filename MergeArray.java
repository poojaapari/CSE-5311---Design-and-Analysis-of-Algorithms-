import java.util.*;

class MergeArray {
    static void mergeArrays(int arr1[], int arr2[], int n1, int n2, int arr3[]) {
        int i=0, j=0, k=0;
        while (i<n1 && j<n2) {
            if (arr1[i] < arr2[j])
                arr3[k++] = arr1[i++];
            else
                arr3[k++] = arr2[j++];
        }
        while (i<n1)
            arr3[k++] = arr1[i++];
        while (j<n2)
            arr3[k++] = arr2[j++];
    }
    
    static void mergeKArrays(int arr[][], int i, int j, int output[]) {
        if (i == j) {
            System.arraycopy(arr[i], 0, output, 0, arr[i].length);
            return;
        }
        if (j - i == 1) {
            mergeArrays(arr[i], arr[j], arr[i].length, arr[j].length, output);
            return;
        }
        int mid = (i+j) / 2;
        int leftSize = 0, rightSize = 0;
        for (int x=i; x<=mid; x++) leftSize += arr[x].length;
        for (int x=mid+1; x<=j; x++) rightSize += arr[x].length;
        int[] out1 = new int[leftSize];
        int[] out2 = new int[rightSize];
        mergeKArrays(arr, i, mid, out1);
        mergeKArrays(arr, mid + 1, j, out2);
        mergeArrays(out1, out2, out1.length, out2.length, output);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Number of arrays: ");
            int k = sc.nextInt();
            System.out.print("Size of each array: ");
            int n = sc.nextInt();
            int[][] arr = new int[k][n];
            for (int i=0; i<k; i++) {
                System.out.println("Enter " + n + " sorted elements for array " + (i + 1) + ": ");
                for (int j=0; j<n; j++) {
                    arr[i][j] = sc.nextInt();
                }
                Arrays.sort(arr[i]);
            }
            int[] output = new int[n * k];
            mergeKArrays(arr, 0, k - 1, output);
            System.out.println("Merged array in sorted order:");
            System.out.println(Arrays.toString(output));
        }
    }
}
