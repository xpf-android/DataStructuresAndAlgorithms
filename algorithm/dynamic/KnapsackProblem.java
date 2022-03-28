package algorithm;

/**
 * 动态规划
 * (0-1)背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w={1,4,3};
        int[] val = {1500,3000,2000};//物品的价值 这里val[i] 就是前面讲的v[i]
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组
        //v[i][j] 表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];
        //为了记录放入商品的情况，定义一个二维数组
        int[][] path = new int[n+1][m+1];

        //初始化第一行和第一列，这里在本程序中，可以不去处理，因为默认是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//将第一列设置为0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//将第一行设置为0;
        }

        //根据前面得到的公式来动态处理
        for (int i = 1; i < v.length; i++) {//不处理第一行i是从1开始的
            for (int j = 1; j < v[0].length; j++) {//不处理第一列j是从1开始的
                //公式
                //因为我们程序i是从1开始的，因此原来公式中的w[i] 修改为w[i-1]
                if (w[i-1] > j) {//当准备加入新增的商品的容量大于当前背包的容量，就直接使用上一单元格的装入策略
                    v[i][j] = v[i-1][j];
                } else {//当准备加入的新增的商品的容量小于等于当前背包的容量
                    //针对原公式说明
                    //v[v-1][j]:就是上一单元格的装入的最大价值
                    //v[i]表示当前商品的价值
                    //v[i-1][j-w[i]]: 装入i-1商品，到剩余空间j-w[i]的最大值
                    //因为i从1开始，因此原公式
                    //v[i][j] = Math.max(v[i-1][j], v[i] + v[i][j-w[i]])
                    //需要调整为
                    //v[i][j] = Math.max(v[i-1][j], val[i-1] +v[i-1][j-w[i-1]]);

                    //v[i][j] = Math.max(v[i-1][j], val[i-1] +v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况，我们不能简单的使用上面的公式，需要使用if-else来体现公式
                    if (v[i-1][j] < val[i-1] + v[i-1][j-w[i-1]]) {
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        //输出一下v,看看目前的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("=========");
        //输出最后放入的哪些商品
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j]+" ");
            }
            System.out.println();
        }

        //遍历path,这样输出会把所有的放入情况都得到，其实只需要最后的放入情况
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n",i);
                j -= w[i-1];
            }
            i--;
        }
    }
}
