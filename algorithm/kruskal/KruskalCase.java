package algorithm;

import androidx.annotation.NonNull;

import java.util.Arrays;

/**
 * kruskal解决公交问题
 */
public class KruskalCase {

    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
           /*A*/{   0,  12, INF, INF, INF,  16,  14},
           /*B*/{  12,   0,  10, INF, INF,   7, INF},
           /*C*/{ INF,  10,   0,   3,   5,   6, INF},
           /*D*/{ INF, INF,   3,   0,   4, INF, INF},
           /*E*/{ INF, INF,   5,   4,   0,   2,   8},
           /*F*/{  16,   7,   6, INF,   2,   0,   9},
           /*G*/{  14, INF, INF, INF,   8,   9,   0},
        };

        KruskalCase kruskalCase = new KruskalCase(vertex, matrix);
        kruskalCase.print();

        System.out.println(Arrays.toString(kruskalCase.getEdges()));
        kruskalCase.kruskal();

    }

    private int edgeNum;//边的个数
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    //构造
    public KruskalCase(char[] vertex, int[][] matrix) {
        //初始化顶点数和边的个数
        int vertexLen = vertex.length;
        //初始化顶点,复制拷贝的方式
        this.vertex = new char[vertexLen];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }
        //初始化边，使用复制拷贝方式
        this.matrix = new int[vertexLen][vertexLen];
        for (int i = 0; i < vertexLen; i++) {
            for (int j = 0; j < vertexLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边的条数
        for (int i = 0; i < vertexLen; i++) {
            for (int j = i + 1; j < vertexLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }


    public void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树"中，每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有边的集合，一共12条边
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + "共"+ edges.length);
        //按照边的权值大小进行排序
        sortEdges(edges);
        //遍历edges数组，将边添加到最小生成树中时，判断准备加入的边是否形成了回路，如果没有，就加入rets，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第1个顶点(起点)
            int p1 = getPosition(edges[i].start);//E 4
            //获取到第i条边的第2个顶点(起点)
            int p2 = getPosition(edges[i].end);//F 5
            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);// m = 4
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends,p2);// n = 5
            //是否构成回路
            if (m != n) {//没有构成回路
                ends[m] = n;//设置m在"已有最小生成树"中的终点<E,F> [0,0,0,0,5,0,0,0,0,0,0,0] 此时E的终点是F
                rets[index++] = edges[i];//有一条边加入到rets数组
            }
        }
        //统计并打印"最小生成树"，输出rets

        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
        /**
         * EData[<E, F>=2]
         * EData[<C, D>=3]
         * EData[<D, E>=4]
         * EData[<B, F>=7]
         * EData[<E, G>=8]
         * EData[<A, B>=12]
         */

    }

    //打印邻接矩阵
    public void print() {
        System.out.println("领接矩阵为：\n");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d\t",matrix[i][j]);
            }
            System.out.println();//换行
        }
    }

    /**
     * 对边进行排序处理(冒泡)
     * @param edges
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j+1].weight) {//交换
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     *
     * @param ch 顶点的值，比如'A','B'等
     * @return
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中边，放到EData[]数组中，后面需要遍历
     * 是通过matrix 邻接矩阵来获取
     * EData[] 形式[['A','B',12],['B','F',7],...]
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertex[i],vertex[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends 数组就是记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成
     * @param i 传入的顶点对应的下标
     * @return 返回的就是下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

//创建一个类EData，它的对象实例就表示一条边
class EData {
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString，便于输出边信息


    @Override
    public String toString() {
        return "EData[<"
                 + start +
                ", " + end +
                ">=" + weight +
                "]";
    }
}
