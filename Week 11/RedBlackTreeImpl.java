import java.util.Scanner;

public class RedBlackTreeImpl {
    enum Color { RED, BLACK }

    static class RBNode {
        int key;
        Color color;
        RBNode left, right, parent;

        RBNode(int key) {
            this.key = key;
            this.color = Color.RED;
        }
    }

    static class RedBlackTree {
        private final RBNode NIL = new RBNode(0);
        private RBNode root;

        public RedBlackTree() {
            NIL.color = Color.BLACK;
            NIL.left = NIL.right = NIL.parent = null;
            root = NIL;
        }

        public void insert(int key) {
            RBNode node = new RBNode(key);
            node.left = node.right = node.parent = NIL;

            RBNode y = NIL;
            RBNode x = root;

            while (x != NIL) {
                y = x;
                if (node.key < x.key)
                    x = x.left;
                else
                    x = x.right;
            }

            node.parent = y;
            if (y == NIL)
                root = node;
            else if (node.key < y.key)
                y.left = node;
            else
                y.right = node;

            node.left = NIL;
            node.right = NIL;
            node.color = Color.RED;
            fixInsert(node);
        }

        private void fixInsert(RBNode z) {
            while (z.parent.color == Color.RED) {
                if (z.parent == z.parent.parent.left) {
                    RBNode y = z.parent.parent.right;
                    if (y.color == Color.RED) {
                        z.parent.color = Color.BLACK;
                        y.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.right) {
                            z = z.parent;
                            leftRotate(z);
                        }
                        z.parent.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        rightRotate(z.parent.parent);
                    }
                } else {
                    RBNode y = z.parent.parent.left;
                    if (y.color == Color.RED) {
                        z.parent.color = Color.BLACK;
                        y.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.left) {
                            z = z.parent;
                            rightRotate(z);
                        }
                        z.parent.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        leftRotate(z.parent.parent);
                    }
                }
            }
            root.color = Color.BLACK;
        }

        private void leftRotate(RBNode x) {
            RBNode y = x.right;
            x.right = y.left;
            if (y.left != NIL)
                y.left.parent = x;
            y.parent = x.parent;
            if (x.parent == NIL)
                root = y;
            else if (x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
            y.left = x;
            x.parent = y;
        }

        private void rightRotate(RBNode x) {
            RBNode y = x.left;
            x.left = y.right;
            if (y.right != NIL)
                y.right.parent = x;
            y.parent = x.parent;
            if (x.parent == NIL)
                root = y;
            else if (x == x.parent.right)
                x.parent.right = y;
            else
                x.parent.left = y;
            y.right = x;
            x.parent = y;
        }

        public boolean search(int key) {
            return searchRec(root, key);
        }

        private boolean searchRec(RBNode node, int key) {
            if (node == NIL) return false;
            if (key == node.key) return true;
            return key < node.key ? searchRec(node.left, key) : searchRec(node.right, key);
        }

        public void inorder() {
            System.out.print("Inorder traversal: ");
            inorderRec(root);
            System.out.println();
        }

        private void inorderRec(RBNode node) {
            if (node != NIL) {
                inorderRec(node.left);
                System.out.print(node.key + " ");
                inorderRec(node.right);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RedBlackTree rbt = new RedBlackTree();

        System.out.println("Red Black Tree");

        int choice, value;
        do {
            System.out.println("\nChoose operation: 1. Insert 2. Search 3. Inorder Traversal 0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = scanner.nextInt();
                    rbt.insert(value);
                    break;
                case 2:
                    System.out.print("Enter value to search: ");
                    value = scanner.nextInt();
                    System.out.println("Found: " + rbt.search(value));
                    break;
                case 3:
                    rbt.inorder();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
