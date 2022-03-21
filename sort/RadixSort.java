package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        /*int arr[] = {666666,53, 3, 542, 748, 14, 24,1245,25365};

        radixSort(arr);*/

        //速度测试
        //创建容量为80000的随机数组
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int)(Math.random()*800000);//生成一个[0,80000]的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为：" + dateString1);

        radixSort(arr);

        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间为：" + dateString2);


    }

    //基数排序方法
    public static void radixSort(int[] arr) {

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一位数组(桶)，大小定为arr.length
        //3.很显然，基数排序是空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录桶的每次放入的数据个数
        //bucketElementCounts[0]，记录的就是 bucket[0]桶放入数据的个数
        int[] bucketElementCounts = new int[10];

        //根据下面的逐步推导过程，得到最终的基数排序代码

        //得到数组中最大的数的位数
        int max = arr[0];//假设第一个数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大的数是几位
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素的对应位进行排序处理，第一次是个位，第二次是十位，第三次是百位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照这个桶的顺序(一维数组的下标以此取出数据，放入原来数组)
            int index = 0;
            //遍历每一个桶，并将桶中的数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶(第K个一维数组)，放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到原始数组arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementCounts[k]置为0 !!!
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第"+(i+1)+"轮，对个位数的排序处理arr =" + Arrays.toString(arr));
        }



        //============第1轮(针对每个元素的个位进行排序处理)============
        /*for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标以此取出数据，放入原来数组)
        int index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入到原数组
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶(第K个一维数组)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到原始数组arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第1轮处理后，需要将每个bucketElementCounts[k]置为0 !!!
            bucketElementCounts[k] = 0;
        }

        System.out.println("第1轮，对个位数的排序处理arr =" + Arrays.toString(arr));
        //第1轮，对个位数的排序处理arr =[542, 53, 3, 14, 24, 748]*/


        //============第2轮(针对每个元素的十位进行排序处理)============
        /*for (int j = 0; j < arr.length; j++) {
            //取出每个元素的十位的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标以此取出数据，放入原来数组)
        index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入到原数组
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶(第K个一维数组)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到原始数组arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第2轮处理后，需要将每个bucketElementCounts[k]置为0 !!!
            bucketElementCounts[k] = 0;
        }

        System.out.println("第2轮，对十位数的排序处理arr =" + Arrays.toString(arr));
        //第2轮，对十位数的排序处理arr =[542, 53, 3, 14, 24, 748]*/

        //============第3轮(针对每个元素的百位进行排序处理)============
        /*for (int j = 0; j < arr.length; j++) {
            //取出每个元素的百位的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标以此取出数据，放入原来数组)
        index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入到原数组
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶(第K个一维数组)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到原始数组arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第3轮处理后，需要将每个bucketElementCounts[k]置为0 !!!
            bucketElementCounts[k] = 0;
        }

        System.out.println("第3轮，对百位数的排序处理arr =" + Arrays.toString(arr));
        //第3轮，对百位数的排序处理arr =[542, 53, 3, 14, 24, 748]*/
    }
}
