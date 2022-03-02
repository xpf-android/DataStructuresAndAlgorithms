package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 * 时间复杂度O(n^2)
 */
public class SelectSort {
    public static void main(String[] args) {
        //逻辑测试
        /*int[] arr = {101, 119,34,  1};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);*/

        //时间测试
        //创建容量为80000的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*80000);//生成一个[0,80000]的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为：" + dateString1);
        selectSort(arr);
        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间为：" + dateString2);
    }


    //选择排序
    public static void selectSort(int[] arr) {
        //原始的数组： 101,34,119,1
        //算法 先简单-->后复杂 就是可以把一个复杂的算法，拆分成简单的问题-> 逐步解决
        //在推导过程中，我们发现了规律，因此，可以用循环解决

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                //如果需要按从大到小排序，只需改动一处 min < arr[j]
                if (min > arr[j]) {//说明假定的最小值，并不是最小
                    min = arr[j];//重置min
                    minIndex = j;//重置minIndex
                }
            }
            //将最小值，放在arr[i], 即交换
            if (minIndex != i){//如果arr[i]最小，无需执行下面的操作
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.printf("第%d轮排序后:\n", i + 1);
//            System.out.println(Arrays.toString(arr));
        }

        /*//第1轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定的最小值，并不是最小
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        //将最小值，放在arr[0], 即交换
        if (minIndex != 0){//如果arr[0]最小，无需执行下面的操作
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第1轮排序后：");
        System.out.println(Arrays.toString(arr));

        //第2轮
        minIndex = 1;
        min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定的最小值，并不是最小
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        //将最小值，放在arr[1], 即交换
        if (minIndex != 1) {//如果arr[1]最小，无需执行下面的操作
            arr[minIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第2轮排序后：");
        System.out.println(Arrays.toString(arr));

        //第3轮
        minIndex = 2;
        min = arr[2];
        for (int j = 2 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定的最小值，并不是最小
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        //将最小值，放在arr[2], 即交换
        if (minIndex != 2) {//如果arr[1]最小，无需执行下面的操作
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第3轮排序后：");
        System.out.println(Arrays.toString(arr));*/
    }
}
