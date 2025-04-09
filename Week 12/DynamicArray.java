public class DynamicArray {
    private int[] array;
    private int size;
    private int c;
    
    public DynamicArray() {
        c = 10; 
        array = new int[c];
        size = 0;
    }
    
    public void add(int value) {
        if (size == c) {
            resize(c * 2);
        }
        array[size] = value;
        size++;
    }
    
    public int get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }
    
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        
        if (size > 0 && size == c / 4) {
            resize(c / 2);
        }
    }
    
    private void resize(int newc) {
        int[] newArray = new int[newc];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        c = newc;
    }
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.add(2);
        dynamicArray.add(13);
        dynamicArray.add(17);
        dynamicArray.add(24);
        dynamicArray.add(5);
        System.out.println("Array size: " + dynamicArray.size()); 
        System.out.println("Element at index 2: " + dynamicArray.get(2)); 
        dynamicArray.remove(1);
        System.out.println("Array size after removal: " + dynamicArray.size()); 
    }
}
