import java.util.*;

// Kelas untuk operasi pada Array
public class ArrayOperations {
    private int[] array;

    // Konstruktor
    public ArrayOperations(int[] initialArray) {
        this.array = initialArray;
    }

    // Metode Traversal
    public void traverseArray() {
        System.out.print("Array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + (i < array.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    // Linear Search
    public int linearSearch(int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search (memerlukan array terurut)
    public int binarySearch(int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] == target) {
                return mid;
            }
            
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }

    // Metode Penyisipan Elemen
    public int[] insertElement(int element, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("Indeks tidak valid");
        }

        int[] newArray = new int[array.length + 1];
        
        // Salin elemen sebelum indeks
        System.arraycopy(array, 0, newArray, 0, index);
        
        // Sisipkan elemen baru
        newArray[index] = element;
        
        // Salin sisa elemen
        System.arraycopy(array, index, newArray, index + 1, array.length - index);
        
        return newArray;
    }

    // Metode Penghapusan Elemen
    public int[] removeElement(int index) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("Indeks tidak valid");
        }

        int[] newArray = new int[array.length - 1];
        
        // Salin elemen sebelum indeks
        System.arraycopy(array, 0, newArray, 0, index);
        
        // Salin sisa elemen setelah indeks
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        
        return newArray;
    }
}

// Kelas untuk operasi pada ArrayList
class ArrayListOperations {
    private ArrayList<Integer> arrayList;

    // Konstruktor
    public ArrayListOperations(ArrayList<Integer> initialList) {
        this.arrayList = new ArrayList<>(initialList);
    }

    // Metode Traversal
    public void traverseArrayList() {
        System.out.println("ArrayList: " + arrayList);
    }

    // Menambah Elemen
    public void addElement(int element) {
        arrayList.add(element);
    }

    // Menghapus Elemen berdasarkan indeks
    public void removeElement(int index) {
        if (index >= 0 && index < arrayList.size()) {
            arrayList.remove(index);
        }
    }

    // Pencarian Elemen
    public int searchElement(int target) {
        return arrayList.indexOf(target);
    }

    // Pengurutan Elemen
    public void sortElements() {
        Collections.sort(arrayList);
    }

    // Getter untuk ArrayList
    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }
}

// Kelas Perbandingan Kinerja
class Comparison {
    public static void comparePerformance(int[] array, ArrayList<Integer> list) {
        // Perbandingan Traversal
        long startTime, endTime;

        // Array Traversal
        startTime = System.nanoTime();
        ArrayOperations arrayOps = new ArrayOperations(array);
        arrayOps.traverseArray();
        endTime = System.nanoTime();
        System.out.printf("Waktu Traversal Array: %.4f ms%n", (endTime - startTime) / 1_000_000.0);

        // ArrayList Traversal
        startTime = System.nanoTime();
        ArrayListOperations listOps = new ArrayListOperations(list);
        listOps.traverseArrayList();
        endTime = System.nanoTime();
        System.out.printf("Waktu Traversal ArrayList: %.4f ms%n", (endTime - startTime) / 1_000_000.0);

        // Perbandingan Pencarian
        int searchTarget = 30;
        
        startTime = System.nanoTime();
        int arrayIndex = arrayOps.linearSearch(searchTarget);
        endTime = System.nanoTime();
        System.out.printf("Pencarian %d di Array: indeks %d, Waktu: %.4f ms%n", 
                          searchTarget, arrayIndex, (endTime - startTime) / 1_000_000.0);

        startTime = System.nanoTime();
        int listIndex = listOps.searchElement(searchTarget);
        endTime = System.nanoTime();
        System.out.printf("Pencarian %d di ArrayList: indeks %d, Waktu: %.4f ms%n", 
                          searchTarget, listIndex, (endTime - startTime) / 1_000_000.0);
    }

    public static void main(String[] args) {
        // Data uji
        int[] array = {10, 20, 30, 40, 50};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));

        // Jalankan perbandingan
        comparePerformance(array, list);

        // Demonstrasi penyisipan dan penghapusan
        ArrayOperations arrayOps = new ArrayOperations(array);
        int[] newArray = arrayOps.insertElement(25, 2);
        System.out.print("Array setelah penyisipan: ");
        for (int num : newArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        ArrayListOperations listOps = new ArrayListOperations(list);
        listOps.addElement(25);
        listOps.sortElements();
        System.out.println("ArrayList setelah penyisipan dan pengurutan: " + listOps.getArrayList());
    }
}