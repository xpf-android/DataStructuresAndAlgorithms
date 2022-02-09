package linkedlist;

/**
 * 约瑟夫问题：
 * 设编号为1,2,...,n的n个人围坐一圈，约定编号为k(1<=k<=n)的人从1开始报数，
 * 数到m的那个人出列，他的下一位又从1开始报数，数到m的人又出列，以此类推，
 * 直到所有人出列为止，由此产生一个出队编号的序列。
 *
 * 提示：用一个不带头节点的循环链表来处理Joseph问题，先构成一个有n个节点的
 * 单循环链表，然后由k节点从1开始计数，记到m时，对应的节点从链表中删除，然后
 * 再被删除节点的下一节点又从1开始计数，知道最后一个节点从链表中删除。
 */
public class Joseph {
    public static void main(String[] args) {
        //环形链表测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);//添加小孩节点
        circleSingleLinkedList.showBoy();

        System.out.println("======");

        //测试小孩出圈顺序是否正确
        circleSingleLinkedList.countBoy(1,2,5);

    }
}

//创建一个单向环形链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);
    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int number) {
        //校验
        if (number < 1) {
            System.out.println("number值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用循环创建环形链表
        for (int i = 1; i <= number; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);//指向新的节点
                boy.setNext(first);//构成环
                curBoy = boy;//指针后移
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("===没有任何小孩===");
            return;
        }
        //因为first不能动，因此仍然需要使用一个辅助指针完成遍历
        Boy curBoy = first;
        while(true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            //说明遍历完毕
            if (curBoy.getNext() == first) break;
            curBoy = curBoy.getNext();//指针后移
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("输入有误，请重新输入...");
            return;
        }
        //创建要给辅助指针，帮助小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j ++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //这里是一个循环操作，指导圈中只有一个小孩节点
        while (true) {
            if (helper == first) {//说明圈中只有一个节点
                break;
            }
            //让first和helper同时移动countNum - 1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n", first.getNo());//最后first和helper指向同一节点
    }
}


//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点
    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}