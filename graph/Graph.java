package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图
 * 深度优先搜索/遍历
 * 广度优先搜索/遍历
 */
public class Graph {

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻结矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//记录某个节点是否被访问


    public static void main(String[] args) {
//        int n = 5;
//        String[] vertexs = {"A","B","C","D","E"};
        int n = 8;
        String[] vertexs = {"1","2","3","4","5","6","7","8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String vertexValue : vertexs) {
            graph.insertVertex(vertexValue);
        }
        //添加边
        /*//A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);*/

        //更新边的关系
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);

        //显示
        graph.showGraph();
        /**
         * [0, 1, 1, 0, 0]
         * [1, 0, 1, 1, 1]
         * [1, 1, 0, 0, 0]
         * [0, 1, 0, 0, 0]
         * [0, 1, 0, 0, 0]
         */

        //深度优先遍历
        System.out.println("深度优先遍历...");
        graph.dfs();//A->B->C->D->E->
        System.out.println();
        //广度优先遍历
        System.out.println("广度优先遍历...");
        graph.bfs();


    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }



    /**
     * 获取第一个邻接点的下标w
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点
     * @param v1 当前节点下标
     * @param v2 根据v1找到邻接的节点v2，但v2已被访问过
     * @return 返回v1下一个邻接点(没被访问过)
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {//表示v1,v2两个顶点邻接，右边
                return i;
            }
        }
        return -1;
    }

    /**
     * 对一个节点进行深度遍历
     * @param isVisited
     * @param i
     */
    public void dfs(boolean[] isVisited, int i) {
        //首先访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将节点设置为已访问
        isVisited[i] = true;
        //查找i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已被访问过
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 遍历所有节点，深度优先
     */
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的节点，进行dfs回溯
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 对一个节点进行广度优先遍历
     * @param isVisited 节点是否访问过的布尔数组
     * @param i 节点下标
     */
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队列的头节点对应下标
        int w;//邻节点w(对应下标)
        //队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();//addLast方法代替队列
        //访问节点，输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头节点下标
            u = (int) queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记为已访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //以u为前驱节点，找w后面的下一个邻接节点
                w = getNextNeighbor(u,w);//体现出广度优先
            }
        }
    }

    /**
     * 遍历所有的节点，进行广度优先搜索
     */
    private void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
    }




    //返回节点(顶点)的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回节点(下标)对应的数据
     * @param i
     * @return
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回边(两个顶点之间)的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }


    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 表示添加的第一个点的下标，即是第几个顶点
     * @param v2 表示添加的第二个点的下标，即是第几个顶点
     * @param weight 边的权重
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //显示对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }


}
