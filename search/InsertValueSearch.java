package search;

import java.util.Arrays;

/**
 * 插值查找
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
//        System.out.println(Arrays.toString(arr));

//        int index = insertValueSearch(arr, 0, arr.length - 1, 78);
        int index = binarySearch(arr, 0, arr.length - 1, 78);
        System.out.println("index="+ index);
    }

    /**
     * 插值查找
     * @param arr 原始数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的数据
     * @return 如果找到，返回对应的下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        System.out.println("插值查找被调用...");

        //注意：findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须加上
        //由于findVal参与到mid的计算，否则我们得到的mid 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //求出mid
        int mid = left + (right - left)*(findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//说明应该向右边递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal){//说明应该向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }



    /**
     * 二分查找算法
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，否则返回-1
     */
    public static int binarySearch(int[] arr, int left,int right, int findVal) {
        System.out.println("二分查找被调用...");

        //当left > right时，说明递归这个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        //找到的情况

        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch(arr, left, mid -1,findVal);
        } else {
            return mid;
        }
    }
}
