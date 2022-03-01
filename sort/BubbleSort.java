package sort;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 * 时间复杂度为：O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3,9,-1,10,-2};
//        System.out.println("排序前的数组为：");
//        System.out.println(Arrays.toString(arr));
        //创建容量为80000的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*80000);//生成一个[0,80000]的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为：" + dateString1);
        bubbleSort(arr);
        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间为：" + dateString2);
//        System.out.println("排序后的数组为：");
//        System.out.println(Arrays.toString(arr));

        //第1趟排序，就是将最大的数排在最后
        /*for (int j = 0; j < arr.length - 1; j++) {
            //如果前面的数比后面的大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j +1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第1趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第2趟排序，就是将第二大的数排在倒数第二位
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            //如果前面的数比后面的大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j +1];
                arr[j + 1] = temp;
            }
        }

        System.out.println("第2趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第3趟排序，就是将第三大的数排在倒数第三位
        for (int j = 0; j < arr.length - 1 - 2; j++) {
            //如果前面的数比后面的大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j +1];
                arr[j + 1] = temp;
            }
        }

        System.out.println("第3趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第4趟排序，就是将第四大的数排在倒数第四位
        for (int j = 0; j < arr.length - 1 - 3; j++) {
            //如果前面的数比后面的大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j +1];
                arr[j + 1] = temp;
            }
        }

        System.out.println("第4趟排序后的数组");
        System.out.println(Arrays.toString(arr));*/
    }

    private static void bubbleSort(int[] arr) {
        //冒泡排序的时间复杂度O(n^2)
        int temp = 0;//临时变量
        for (int i = 0; i < arr.length - 1; i++) {
            //表示变量，表示是否进行过交换， 有可能提前
            //完成排序，后续就不会再发生交换操作
            boolean flag = false;
            for (int j = 0; j < arr.length -1 - i; j++) {
                //如果前面的数比后面的大，则交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j +1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
//            System.out.printf("第%d趟排序后的数组\n", i + 1);
//            System.out.println(Arrays.toString(arr));
            if (!flag) {//某一趟一次交换都没有发生过,表明已经完成排序
                break;
            }
        }
    }
}
