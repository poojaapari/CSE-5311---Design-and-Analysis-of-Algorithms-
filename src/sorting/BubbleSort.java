package sorting;
import java.util.Scanner;

public class BubbleSort {

    public static void main(String[] args) {
    	int i,j,k,n;
    	boolean swap;
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
        for (i=0;i<n-1;i++) 
        {
            swap = false;
            for (j=0;j<n-i-1;j++) 
            {
                if (a[j] > a[j + 1]) 
                {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swap = true;
                }
            }
        if (!swap) break;
        }
        for(i=0;i<n;i++) 
        {
            System.out.print(a[i] + " ");
        }     
        s.close();
    }
}
