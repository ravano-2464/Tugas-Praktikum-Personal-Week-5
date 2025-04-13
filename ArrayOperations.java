import java.util.*;

// Kelas untuk operasi pada Array (kode tetap sama)
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

// Kelas untuk visualisasi perbandingan (Baru)
class PerformanceVisualizer {
    // Menampilkan tabel perbandingan operasi
    public static void displayTable(Map<String, Double> performanceData) {
        System.out.println("\n+-----------------------+----------------+");
        System.out.println("| Operasi               | Waktu (ms)     |");
        System.out.println("+-----------------------+----------------+");
        
        for (Map.Entry<String, Double> entry : performanceData.entrySet()) {
            System.out.printf("| %-21s | %-14.4f |\n", entry.getKey(), entry.getValue());
        }
        
        System.out.println("+-----------------------+----------------+");
    }
    
    // Menampilkan grafik bar sederhana ASCII
    public static void displayChart(Map<String, Double> performanceData) {
        System.out.println("\nGrafik Perbandingan Waktu Eksekusi:");
        System.out.println("----------------------------------");
        
        // Tentukan nilai maksimum untuk skala
        double maxValue = 0;
        for (Double value : performanceData.values()) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        
        // Faktor skala untuk grafik (panjang bar maksimum 50 karakter)
        double scaleFactor = 50.0 / maxValue;
        
        // Tampilkan grafik bar untuk setiap operasi
        for (Map.Entry<String, Double> entry : performanceData.entrySet()) {
            String operation = entry.getKey();
            double executionTime = entry.getValue();
            int barLength = (int) (executionTime * scaleFactor);
            
            // Cetak label operasi dengan lebar tetap
            System.out.printf("%-21s ", operation);
            
            // Cetak bar dan nilai
            System.out.print("|");
            for (int i = 0; i < barLength; i++) {
                System.out.print("=");
            }
            System.out.printf(" %.4f ms\n", executionTime);
        }
        
        System.out.println("----------------------------------");
    }
    
    // Kumpulkan dan visualisasikan data kinerja untuk berbagai ukuran data
    public static void compareWithVariousDataSizes() {
        System.out.println("\nPerbandingan Kinerja dengan Berbagai Ukuran Data:");
        
        // Ukuran data untuk diuji
        int[] dataSizes = {100, 1000, 10000, 100000};
        
        System.out.println("\n+------------+------------------------+------------------------+");
        System.out.println("| Ukuran     | Traversal Array (ms)    | Traversal ArrayList (ms)|");
        System.out.println("+------------+------------------------+------------------------+");
        
        for (int size : dataSizes) {
            // Buat data uji dengan ukuran tertentu
            int[] array = new int[size];
            ArrayList<Integer> arrayList = new ArrayList<>(size);
            
            for (int i = 0; i < size; i++) {
                array[i] = i;
                arrayList.add(i);
            }
            
            // Ukur traversal array
            ArrayOperations arrayOps = new ArrayOperations(array);
            long startTime = System.nanoTime();
            // Modifikasi untuk tidak mencetak (hanya mengukur waktu komputasi)
            for (int i = 0; i < array.length; i++) {
                int temp = array[i]; // Akses elemen tanpa mencetak
            }
            long endTime = System.nanoTime();
            double arrayTraversalTime = (endTime - startTime) / 1_000_000.0;
            
            // Ukur traversal ArrayList
            ArrayListOperations listOps = new ArrayListOperations(arrayList);
            startTime = System.nanoTime();
            // Modifikasi untuk tidak mencetak (hanya mengukur waktu komputasi)
            for (int i = 0; i < arrayList.size(); i++) {
                int temp = arrayList.get(i); // Akses elemen tanpa mencetak
            }
            endTime = System.nanoTime();
            double arrayListTraversalTime = (endTime - startTime) / 1_000_000.0;
            
            // Tampilkan hasil
            System.out.printf("| %-10d | %-22.4f | %-22.4f |\n", size, arrayTraversalTime, arrayListTraversalTime);
        }
        
        System.out.println("+------------+------------------------+------------------------+");
    }
}

// Kelas Perbandingan Kinerja
class Comparison {
    public static void comparePerformance(int[] array, ArrayList<Integer> list) {
        // Simpan hasil kinerja dalam Map untuk visualisasi
        Map<String, Double> performanceData = new LinkedHashMap<>();
        
        long startTime, endTime;
        double elapsedTime;
        
        // Array Traversal
        startTime = System.nanoTime();
        ArrayOperations arrayOps = new ArrayOperations(array);
        arrayOps.traverseArray();
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Waktu Traversal Array: %.4f ms%n", elapsedTime);
        performanceData.put("Traversal Array", elapsedTime);

        // ArrayList Traversal
        startTime = System.nanoTime();
        ArrayListOperations listOps = new ArrayListOperations(list);
        listOps.traverseArrayList();
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Waktu Traversal ArrayList: %.4f ms%n", elapsedTime);
        performanceData.put("Traversal ArrayList", elapsedTime);

        // Perbandingan Pencarian
        int searchTarget = 30;
        
        startTime = System.nanoTime();
        int arrayIndex = arrayOps.linearSearch(searchTarget);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Pencarian %d di Array: indeks %d, Waktu: %.4f ms%n",
                          searchTarget, arrayIndex, elapsedTime);
        performanceData.put("Linear Search Array", elapsedTime);

        startTime = System.nanoTime();
        int listIndex = listOps.searchElement(searchTarget);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Pencarian %d di ArrayList: indeks %d, Waktu: %.4f ms%n",
                          searchTarget, listIndex, elapsedTime);
        performanceData.put("Search ArrayList", elapsedTime);
        
        // Tambahkan pengukuran untuk binary search pada array yang diurutkan
        int[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        ArrayOperations sortedArrayOps = new ArrayOperations(sortedArray);
        
        startTime = System.nanoTime();
        int binarySearchIndex = sortedArrayOps.binarySearch(searchTarget);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Binary Search di Array: indeks %d, Waktu: %.4f ms%n",
                         binarySearchIndex, elapsedTime);
        performanceData.put("Binary Search Array", elapsedTime);
        
        // Menambahkan operasi penyisipan
        startTime = System.nanoTime();
        int[] newArray = arrayOps.insertElement(25, 2);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1_000_000.0;
        performanceData.put("Insert Array", elapsedTime);
        
        startTime = System.nanoTime();
        listOps.addElement(25);
        endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1_000_000.0;
        performanceData.put("Insert ArrayList", elapsedTime);
        
        // Visualisasikan hasil dalam tabel dan grafik
        PerformanceVisualizer.displayTable(performanceData);
        PerformanceVisualizer.displayChart(performanceData);
    }

    public static void main(String[] args) {
        // Data uji
        int[] array = {10, 20, 30, 40, 50};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        
        // Jalankan perbandingan
        System.out.println("PERBANDINGAN KINERJA ARRAY VS ARRAYLIST");
        System.out.println("======================================");
        comparePerformance(array, list);
        
        // Tambahkan perbandingan dengan ukuran data berbeda
        PerformanceVisualizer.compareWithVariousDataSizes();
        
        // Demonstrasi penyisipan dan penghapusan
        System.out.println("\nDEMONSTRASI OPERASI DASAR");
        System.out.println("========================");
        
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