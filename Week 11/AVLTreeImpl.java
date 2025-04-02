import java.util.Scanner;

public class AVLTreeImpl {
    static class AVLNode {
        int key, height;
        AVLNode l, r;

        AVLNode(int key) {
            this.key = key;
            height = 1;
        }
    }
    static class AVLTree {
        private AVLNode root;

        int height(AVLNode node) {
            return node == null ? 0 : node.height;
        }

        int getBalance(AVLNode node) {
            return node == null ? 0 : height(node.l) - height(node.r);
        }

        AVLNode rRotate(AVLNode y) {
            AVLNode x = y.l;
            AVLNode T2 = x.r;
            x.r = y;
            y.l = T2;
            y.height = Math.max(height(y.l), height(y.r)) + 1;
            x.height = Math.max(height(x.l), height(x.r)) + 1;
            return x;
        }

        AVLNode lRotate(AVLNode x) {
            AVLNode y = x.r;
            AVLNode T2 = y.l;
            y.l = x;
            x.r = T2;
            x.height = Math.max(height(x.l), height(x.r)) + 1;
            y.height = Math.max(height(y.l), height(y.r)) + 1;
            return y;
        }

        public void insert(int key) {
            root = insertRec(root, key);
        }

        private AVLNode insertRec(AVLNode node, int key) {
            if (node == null) return new AVLNode(key);

            if (key < node.key)
                node.l = insertRec(node.l, key);
            else if (key > node.key)
                node.r = insertRec(node.r, key);
            else
                return node;

            node.height = 1 + Math.max(height(node.l), height(node.r));
            int balance = getBalance(node);

            // Balancing cases
            if (balance > 1 && key < node.l.key) return rRotate(node);
            if (balance < -1 && key > node.r.key) return lRotate(node);
            if (balance > 1 && key > node.l.key) {
                node.l = lRotate(node.l);
                return rRotate(node);
            }
            if (balance < -1 && key < node.r.key) {
                node.r = rRotate(node.r);
                return lRotate(node);
            }

            return node;
        }

        public boolean search(int key) {
            return searchRec(root, key);
        }

        private boolean searchRec(AVLNode node, int key) {
            if (node == null) return false;
            if (key == node.key) return true;
            return key < node.key ? searchRec(node.l, key) : searchRec(node.r, key);
        }

        public void inorder() {
            System.out.print("Inorder traversal: ");
            inorderRec(root);
            System.out.println();
        }

        private void inorderRec(AVLNode node) {
            if (node != null) {
                inorderRec(node.l);
                System.out.print(node.key + " ");
                inorderRec(node.r);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree avl = new AVLTree();

        System.out.println("AVL Tree");

        int choice, value;
        do {
            System.out.println("\nChoose operation: 1. Insert 2. Search 3. Inorder Traversal 0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = scanner.nextInt();
                    avl.insert(value);
                    break;
                case 2:
                    System.out.print("Enter value to search: ");
                    value = scanner.nextInt();
                    System.out.println("Found: " + avl.search(value));
                    break;
                case 3:
                    avl.inorder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
