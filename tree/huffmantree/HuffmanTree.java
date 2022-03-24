package tree.huffmantree;




import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);

    }

    //前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrderTraverse();
        } else {
            System.out.println("空树，无法遍历...");
        }
    }


    /**
     * 创建赫夫曼树的方法
     * @param arr 需要创建成赫夫曼树的数组
     * @return 创建好后的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr) {
        //遍历arr数组
        //将arr的每个元素构成一个Node
        //将Node放入到ArrayL中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }



//        System.out.println("nodes = " + nodes);

        while (nodes.size() >1) {

            //排序 从小到大
            Collections.sort(nodes);

            //取出根结点权值最小的两棵二叉树
            //1.取出权值最小的节点(二叉树)
            Node leftNode = nodes.get(0);
            //2.取出权值第二小的节点(二叉树)
            Node rightNode = nodes.get(1);
            //3.构建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4.从ArrayList中删除处理过的二叉树(最小和次最小的节点)
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5.将parent节点加入到nodes
            nodes.add(parent);
        }
        //返回赫夫曼树的root节点
        return nodes.get(0);

    }

}

//创建节点类
//为了让Node支持排序，实现Comparable接口
class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//指向左子节点
    Node right;//指向右子节点

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOrderTraverse() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrderTraverse();
        }
        if (this.right != null) {
            this.right.preOrderTraverse();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从大到小
        //return -(this.value - o.value);
        //表示从小到大排序
        return this.value - o.value;
    }
}