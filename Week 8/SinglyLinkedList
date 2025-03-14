import java.util.Scanner;

class SinglyLinkedList {
    private int[] arr;
    private int[] next;
    private int head, size, s;

    public SinglyLinkedList(int s) {
        this.s = s;
        arr = new int[s];
        next = new int[s];
        for (int i = 0; i < s; i++) next[i] = -1;
        head = -1;
        size = 0;
    }

    public void insert(int x) {
        if (size == s) {
            System.out.println("List is full");
            return;
        }
        arr[size] = x;
        next[size] = head;
        head = size;
        size++;
    }

    public void printList() {
        int curr = head;
        while (curr != -1) {
            System.out.print(arr[curr] + " -> ");
            curr = next[curr];
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter list capacity: ");
        int size = sc.nextInt();
        SinglyLinkedList list = new SinglyLinkedList(size);

        while (true) {
            System.out.println("\n1. Insert\n2. Print\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int val = sc.nextInt();
                    list.insert(val);
                    break;
                case 2:
                    list.printList();
                    break;
                case 3:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid");
            }
        }
    }
}
