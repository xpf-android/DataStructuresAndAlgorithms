package threaded;

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//默认null

    //说明：
    //1.如果leftType == 0 表示指向的是左子树，如果为1则表示指向前驱节点
    //2.如果rightType == 0 表示指向的丝毫右子树，如果为1则表示指向后继节点
    private int leftType;
    private int rightType;


    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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
