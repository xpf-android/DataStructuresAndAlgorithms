package algorithm;

/**
 * 二分查找非递归实现
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        //测试
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr, -67);
        System.out.println("index=" +index);
    }

    /**
     * 二分查找非递归实现
     * @param arr 待查找的数组，arr是升序
     * @param target 需要查找的数据
     * @return 返回对应的下标，没有找到就返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;//向左边查找
            } else if (arr[mid] < target) {
                left = mid + 1;//向右边查找
            }
        }
        return -1;
    }

}
