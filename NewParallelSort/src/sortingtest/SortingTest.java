/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sortingtest;

import java.util.Arrays;

/**
 *
 * @author edkjd
 */
public class SortingTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        floatSortTest1(5000000);
    }

    public static void floatSortTest1(int size) {

        float nStTime = 0, jStTime = 0;
        float[] arr = new float[size];
        float[] arr1 = new float[size];
        for (int b = 0; b < 10; b++) {
            for (int a = 0; a < arr.length; a++) {
                arr1[a] = arr[a] = (float) (Math.random() * Float.MAX_VALUE-Float.MAX_VALUE/2);
            }

            long time = System.nanoTime();
            float temp;
            Sort.FloatParallelSort.newSorting(arr);
            System.out.println(size + " my new float sort : " + (temp = (System.nanoTime() - time) * 0.000001f) + "ms");
            nStTime += temp;

            time = System.nanoTime();
            Arrays.sort(arr1);
            System.out.println(size + " java float sort : " + (temp = (System.nanoTime() - time) * 0.000001f) + "ms");
            jStTime += temp;
            System.out.println();
        }
        System.out.println(size + " my new sort avg : " + nStTime / 10);
        System.out.println(size + " java sort avg : " + jStTime / 10);
        System.out.println(jStTime/nStTime+" rate faster");
    }

}
