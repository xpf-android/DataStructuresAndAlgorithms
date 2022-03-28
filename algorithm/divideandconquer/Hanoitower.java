package algorithm;

/**
 * 分治算法实现汉诺塔
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');

    }
    //汉诺塔的移动方法
    //使用分治算法

    public static void hanoiTower(int num, char a,char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else if (num >= 2) {
            // n>=2, 总是可以看作是两个盘，最下边一个盘1，上面所有盘2
            //1.先把最上面的所有盘A->B,移动过程会使用到c塔
            hanoiTower(num - 1,a,c,b);
            //2.把最下边的盘A->C
            System.out.println("第" + num + "个盘从 " +a+"->"+c);
            //3.把B塔的所有盘从B->C,移动过程使用到a塔
            hanoiTower(num-1,b,a,c);
        }
    }
}
