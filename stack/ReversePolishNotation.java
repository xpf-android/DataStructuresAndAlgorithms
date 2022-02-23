package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式
 * 中缀转前缀
 * (a+b)*c+d-(e+g)*h
 * 加括号 ((a+b)*c+d)-((e+g)*h)
 * 去括号 符号放在前面
 *       -((a+b)*c+d) ((e+g)*h)
 *       -+((a+b)*c)d*(e+g)h
 *       -+*(a+b)cd*+egh
 *       -+*+abcd*+egh
 * 中缀转后缀
 * 加括号 ((a+b)*c+d)-((e+g)*h)
 * 去括号 符号放在后面
 *       ((a+b)*c+d) ((e+g)*h)-
 *       (a+b)*cd+(e+g)h*-
 *       ab+c*d+eg+h*-
 */
public class ReversePolishNotation {
    public static void main(String[] args) {
        //先定义逆波兰表达式
        //(30+4)*5-6  => 3 4 + 5 * 6 -
        //4 * 5 -8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        //说明为了方便，逆波兰表达式的数字和符号使用空格隔开
//        String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1. 先将"3 4 + 5 * 6 - " => 放入到ArrayList中
        //2. 将ArrayList 传递给一个方法，遍历ArrayList 配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);

        int res = calculate(rpnList);
        System.out.printf("suffixExpression的运算结果为%d\n", res);

    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpress分隔
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String element : split) {
            list.add(element);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /**
     * (1)从左至右扫描，将3和4压入堆栈
     * (2)遇到+运算符，因此弹出4和3(4为栈顶元素，3为次顶元素)，计算出3+4的值，得7，再将7入栈
     * (3)将5入栈
     * (4)接下来是*运算符，因此弹出5和7，计算7*5=35，将35入栈
     * (5)将6入栈
     * (6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> list) {
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        //遍历list
        for (String item : list) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop处两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());//先pop出的数
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误...");
                }
                //把res入栈
                stack.push(""+res);
            }

        }
        //最后留在stack中的数是运算结果
        return Integer.parseInt(stack.pop());
    }
}
