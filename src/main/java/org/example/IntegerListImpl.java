package org.example;

import org.example.exception.ElementNotFoundException;
import org.example.exception.InvalidIndexException;
import org.example.exception.NullItemException;
import org.example.exception.StorageIsFullException;

import java.util.Arrays;
import java.util.Random;

public class IntegerListImpl implements IntegerList {

    private Integer[] storage;
    private int size;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    public IntegerListImpl(int initsize) {
        storage = new Integer[initsize];
    }

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            storage[size++] = item;
            return item;
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = storage[index];
        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        size--;
        return item;

    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            Integer s = storage[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            Integer s = storage[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    private void sort(Integer[] arr) {

            for (int i = 1; i < arr.length; i++) {
                int temp = arr[i];
                int j = i;
                while (j > 0 && arr[j - 1] >= temp) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    public Integer[] bubbleSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int temp = 0;
                if (array[j] < array[i]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
//        System.out.println(Arrays.toString(array));
        return array;
    }

    // выбором
    public Integer[] selectionSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int indexMin = i;
            int temp = 0;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    indexMin = j;
                }
            }
            temp = array[i];
            array[i] = array[indexMin];
            array[indexMin] = temp;
        }

//        System.out.println(Arrays.toString(array));
        return array;
    }

    // вставками
    public Integer[] insertionSort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = 0;
            int currIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[currIndex] < array[j]) {
                    temp = array[currIndex];
                    array[currIndex] = array[j];
                    array[j] = temp;
                    currIndex--;
                } else {
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(array));
        return array;
    }

    public void sortTest() {
        Integer[] array = new Integer[100_000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(50_000);
        }

        long start = System.currentTimeMillis();
        this.bubbleSort(array.clone());
        System.out.println("Bubble sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");

        start = System.currentTimeMillis();
        this.selectionSort(array.clone());
        System.out.println("Selection sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");

        start = System.currentTimeMillis();
        this.insertionSort(array.clone());
        System.out.println("Insertion sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");
    }

}
