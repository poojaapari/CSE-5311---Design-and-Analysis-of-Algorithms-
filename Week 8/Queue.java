import java.util.Scanner;

class Queue {
    private int[] arr;
    private int front, rear, size, s;

    public Queue(int s) {
        this.s = s;
        arr = new int[s];
        front = 0;
        size = 0;
        rear = -1;
    }

    public void enqueue(int x) {
        if (size == s) {
            System.out.println("Queue Overflow");
            return;
        }
        rear = (rear + 1) % s;
        arr[rear] = x;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            System.out.println("Queue Underflow");
            return -1;
        }
        int item = arr[front];
        front = (front + 1) % s;
        size--;
        return item;
    }

    public int front() {
        if (size == 0) return -1;
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter queue capacity: ");
        int size = sc.nextInt();
        Queue queue = new Queue(size);

        while (true) {
            System.out.println("1. Enqueue 2. Dequeue 3. Front 4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to enqueue: ");
                    int val = sc.nextInt();
                    queue.enqueue(val);
                    break;
                case 2:
                    System.out.println("Dequeued value: " + queue.dequeue());
                    break;
                case 3:
                    System.out.println("Front value: " + queue.front());
                    break;
                case 4:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid");
            }
        }
    }
}
