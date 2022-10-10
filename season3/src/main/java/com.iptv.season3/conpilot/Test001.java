package com.iptv.season3.conpilot;

/**
 * @author: liuqi
 * @date: 2022/6/17 14:55
 * @description:
 */
public class Test001 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("hello world");




    }

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }

    // 快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int base = arr[i];
            while (i < j) {
                while (i < j && arr[j] >= base) {
                    j--;
                }
                arr[i] = arr[j];
                while (i < j && arr[i] <= base) {
                    i++;
                }
                arr[j] = arr[i];
            }
            arr[i] = base;
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }

    // lru
    public static void lfu(){

    }

}
