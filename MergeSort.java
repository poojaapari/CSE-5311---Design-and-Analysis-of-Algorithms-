import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5,2,4,7,1,3,2,6};
        System.out.println("Original Array:" + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Array after Sorting: " + Arrays.toString(arr));
    }
    
    public static void mergeSort(int[] arr, int l, int r) {
        if(l<r) 
        {
            int m = l+(r-l)/2;
            mergeSort(arr,l,m);
            mergeSort(arr,m+1,r);
            merge(arr,l,m,r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1,n2;
        n1 = m-l+1;
        n2 = r-m;
        int[] lArr = new int[n1];
        int[] rArr = new int[n2];
        for(int i=0;i<n1;i++) 
        {
            lArr[i] = arr[l+i];
        }
        for(int j=0;j<n2;j++)
        {
            rArr[j] = arr[m+1+j];
        }
        int i=0,j=0,k=l;
        while(i<n1 && j<n2) 
        {
            if(lArr[i] <= rArr[j]) 
            {
                arr[k] = lArr[i];
                i++;
            }
            else
            {
                arr[k] = rArr[j];
                j++;
            }
            k++;
        }
        while(i<n1) 
        {
            arr[k] = lArr[i];
            i++;
            k++;
        }
        while(j<n2) 
        {
            arr[k] = rArr[j];
            j++;
            k++;
        }
    }
}
