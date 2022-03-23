package tree;

/**
 * 顺序存储二叉树
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
//        arrayBinaryTree.preOrderTraverse(0);
        arrayBinaryTree.infixOrderTraverse(0);
//        arrayBinaryTree.postOrderTraverse(0);

    }
}

//编写一个ArrayBinaryTree,实现顺序存储二叉树
class ArrayBinaryTree {
    private int[] arr;//存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

//    //重载
//    public void preOrderTraverse() {
//        this.preOrderTraverse(0);
//    }

    /**
     * 实现顺序存储二叉树的前序遍历
     * @param index 数组元素的下标
     */
    public void preOrderTraverse(int index) {
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的顺序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左遍历
        if ((index * 2 + 1) < arr.length) {
            preOrderTraverse(index * 2 + 1);
        }
        //向右遍历
        if ((index * 2 + 2) < arr.length) {
            preOrderTraverse(index * 2 + 2);
        }
    }


    /**
     * 实现顺序存储二叉树的中序遍历
     * @param index 数组元素的下标
     */
    public void infixOrderTraverse(int index) {
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的顺序遍历");
        }

        //向左遍历
        if ((index * 2 + 1) < arr.length) {
            infixOrderTraverse(index * 2 + 1);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向右遍历
        if ((index * 2 + 2) < arr.length) {
            infixOrderTraverse(index * 2 + 2);
        }
    }


    /**
     * 实现顺序存储二叉树的后序遍历
     * @param index 数组元素的下标
     */
    public void postOrderTraverse(int index) {
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的顺序遍历");
        }

        //向左遍历
        if ((index * 2 + 1) < arr.length) {
            postOrderTraverse(index * 2 + 1);
        }
        //向右遍历
        if ((index * 2 + 2) < arr.length) {
            postOrderTraverse(index * 2 + 2);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
    }
}