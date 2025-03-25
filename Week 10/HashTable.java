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
        HashNode current = head;
        while (current != null) {
            if (current.k == k) {
                current.v = v;
                return;
            }
            current = current.next;
        }

        HashNode newNode = new HashNode(k, v);
        newNode.next = head;
        if (head != null) head.prev = newNode;
        head = newNode;
    }

    public boolean delete(int k) {
        HashNode current = head;
        while (current != null) {
            if (current.k == k) {
                if (current.prev != null) current.prev.next = current.next;
                else head = current.next;
                if (current.next != null) current.next.prev = current.prev;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Integer get(int k) {
        HashNode current = head;
        while (current != null) {
            if (current.k == k) return current.v;
            current = current.next;
        }
        return null;
    }

    public void printList() {
        HashNode current = head;
        while (current != null) {
            System.out.print(" -> " + current.k + ":" + current.v);
            current = current.next;
        }
    }
}

public class HashTable {
    private DoublyLinkedList[] table;
    private int capacity;
    private int size;
    private final double A = 0.6180339887; 
    private final double LOAD_FACTOR_UP = 0.75;
    private final double LOAD_FACTOR_DOWN = 0.25;
    private final int MIN_CAPACITY = 8;

    public HashTable(int initialCapacity) {
        this.capacity = Math.max(initialCapacity, MIN_CAPACITY);
        this.size = 0;
        this.table = new DoublyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new DoublyLinkedList();
        }
    }

    private int hash(int k) {
        double frac = (k * A) % 1;
        return (int)(capacity * frac);
    }

    public void insert(int k, int v) {
        int index = hash(k);
        int oldSize = size;
        if (table[index].get(k) == null) size++;
        table[index].insertOrUpdate(k, v);

        if ((double)size / capacity > LOAD_FACTOR_UP) {
            resize(capacity * 2);
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
            if (capacity > MIN_CAPACITY && (double)size / capacity < LOAD_FACTOR_DOWN) {
                resize(capacity / 2);
            }
        }
    }

    private void resize(int newCapacity) {
        DoublyLinkedList[] oldTable = table;
        table = new DoublyLinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            table[i] = new DoublyLinkedList();
        }

        int oldCapacity = capacity;
        capacity = newCapacity;
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            HashNode current = oldTable[i].head;
            while (current != null) {
                insert(current.k, current.v);
                current = current.next;
            }
        }

        System.out.println("Resized to capacity " + newCapacity);
    }

    public void display() {
        System.out.println("\nHash Table:");
        for (int i = 0; i < capacity; i++) {
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
