package recursion;

/**
 * 八皇后
 */
public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        //测试一把
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法", count);
    }

    //放置第n个皇后

    private void check(int n) {
        if (n == max) {//n = 8，表示放第9个皇后，其实前8个皇后就已经放好了
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到第i列时，是否冲突
            if (judge(n)) {//不冲突
                //接着放第n+1个皇后，即开始递归
                check(n +1);
            }
            //如果冲突，继续执行array[n] = i;即将第n个皇后，放置在本行的后移的一个位置
        }
    }

    /**
     * 查看放置第n个皇后，就去检测该皇后
     * 是否和前面已经摆放的皇后冲突
     * @param n 第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //说明
            //1. array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n]-array[i]) 表示判断第n个皇后和第i个皇后是否在同一斜线
            //3. 判断是否在同一行，没有必要，n每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
