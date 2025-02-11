import java.util.*;

class DuplicateArray {
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;
        int n = 0;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] != arr[n]) {
                n++; 
                arr[n] = arr[i]; 
            }
        }
        return n + 1; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Size of Array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Sorted elements:");
        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();
        int n1 = removeDuplicates(arr);
        System.out.println("Array after removing duplicates:");
        for (int i=0; i<n1; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
