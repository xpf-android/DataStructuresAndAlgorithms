package tree;

/**
 * 二叉树的前序、中序、后序遍历
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先要创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //先手动创建该二叉树，后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        binaryTree.preOrderTraverse();

        //测试
        System.out.println("中序遍历");
        binaryTree.infixOrderTraverse();

        //测试
        System.out.println("后序遍历");
        binaryTree.postOrderTraverse();

    }
}

//定义BinaryTree二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrderTraverse() {
        if (this.root != null) {
            this.root.preOrderTraverse();
        } else {
            System.out.println("二叉树为空，无法遍历...");
        }
    }

    //中序遍历
    public void infixOrderTraverse() {
        if (this.root != null) {
            this.root.infixOrderTraverse();
        } else {
            System.out.println("二叉树为空，无法遍历...");
        }
    }

    //后序遍历
    public void postOrderTraverse() {
        if (this.root != null) {
            this.root.postOrderTraverse();
        } else {
            System.out.println("二叉树为空，无法遍历...");
        }
    }
}

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//默认null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrderTraverse() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrderTraverse();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrderTraverse();
        }
    }

    //中序遍历
    public void infixOrderTraverse() {
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.infixOrderTraverse();
        }

        //输出父节点
        System.out.println(this);

        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.infixOrderTraverse();
        }
    }

    //后序遍历
    public void postOrderTraverse() {
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.postOrderTraverse();
        }



        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.postOrderTraverse();
        }

        //输出父节点
        System.out.println(this);
    }
}
