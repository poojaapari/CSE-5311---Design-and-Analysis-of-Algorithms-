package sorting;
import java.util.Scanner;

public class SelectionSort {

    public static void main(String[] args) {
    	int i,j,min,temp,n;
        Scanner s = new Scanner(System.in);
        System.out.println("Number of elements:");
        n = s.nextInt();
        int[] a = new int[n];
        System.out.print("Enter the elements:");
        for(i=0;i<n;i++)
        {
        	a[i] = s.nextInt();
        }
        System.out.print("Original Array:");
        for(i=0;i<n;i++) 
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        System.out.print("Sorted Array:");
        for (i=0;i<n-1;i++) {
            min = i;
            for (j=i+1;j<n;j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
        for(i=0;i<n;i++) 
        {
            System.out.print(a[i] + " ");
        } 
        s.close();
    }
}
