package com.xpf.data.structures.and.algorithms.dn_lsn_01;

import org.junit.Test;
/**
 * 蛮力法(排序)，也称为穷举法或枚举法
 * 是一种简单直接地解决问题的方法，常常直接基于问题的描述。
 * 所以，蛮力法也是最容易应用的方法。但是，用蛮力法设计的
 * 算法时间特性往往也是最低的，典型的指数时间算法一般都是
 * 通过蛮力搜索而得到的。
 * 当n--->无穷大大，时间复杂度最差(复杂)
 * 当数据量足够小(小于5)的时候，是所有算法中最快的
 *
 * 线性表的应用，顺序存储结构数组的应用，冒泡排序，插入排序，选择排序
 *
 */
public class Client {
    @Test
    public void cardsTest() {
        Cards[] array=new Cards[]{
                new Cards(3,2),
                new Cards(2,9),
                new Cards(1,7),
                new Cards(3,5),
                new Cards(4,3)
        };
//        Arrays.sort(array);//TODO：100行以上,在数据量较小时，不可取
        bubbleSort(array);
        for (Cards cards : array) {
            System.out.println(cards.toString());
        }
    }





    public void bubbleSort(Cards[] array) {
        int length = array.length;
        for (int i = length - 1; i > 0; i--) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[j + 1]) >0) {
                    Cards temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;//前面比后面大，才会变为false
                }
            }
            if (flag) {//说明没有执行循环代码，即有序(前面比后面小)
                break;
            }
        }
    }


    @Test
    public void sortTest() {
        int[] array = {1,5,6,2,8,4};
//        selectSort(array);
//        bubbleSort(array);
        insertionSort(array);
        for (int i : array) {
            System.out.print(i+" ");
        }
    }

    /**
     * 冒泡排序
     */
    public void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean flag = true;//提前退出冒泡循环的标志位
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {//交换
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;//表示有数据交换
                }
            }
            if (flag) {//flag为true，说明没有内层循环的if语句，也就是每一个都比后边的小，即有序。终止本层循环
                break;
            }
        }
    }

    /**
     * 插入排序
     * 我们将数组中的数据分为两个区间，已排序区间和未排序区间。
     * 初始已排序区间只有一个元素，就是数组的第一个元素。插入
     * 算法的核心思想是取未排序区间中的元素，在已排序区间中找
     * 到合适的插入位置将其插入，并保证已排序区间数据一直有序。
     * 重复这个过程，直到未排序区间中元素为空，算法结束
     * @param array 数组
     */
    public void insertionSort(int[] array) {
        int length = array.length;
        if (length <= 1) return;
        for (int i = 1; i < length; i++) {
            int value = array[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; j--) {
                if (array[j] > value) {
                    array[j+1] = array[j]; // 数据移动
                } else {
                    break;
                }
            }
            array[j+1] = value; // 插入数据
        }
    }


    /**
     * 选择排序
     * 每一轮和初始位置下标为i的元素进行比较，若比它小，则互换
     * @param array
     */
    public void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[index]) {
                    index = j;
                }
            }

            if (index != i) {//如果已经是最小的，就不需要交换位置
                int temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }
    }
}
