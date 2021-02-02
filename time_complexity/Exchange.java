package com.xpf.data.structures.and.algorithms.dn_lsn_01;

import org.junit.Test;

/**
 * 两个数的值互换
 *
 * 程序好坏=空间复杂度+时间复杂度+应用场景(重要)
 * ^(异或运算)：二进制数x为0101,y为1011, x^y=1110
 * 0101
 * 1011
 * ----
 * 1110
 * 只有在两个比较的位不同时其结果为1，否则结果为0;
 */

public class Exchange {
    @Test
    public void swapTest() {
        int a = 5;
        int b = 6;
        //方式一 可读性最好,变量t占用空间
//        int t = a;
//        a = b;
//        b = t;
//        System.out.print("a= "+a+"  b="+b+" ");

        //方式二
//        a = a + b;//a = 11 b = 6;
//        b = a - b;//a = 11 b = 5;
//        a = a - b;//a = 6  b = 5;
//        System.out.print("a= "+a+"  b="+b+" ");

        //方式三  性能最优(没有可读性)，应用场景：无人机，跑步机(要求空间占用少，性能高)
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.print("a= "+a+"  b="+b+" ");


    }

    @Test
    public void testArrayIndexOutOfBounds(){
        int i = 0;
        int arr[] = {0,1,2};
        for(; i<=3; i++){
            arr[i] = 0;
            System.out.println("hello world\n");
        }
    }
}
