package sorting;
import java.util.Scanner;

public class InsertionSort {

    public static void main(String[] args) {
    	int i,j,k,n;
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
        for(i=1;i<n;i++)
        {
        	k = a[i];
        	j = i-1;
        	while(j>=0 && a[j]>k)
        	{
        		a[j+1] = a[j];
        		j--;
        	}
        	a[j+1] = k;
        }
        for(i=0;i<n;i++) 
        {
            System.out.print(a[i] + " ");
        }     
        s.close();
    }
}
