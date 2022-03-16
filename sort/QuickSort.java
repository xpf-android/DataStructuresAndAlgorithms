package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {-9, 78, 0, 23, -567, 70,1,1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr=" + Arrays.toString(arr));*/

        //速度测试
        //创建容量为80000的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*80000);//生成一个[0,80000]的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为：" + dateString1);
        quickSort(arr, 0, arr.length - 1);
        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间为：" + dateString2);

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量
        //while循环的目的是让比pivot值小放到左边
        //比pivot值大放到右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot的值，才退出
            while(arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot的值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l >= r 说明pivot的左右两边的值，已经按照左边全部是
            //小于等于pivot的值，右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //防止已经交换的两个数都等于pivot，出现死循环，一直交换

            //如果交换后，发现arr[l] == pivot的值，r--,前移
            if (arr[l] == pivot) {
                r -= 1;
            }

            //如果交换后，发现arr[r] == pivot的值，l++,后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        //如果l == r, 必须l++, r--,否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }


    }
}
