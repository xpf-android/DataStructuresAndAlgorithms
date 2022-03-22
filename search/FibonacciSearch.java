package search;

import java.util.Arrays;

/**
 * 斐波那契(黄金分割法)查找算法
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println("index=" + fibSearch(arr,1234));


    }

    //因为后面我们mid=low+F(k-1)-1,需要使用到斐波那契数列，因袭需要先获取到一个斐波那契数列
    //非递归方式得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;

    }

    /**
     * 斐波那契查找算法
     *
     * @param a   数据
     * @param key 要查找的关键码(值)
     * @return 如果找到返回对应下标，否则返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;
        int f[] = fib();//获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]值可能大于a的长度，因此需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0代替
        //举例，如果a的长度比f[k]小，则用0代替
        //temp = {1, 8, 10, 89, 1000, 1234, 0, 0, 0}
        int[] temp = Arrays.copyOf(a, f[k]);
        //但实际上需求使用a数组最后数填充
        //temp = {1, 8, 10, 89, 1000, 1234, 0, 0} => {1, 8, 10, 89, 1000, 1234, 1234, 1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //使用while来循环处理，找到我们的数key
        while(low <= high) {//只要这个条件满足，就可以找
            mid = low + f[k-1]-1;
            if (key < temp[mid]) {//应该继续向数组的前面(左边)查找
                high = mid - 1;
                //说明：
                //1.全部元素= 前面的元素 + 后边元素
                //2.f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1]个元素，可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {//应该继续向数组的后面查找(右边)
                low = mid + 1;
                //为什么是 k -= 2
                //说明：
                //1.全部元素 = 前面元素 + 后边元素
                //2.f[k] = f[k-1] + f[k-2]
                //3.因为后面我们有f[k-2] 所以可以继续拆分 f[k-2] = f[k-3] + f[k-4]
                //4.即在f[k-2]的前面进行查找 k-=2
                //5.下次循环mid=f[k-1-2] - 1
                k -= 2;
            } else {//找到
                //需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }

            }

        }
        return -1;

    }

}
