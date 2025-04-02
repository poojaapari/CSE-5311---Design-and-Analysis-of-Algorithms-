import java.util.Scanner;

public class BinarySearchTree {
    static class BSTNode {
        int key;
        BSTNode l, r;

        BSTNode(int key) {
            this.key = key;
        }
    }

    static class BST {
        private BSTNode root;

        public void insert(int key) {
            root = insertRec(root, key);
        }

        private BSTNode insertRec(BSTNode node, int key) {
            if (node == null) return new BSTNode(key);
            if (key < node.key) node.l = insertRec(node.l, key);
            else if (key > node.key) node.r = insertRec(node.r, key);
            return node;
        }

        public void delete(int key) {
            root = deleteRec(root, key);
        }

        private BSTNode deleteRec(BSTNode node, int key) {
            if (node == null) return null;
            if (key < node.key) node.l = deleteRec(node.l, key);
            else if (key > node.key) node.r = deleteRec(node.r, key);
            else {
                if (node.l == null) return node.r;
                if (node.r == null) return node.l;
                node.key = minValue(node.r);
                node.r = deleteRec(node.r, node.key);
            }
            return node;
        }

        private int minValue(BSTNode node) {
            while (node.l != null) node = node.l;
            return node.key;
        }

        public boolean search(int key) {
            return searchRec(root, key);
        }

        private boolean searchRec(BSTNode node, int key) {
            if (node == null) return false;
            if (node.key == key) return true;
            return key < node.key ? searchRec(node.l, key) : searchRec(node.r, key);
        }

        public void inorder() {
            System.out.print("Inorder traversal: ");
            inorderRec(root);
            System.out.println();
        }

        private void inorderRec(BSTNode node) {
            if (node != null) {
                inorderRec(node.l);
                System.out.print(node.key + " ");
                inorderRec(node.r);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST bst = new BST();
        int choice, value;

        System.out.println("Binary Search Tree");

        do {
            System.out.println("\nChoose operation: 1. Insert 2. Delete 3. Search 4. Inorder Traversal 0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = scanner.nextInt();
                    bst.insert(value);
                    break;
                case 2:
                    System.out.print("Enter value to delete: ");
                    value = scanner.nextInt();
                    bst.delete(value);
                    break;
                case 3:
                    System.out.print("Enter value to search: ");
                    value = scanner.nextInt();
                    System.out.println("Found: " + bst.search(value));
                    break;
                case 4:
                    bst.inorder();
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
