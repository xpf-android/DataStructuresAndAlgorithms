package tree;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
//        int[] arr = {4, 6, 8, 5, 9};
        int[] arr = {4, 6, 8, 5, 9,11,1,7,-3};
        heapSort(arr);

    }

    //堆排序
    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序");
        //分步完成
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次" + Arrays.toString(arr));//4,9,8,5,6
//
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第二次" + Arrays.toString(arr));//9,6,8,5,4

        //完成最终代码
        //将无序序列(数组)构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        //最大元素值到堆顶
        //i = arr.length / 2 -1 是最后一个非叶子节点的下标

        for (int i = arr.length / 2 -1; i >= 0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
        //将堆顶元素与末尾元素进行交换，将最大元素"沉"到数组末端
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换操作，知道整个序列有序
        for (int j = arr.length - 1; j>0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
        System.out.println("堆排序后数组为：" + Arrays.toString(arr));
    }

    //将一个数组(二叉树)，调整成一个大顶堆

    /**
     * 完成将以i对应的非叶子节点的数调整为大顶堆
     * 举例 int[] arr = {4,6,8,5,9}; => i=1 => adjustHeap => 得到{4,9，8,5,6}
     * 如果再次调用 adjustHeap 传入的是 i=0 => 得到{4,9,8,5,6} => {9,6,8,5,4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整，length是在逐步减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //k = i * 2 + 1  其中k表示i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {//说明子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//!!! i指向k,继续循环比较
            } else {
                break;
            }
            //当for循环结束后，已经将以i为父节点的树的最大值，放在了最顶(局部树)
            arr[i] = temp;
        }
    }
}
