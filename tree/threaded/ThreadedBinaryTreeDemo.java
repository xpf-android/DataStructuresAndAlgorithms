package threaded;

/**
 * 线索化二叉树测试类
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面要递归创建，这里简单处理事宜手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //以10号(no =10)为例，测试
        HeroNode leftNode = node5.getLeft();
        System.out.println("10号节点的前驱节点是：" +leftNode);//3
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的后继节点是：" +rightNode);//1

        //遍历中序线索化后的二叉树
        System.out.println("遍历中序线索化二叉树...");
        threadedBinaryTree.infixOrderThreadedBinaryTree();//8,3,10,1,14,6

    }

}
