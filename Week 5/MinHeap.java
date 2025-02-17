import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MinHeap<T extends Comparable<T>> {
    private List<T> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }
    
    private int parent(int i) {
        return (i - 1) >> 1; 
    }

    public void buildMinHeap(List<T> elements) {
        heap.clear();
        heap.addAll(elements);
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
        printHeap();
    }
    
    private void heapify(int i) {
        int smallest = i;
        int left = (i << 1) + 1;
        int right = (i << 1) + 2; 

        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }
    
    public void insert(T item) {
        heap.add(item);
        int index = heap.size() - 1;
        while (index > 0 && heap.get(parent(index)).compareTo(heap.get(index)) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
        printHeap(); 
    }
    
    public T extractMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Empty Heap");
        }
        T min = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        if (!heap.isEmpty()) {
            heapify(0);
        }
        return min;
    }
    
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    private void printHeap() {
        System.out.println("Current Heap: " + heap);
    }
    
    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter numbers to build heap: ");
        String input = sc.nextLine();
        String[] numbers = input.split(" ");
        List<Integer> elements = new ArrayList<>();

        for (String num : numbers) {
            try {
                elements.add(Integer.parseInt(num));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number: " + num);
            }
        }

        minHeap.buildMinHeap(elements);

        while (true) {
            System.out.println("\nChoose an operation: ");
            System.out.println("1. Insert an element");
            System.out.println("2. Extract the minimum element");
            System.out.println("3. Print heap");
            System.out.println("4. Exit");
            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice");
                sc.next(); 
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter number to insert: ");
                    int num = sc.nextInt();
                    minHeap.insert(num);  
                    break;

                case 2:
                    try {
                        int min = minHeap.extractMin();
                        System.out.println("Minimum element removed: " + min);
                    } catch (IllegalStateException e) {
                        System.out.println("Empty Heap");
                    }
                    break;

                case 3:
                    minHeap.printHeap();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
