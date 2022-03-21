package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 注意：使用二分查找的前提是该数组是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr = {1,8,10,89,1000,1234};

//        int resIndex = binarySearch(arr, 0, arr.length - 1, 1);
//        System.out.println("resIndex=" +resIndex);

        int[] arr = {1,8,10,89,1000,1000,1000,1234};
        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList="+resIndexList);


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


    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //当left > right时，说明递归这个数组，但是没有找到
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        //找到的情况

        if (findVal > midVal) {//向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch2(arr, left, mid -1,findVal);
        } else {
            //思路分析：
            //1.在找到mid索引值，不要马上返回
            //2.向mid索引值的左边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
            //3.向mid索引值的右边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
            //4.将ArrayList返回
            List<Integer> resIndexList = new ArrayList<Integer>();
            //向mid索引值的左边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //否则，就把temp放入到resIndexList，此时arr[temp] = findVal
                resIndexList.add(temp);
                temp -= 1;//temp左移
            }
            resIndexList.add(mid);

            //向mid索引值的右边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                //否则，就把temp放入到resIndexList，此时arr[temp] = findVal
                resIndexList.add(temp);
                temp += 1;//temp右移
            }
            return resIndexList;
        }
    }
}
