package binarysorttree;


/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {7, 9};
        int[] arr = {7, 3, 10, 12, 5, 1, 9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //中序遍历
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrderTraver();

        //测试删除叶子节点
//        System.out.println("删除叶子节点...");
//        binarySortTree.delNode(2);
//        binarySortTree.infixOrderTraver();

        //测试删除只有一棵子树的节点
        /*System.out.println("删除只有一颗子树的节点...");
        binarySortTree.delNode(7);
        binarySortTree.infixOrderTraver();*/

        //测试删除有两棵子树的节点
        System.out.println("删除有两棵子树的节点...");
        binarySortTree.delNode(7);
        binarySortTree.infixOrderTraver();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;
    //添加节点方法
    public void add(Node node) {
        if (root == null) {
            //如果root为空则直接让root指向note
            root = node;
        } else {
            root.add(node);
        }
    }

    //查找节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     *
     * @param node 要删除的节点(当作当前二叉排序树的根节点)
     * @return 返回的是以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //先要找到要删除的节点targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果找到该节点，且二叉排序树只有这一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左子节点，还是右子节点
                if (parent.left != null && parent.left.value == value) {//是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//是右子节点
                    parent.right = null;
                }
            } else if(targetNode.left != null && targetNode.right != null) {//删除有两棵子树的节点
                //获取以目标节点右子节点为根节点的二叉排序树的最小节点值，
                //删除那个节点，获取那个最小节点的值赋值给当前节点
                //删除当前节点，并没有断开该节点的左右指针，是删除以它右子节点为根节点的的二叉排序树
                //中值最小的节点，并把最小值赋值给当前节点，从而达到删除当前节点的效果
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//删除只有一个子节点的节点

                if (parent != null) {
                    //如果要删除的节点有左子节点
                    if (targetNode.left != null) {
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        }else {//targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {//如果要删除的节点有右子节点
                        //如果targetNode是parent的左子节点
                        if(parent.left.value == value){
                            parent.left = targetNode.right;
                        } else {//如果要删除的节点是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    }
                } else {
                    if (targetNode.left != null) {
                        root = targetNode.left;
                    }
                    if (targetNode.right != null) {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    //中序遍历
    public void infixOrderTraver(){
        if (root != null) {
            root.infixOrderTraverse();
        } else {
            System.out.println("二叉排序树为空树，无法遍历...");
        }
    }
}


//创建节点
class Node {
    int value;
    Node left;
    Node right;


    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点的方法
    public void add (Node node) {
        if (node == null) {
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的值(大小)关系
        if (node.value < this.value) {
            //如果当前节点左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {//添加节点的值大于等于当前节点的值
            //如果当前节点右子节点为null
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右子树添加
                this.right.add(node);
            }
        }
    }


    //查找要删除的节点

    /**
     *
     * @param value 待查找节点的值
     * @return 如果找到就返回该节点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {//找到就是该节点
            return this;
        } else if (value < this.value) {//查找节点的值小于当前节点的值
            //如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//查找节点的值大于当前节点的值
            //右子节点为空
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);

        }
    }


    //查找要删除节点的父节点

    /**
     *
     * @param value 要找到的节点的值
     * @return 返回的是要查找节点的父节点，如果没有就返回null
     */
    public Node searchParent(int value) {
        //如果当前节点就是要删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value > this.value && this.right != null){
                return this.right.searchParent(value);//向右子树递归查找
            } else {
                return null;//没有找到父节点
            }
        }
    }

    //中序遍历
    public void infixOrderTraverse() {
        if (this.left != null) {
            this.left.infixOrderTraverse();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrderTraverse();
        }
    }
}
