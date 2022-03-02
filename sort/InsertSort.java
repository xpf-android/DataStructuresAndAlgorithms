package sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        insertSort1(arr);

    }



    //插入排序
    public static void insertSort1(int[] arr) {
        /*//使用逐步推导的方式，找规律

        //第1轮 给arr[1]找插入的位置
        // {101,34,119,1} => {34,101,119,1}
        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1 - 1;//即arr[1]前面这个数的下标
        //给insertVal找到插入的位置
        //说明
        //1. insertIndex >= 0 保证在给insertVal找插入位置，不越界
        //2. insertVal < arr[insertIndex] 待插入的数，需要更换位置
        //3. 就需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第1轮排序后：");
        System.out.println(Arrays.toString(arr));


        //第2轮 给arr[2]找插入的位置
        // {34, 101, 119, 1} => {34,101,119,1}
        //定义待插入的数
        insertVal = arr[2];
        insertIndex = 2 - 1;//即arr[2]前面这个数的下标
        //给insertVal找到插入的位置
        //说明
        //1. insertIndex >= 0 保证在给insertVal找插入位置，不越界
        //2. insertVal < arr[insertIndex] 待插入的数，需要更换位置
        //3. 就需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第2轮排序后：");
        System.out.println(Arrays.toString(arr));

        //第3轮 给arr[3]找插入的位置
        // {34, 101, 119, 1} => {1,34,101,119}
        //定义待插入的数
        insertVal = arr[3];
        insertIndex = 3 - 1;//即arr[3]前面这个数的下标
        //给insertVal找到插入的位置
        //说明
        //1. insertIndex >= 0 保证在给insertVal找插入位置，不越界
        //2. insertVal < arr[insertIndex] 待插入的数，需要更换位置
        //3. 就需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第3轮排序后：");
        System.out.println(Arrays.toString(arr));*/


        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1;//即arr[1]前面这个数的下标
            //给insertVal找到插入的位置
            //说明
            //1. insertIndex >= 0 保证在给insertVal找插入位置，不越界
            //2. insertVal < arr[insertIndex] 待插入的数，需要更换位置
            //3. 就需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环，说明插入的位置找到，insertIndex + 1
            arr[insertIndex + 1] = insertVal;
            System.out.printf("第%d轮排序后：\n", i);
            System.out.println(Arrays.toString(arr));
        }


    }
}
