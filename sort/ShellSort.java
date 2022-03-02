package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8,9,1,7,2,3,5,4,6,0};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));
//        shellSort(arr);
//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));
        //时间测试
        //创建容量为80000的随机数组
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (int)(Math.random()*8);//生成一个[0,80000]的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为：" + dateString1);
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间为：" + dateString2);
    }

    //使用逐步推导的方式来编写希尔排序
    //交换法
    public static void shellSort(int[] arr) {


        int temp = 0;
        int count = 0;
        //根据逐步分析，使用循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素，共5组，步长为5
                for (int j = i - gap; j>=0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，则交换
                    if (arr[j] > arr[j+gap]) {
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
//            System.out.printf("第%d轮排序后：\n", ++count);
//            System.out.println(Arrays.toString(arr));
        }


        /*//第1轮排序
        //将10个数据分成了10/2 =5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素，共5组，步长为5
            for (int j = i - 5; j>=0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，则交换
                if (arr[j] > arr[j+5]) {
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println("第1轮排序后：");
        System.out.println(Arrays.toString(arr));


        //第2轮排序
        //将10个数据分成了5 / 2 = 2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素，共2组，步长为2
            for (int j = i - 2; j>=0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，则交换
                if (arr[j] > arr[j+2]) {
                    temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println("第2轮排序后：");
        System.out.println(Arrays.toString(arr));


        //第3轮排序
        //将10个数据分成了2 / 2 = 1组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素，共1组，步长为1
            for (int j = i - 1; j>=0; j -= 1) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("第3轮排序后：");
        System.out.println(Arrays.toString(arr));*/
    }


    //对交换式的希尔排序进行优化->移位法
    public static void shellSort2(int[] arr) {
        //增量gap，并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    //移动
                    arr[j] = arr[j-gap];
                    j-=gap;
                }
                //当退出while循环，就给temp找到插入的位置
                arr[j] = temp;
            }
        }
    }
}
