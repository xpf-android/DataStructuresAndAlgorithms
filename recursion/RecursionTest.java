package recursion;

/**
 * 递归测试
 */
public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题，回顾递归调用机制
//        test(4);

        int res = factorial(3);
        System.out.println("res = " + res);

    }
    //打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        } //else { //添加else语句时 ，只会打印n=2
            System.out.println("n=" +n);
        //}
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1)*n;
        }
    }
}
