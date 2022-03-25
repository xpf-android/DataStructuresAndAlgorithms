package tree.avltree;



/**
 * AVL树(二叉平衡树)
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};//左低右高，验证左旋
        //int[] arr = {10,12,8,9,7,6};//左低右高，验证右旋
        int[] arr = {10,11,7,6,8,9};//验证双旋转
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //中序遍历
        avlTree.infixOrderTraver();

//        System.out.println("平衡处理前...");
        System.out.println("平衡处理后...");
        System.out.println("树(根节点)的高度=" +avlTree.getRoot().height());
        System.out.println("树的左子树的高度=" +avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度=" +avlTree.getRoot().rightHeight());
        System.out.println("根节点为" + avlTree.getRoot());
        System.out.println("根节点左节点" + avlTree.getRoot().left);
        System.out.println("根节点右节点" + avlTree.getRoot().right);
    }
}

//创建AVL树
class AVLTree {
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

    public Node getRoot() {
        return root;
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
    public void add(Node node) {
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

        //当添加完一个节点后，(右子树的高度-左子树的高度) > 1 左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对当前节点的右子树右旋转
                right.rightRotate();
                //再对当前节点左旋转
                leftRotate();
            } else {
                leftRotate();
            }

            //因为处理后已经平衡，不return，就会执行下面的右旋判断，一是没有意义，另外可能
            //导致问题
            return;//必须要!!!
        }

        //当添加完一个节点后，(左子树的高度-右子树的高度) > 1 右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左子树左旋转
                left.leftRotate();
                //再对当前节点右旋转
                rightRotate();
            } else {
                rightRotate();
            }
        }

    }

    //获取左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //获取右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }


    /**
     * 返回以当前节点为根节点的树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }


    //左旋转方法
    private void leftRotate() {
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新节点的右子树设置成当前节点右子树的左子树
        newNode.right = right.left;
        //把当前节点的值换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //把当前节点的左子树设置为新的节点
        left = newNode;
    }

    //右旋转方法
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }


    //查找要删除的节点

    /**
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
            } else if (value > this.value && this.right != null) {
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
