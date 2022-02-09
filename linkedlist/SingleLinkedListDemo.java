package linkedlist;

import androidx.annotation.NonNull;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用", "智多星");
        HeroNode hero4 = new HeroNode(4,"林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //添加节点
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        //显示
        singleLinkedList.list();*/

        System.out.println("===========");

        //根据排名顺序添加节点
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero3);
        //显示
        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2,"小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("===修改后的链表情况===");
        singleLinkedList.list();
        System.out.println("===删除测试===");
        //删除一个节点
        singleLinkedList.delete(1);
//        singleLinkedList.delete(4);
//        singleLinkedList.delete(3);
//        singleLinkedList.delete(2);
        singleLinkedList.list();
        //测试获取链表节点个数
        System.out.printf("链表有效节点个数为%d\n",getLength(singleLinkedList.getHead()));


        //测试逆序打印单链表
        System.out.println("===逆序打印单链表, 链表本身结构没变===");
        reversePrint(singleLinkedList.getHead());

    }


    /**
     * 获取链表节点的个数(如果是带头节点的链表，不统计头节点)
     * @param head
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //逆序打印单链表
    //思路
    //方式1：反转单链表，然后遍历打印，但是会破坏原有单链表结构，不推荐
    //方式2：借助栈先进后出的特性，可实现
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不需要打印
        }
        //创建一个栈，将链表各个节点压栈操作
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;//cur后移，这样就可以压入下一个节点
        }
        //将栈中元素进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());//stack的特点就是先进后出
        }
    }
}

//定义SingleLinkedList 管理我们的节点
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode node) {
        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next，指向新的节点
        temp.next = node;
    }

    //第二种方式添加英雄(节点)时，根据排名将英雄插入到指定位置
    //如果已经有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此需要通过一个辅助指针(变量)temp来帮助找到添加的位置
        //因为是单链表，所以找的temp 是位于添加位置的前一个节点，否则会插入不了
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移指针，遍历当前链表
        }
        if (flag) {//编号已存在，不能添加(插入)
            System.out.println("该排名英雄已存在，不能添加");
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改其它信息，即no编号不能改
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断，是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else  {//没有找到
            System.out.printf("没有找到编号为%d的节点，不能改动\n", newHeroNode.no);

        }
    }

    //删除节点
    //思路
    //1.head不能动，因此需要一个temp辅助节点找到待删除节点的前一个节点
    //2.说明在比较时，是temp.next.no 和 需要删除的节点的no比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点
        while(true) {
            if (temp.next == null) {//已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {//找到要删除的节点
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点%d不存在\n", no);
        }

    }

    //显示链表(遍历)
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空...");
            return;
        }
        //因为头节点，不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将节点后移
            temp = temp.next;
        }
    }

}

class HeroNode {


    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一节点
    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方便，重写toString方法
    //单引号'和\'效果一样  ""和''效果一样
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + "'" +
                ", nickName='" + nickName + "\'" +
                '}';
    }

}
