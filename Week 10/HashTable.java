class HashNode {
    int k, v;
    HashNode next, prev;

    public HashNode(int k, int v) {
        this.k = k;
        this.v = v;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList {
    HashNode head;

    public void insertOrUpdate(int k, int v) {
        HashNode curr = head;
        while (curr != null) {
            if (curr.k == k) {
                curr.v = v;
                return;
            }
            curr = curr.next;
        }

        HashNode newNode = new HashNode(k, v);
        newNode.next = head;
        if (head != null) head.prev = newNode;
        head = newNode;
    }

    public boolean delete(int k) {
        HashNode curr = head;
        while (curr != null) {
            if (curr.k == k) {
                if (curr.prev != null) curr.prev.next = curr.next;
                else head = curr.next;
                if (curr.next != null) curr.next.prev = curr.prev;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public Integer get(int k) {
        HashNode curr = head;
        while (curr != null) {
            if (curr.k == k) return curr.v;
            curr = curr.next;
        }
        return null;
    }

    public void printList() {
        HashNode curr = head;
        while (curr != null) {
            System.out.print(" -> " + curr.k + ":" + curr.v);
            curr = curr.next;
        }
    }
}

public class HashTable {
    private DoublyLinkedList[] table;
    private int c;
    private int size;
    private final double A = 0.6180339887; 
    private final double LOAD_FACTOR_UP = 0.75;
    private final double LOAD_FACTOR_DOWN = 0.25;
    private final int MIN_CAPACITY = 8;

    public HashTable(int initialCapacity) {
        this.c = Math.max(initialCapacity, MIN_CAPACITY);
        this.size = 0;
        this.table = new DoublyLinkedList[c];
        for (int i = 0; i < c; i++) {
            table[i] = new DoublyLinkedList();
        }
    }

    private int hash(int k) {
        double frac = (k * A) % 1;
        return (int)(c * frac);
    }

    public void insert(int k, int v) {
        int index = hash(k);
        int oldSize = size;
        if (table[index].get(k) == null) size++;
        table[index].insertOrUpdate(k, v);

        if ((double)size / c > LOAD_FACTOR_UP) {
            resize(c * 2);
        }
    }

    public Integer get(int k) {
        return table[hash(k)].get(k);
    }

    public void remove(int k) {
        int index = hash(k);
        boolean removed = table[index].delete(k);
        if (removed) {
            size--;
            if (c > MIN_CAPACITY && (double)size / c < LOAD_FACTOR_DOWN) {
                resize(c / 2);
            }
        }
    }

    private void resize(int newCapacity) {
        DoublyLinkedList[] oldTable = table;
        table = new DoublyLinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            table[i] = new DoublyLinkedList();
        }

        int oldCapacity = c;
        c = newCapacity;
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            HashNode curr = oldTable[i].head;
            while (curr != null) {
                insert(curr.k, curr.v);
                curr = curr.next;
            }
        }

        System.out.println("Resized to capacity " + newCapacity);
    }

    public void display() {
        System.out.println("\nHash Table:");
        for (int i = 0; i < c; i++) {
            System.out.print("Index " + i + ":");
            table[i].printList();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable(6);

        ht.insert(12, 120);
        ht.insert(21, 210);
        ht.insert(30, 300);
        ht.insert(42, 420);
        ht.insert(8, 80);
        ht.insert(99, 990);

        ht.remove(21);
        ht.remove(42);

        ht.insert(77, 770);
        ht.insert(55, 550);
        ht.insert(81, 810);

        System.out.println("Value for key 55: " + ht.get(55));
        ht.display();
    }
}
