package tree;

/**
 * 二叉树的前序、中序、后序遍历
 * 以及通过三种遍历方式查找(某个节点)
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

        /*//测试前序遍历
        System.out.println("前序遍历");
        binaryTree.preOrderTraverse();

        //测试中序遍历
        System.out.println("中序遍历");
        binaryTree.infixOrderTraverse();

        //测试后序遍历
        System.out.println("后序遍历");
        binaryTree.postOrderTraverse();

        //测试前序遍历查找
        System.out.println("前序遍历方式查找...");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name= %s\n", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄(节点)\n", 5);
        }

        //测试中序遍历查找
        System.out.println("中序遍历方式查找...");
        HeroNode resNode2 = binaryTree.infixOrderSearch(5);
        if (resNode2 != null) {
            System.out.printf("找到了，信息为 no=%d name= %s\n", resNode2.getNo(), resNode2.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄(节点)\n", 5);
        }


        //测试后序遍历查找
        System.out.println("后序遍历方式查找...");
        HeroNode resNode3 = binaryTree.postOrderSearch(5);
        if (resNode3 != null) {
            System.out.printf("找到了，信息为 no=%d name= %s\n", resNode3.getNo(), resNode3.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄(节点)\n", 5);
        }*/

        //测试删除节点
        System.out.println("删除前，前序遍历...");
        binaryTree.preOrderTraverse();
        binaryTree.delNode(5);
        System.out.println("删除后，前序遍历...");
        binaryTree.preOrderTraverse();
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

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root节点，立即判断root是不是就是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除...");
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

    /**
     * 前序遍历查找
     * @param no 要查找的编号
     * @return 如果找到就返回该编号对应的node，如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找");
        //判断当前节点是否为要查找的节点
        if (this.no == no) {
            return this;
        }
        //判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        //左递归前序查找，找到节点，则返回
        if (resNode != null) {//说明找到
            return resNode;
        }

        //判断当前节点的右子节点是否为空，如果不为空，则继续右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        //这里不需要进行非空判断，没有找到，返回的就是null
        return resNode;
    }


    /**
     * 中序遍历查找
     * @param no 要查找的编号
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        //判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        //如果找到则返回
        if (resNode != null) {
            return resNode;
        }

        //用打印日志次数判断查找次数，注意这行代码为什么不能像前序遍历查找那样
        //放在方法的最前面，因为前面左子树递归，放在前面(节点)非空判断，该行代码也会执行
        //但是不是进行查找(比较操作)，应该放在真正的(查找)比较操作(this.no == no)之前
        System.out.println("进入中序遍历查找");
        //如果没有找到，就和当前节点比较，如果当前节点是要查找的节点则返回
        if (this.no == no) {
            return this;
        }

        //否则继续向右递归查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }


    /**
     * 后序遍历查找
     * @param no 要查找的编号
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        //判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        //如果找到则返回
        if (resNode != null) {//说明在左子树找到
            return resNode;
        }


        //左子树没有找到，继续向右递归查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        //如果找到则返回
        if (resNode != null) {//说明在右子树找到
            return resNode;
        }

        System.out.println("进入后序遍历查找");
        //如果没有找到，就和当前节点比较，如果当前节点是要查找的节点则返回
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //递归删除节点
    //1.如果要删除的节点是叶子节点，则删除该节点
    //2.如果要删除的节点是非叶子节点，则删除孩子树
    public void delNode(int no) {
        //思路：
        //因为二叉树是单向的，所以是判断当前节点的子结点是否为需要删除的节点，而不能去判断当前节点是否为要删除节点
        //1.如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left = null,并且就返回(结束递归删除)
        //2.如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right = null,并且就返回(结束递归删除)
        //3.如果前面第1和2两步没有删除节点，那么就要向左子树进行递归删除
        //4.如果第3步也没有删除节点，则应当向右子树进行递归删除

        //1.如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left = null,并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        //2.如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right = null,并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        //3.向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }

        //4.向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}
