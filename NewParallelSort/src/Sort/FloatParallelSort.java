/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sort;

import java.util.stream.IntStream;

/**
 *
 * @author edkjd
 */
public class FloatParallelSort {

    public static void bubleSorting(float[] arr) {
        float temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void newSorting(float[] arr) {
        int i, j;
        int partCount = arr.length>10000?10000:arr.length;
        float max = -Float.MAX_VALUE, min = Float.MAX_VALUE;
        for (i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        if(max==min){
            return;
        }
        float interval = (max - min) / partCount;
        int[] countByPart = new int[partCount];
        for (i = 0; i < arr.length; i++) {
            j = (int) ((arr[i] - min) / interval);
            if (j >= partCount) {
                j = partCount - 1;
            }
            countByPart[j]++;
        }
        int num;
        float[][] part = new float[partCount][];
        for (i = 0; i < partCount; i++) {
            if (countByPart[i] == 0) {
                continue;
            }
            part[i] = new float[countByPart[i]];
            countByPart[i] = 0;
        }
        for (i = 0; i < arr.length; i++) {
            j = (int) ((arr[i] - min) / interval);
            if (j >= partCount) {
                j = partCount - 1;
            }
            part[j][countByPart[j]] = arr[i];
            countByPart[j]++;

        }
        IntStream.range(0, partCount).parallel().forEach(k -> {
            if (part[k] == null || part[k].length == 1) {
                return;
            }
            if (part[k].length >= 64) {
                newSorting(part[k]);
            } else if (part[k].length >= 8) {
                quickSort(part[k]);
            } else {
                bubleSorting(part[k]);
            }
        });
        

        num = 0;
        for (i = 0; i < partCount; i++) {
            if (part[i] == null) {
                continue;
            }
            for (j = 0; j < part[i].length; j++) {
                arr[num] = part[i][j];
                num++;
            }
        }
    }

    public static void quickSort(float[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int part = partition(arr, start, end);
        if (start < part - 1) {
            quickSort(arr, start, part - 1);
        }
        if (end > part) {
            quickSort(arr, part, end);
        }
    }

    private static void quickSort(float[] arr, int start, int end) {
        int part = partition(arr, start, end);
        if (start < part - 1) {
            quickSort(arr, start, part - 1);
        }
        if (end > part) {
            quickSort(arr, part, end);
        }
    }

    private static int partition(float[] arr, int start, int end) {
        float pivot = arr[(start + end) / 2];
        while (start <= end) {
            while (arr[start] < pivot) {
                start++;
            }
            while (arr[end] > pivot) {
                end--;
            }
            if (start <= end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return start;
    }

    private static void swap(float[] arr, int start, int end) {
        float tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }

}
