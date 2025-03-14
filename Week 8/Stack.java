import java.util.Scanner;

class Stack {
    private int[] arr;
    private int top;
    private int s;

    public Stack(int size) {
        arr = new int[size];
        s = size;
        top = -1;
    }

    public void push(int x) {
        if (top == s - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top--];
    }

    public int peek() {
        if (top == -1) return -1;
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter stack capacity: ");
        int size = s.nextInt();
        Stack stack = new Stack(size);

        while (true) {
            System.out.println("\n1. Push 2. Pop 3. Peek 4. Exit");
            System.out.print("Choose an option: ");
            int choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to push: ");
                    int val = s.nextInt();
                    stack.push(val);
                    break;
                case 2:
                    System.out.println("Popped value: " + stack.pop());
                    break;
                case 3:
                    System.out.println("Top value: " + stack.peek());
                    break;
                case 4:
                    s.close();
                    return;
                default:
                    System.out.println("Invalid");
            }
        }
    }
}
