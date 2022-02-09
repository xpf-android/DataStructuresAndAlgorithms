package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("===双链表测试===");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建一个双链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        System.out.println("===修改测试===");
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();

        //删除
        System.out.println("===删除测试===");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
    }


}

class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空...");
            return;
        }
        //因为头节点，不能动，因此需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

    /**
     * 添加节点到双向链表的最后
     *
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode2 temp = head;
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
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    /**
     * 修改一个节点的数据内容
     * 可以看到双链表和单链表一样
     * 只是传入的节点类型不一样
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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
        } else {//没有找到
            System.out.printf("没有找到编号为%d的节点，不能改动\n", newHeroNode.no);

        }
    }


    /**
     * 删除节点
     * 对于双链表，可以直接找到要删除的这个节点
     * 找到后，自我删除即可
     *
     * @param no
     */
    public void delete(int no) {
        //判断当前链表是否为空
        if (head.next == null) {//空链表
            System.out.println("链表为空，无法删除...");
            return;
        }


        HeroNode2 temp = head.next;//辅助变量
        boolean flag = false;//标志是否找到待删除节点
        while (true) {
            if (temp == null) {//已经到链表的最后
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {//找到要删除的节点
            temp.prev.next = temp.next;
            //这里的代码有问题
            //如果是最后一个节点，就不需要下面这句，否则空指针异常
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
        } else {
            System.out.printf("要删除的节点%d不存在\n", no);
        }

    }
}

class HeroNode2 {
    public int no; //编号
    public String name;
    public String nickName;
    public HeroNode2 prev; //指向前一节点
    public HeroNode2 next; //指向后一节点

    //构造器
    public HeroNode2(int no, String name, String nickName) {
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
