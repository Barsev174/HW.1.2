package org.example;

import java.util.Random;

public class Main {
    static IntegerListImpl integerList = new IntegerListImpl();
    public static void main(String[] args) {
        Integer[] array = new Integer[100_000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(50_000);
        }

        long start = System.currentTimeMillis();
        integerList.bubbleSort(array.clone());
        System.out.println("Bubble sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");

        start = System.currentTimeMillis();
        integerList.selectionSort(array.clone());
        System.out.println("Selection sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");

        start = System.currentTimeMillis();
        integerList.insertionSort(array.clone());
        System.out.println("Insertion sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");
    }
}