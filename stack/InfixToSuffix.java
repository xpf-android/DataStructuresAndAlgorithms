package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式转后缀表达式的实现
 */
public class InfixToSuffix {
    public static void main(String[] args) {
        //说明
        //1. 1+((2+3)*4)-5  => 1 2 3 + 4 * + 5 -
        //2. 因为直接对String进行操作不方便，所以先将"1+((2+3)*4)-5" => 转为中缀表达式对应的list
        //   即"1+((2+3)*4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list=" + infixExpressionList);//ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的list=" + suffixExpressionList);//ArrayList [1,2,3,+,4,*,+,5,-]
    }

    /**
     * 将中缀表达式转成对应的list
     *
     * @param s 中缀表达式字符串
     * @return 对应的list
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List,存放中缀表达式对应的内容
        List<String> list = new ArrayList<String>();
        int i = 0;//这是一个指针(索引),用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char ch;//每遍历到一个字符，就放入到ch
        do {
            //如果ch是一个非数字字符，需要加入到list
            if ((ch = s.charAt(i)) < 48 || (ch = s.charAt(i)) > 57) {
                list.add("" + ch);
                i++;//i需要后移
            } else {
                //如果ch是一个数字，需要考虑多位数
                str = "";//置空
                //'0'[48] -> '9'[57]
                while (i < s.length() && (ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57) {
                    str += ch;//拼接
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;//返回
    }

    //ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList [1,2,3,+,4,*,+,5,-]
    //将得到的中缀表达式对应的list =>转为后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> list) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面需要逆序输出
        //因此比较麻烦，这里就不用Stack<String> 直接使用List<String> s2
        //Stack<String> s2 = new Stack<String>();//存储中间结果的栈s2
        List<String> s2 = new ArrayList<String>();//存储中间结果的list2
        
        //遍历list
        for (String item : list) {
            //如果是一个数,加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号")",则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//!!!将 ( 弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入s1栈
                s1.push(item);
            }

        }
        //将s1剩余的运算符依次弹出并加入s2
        while(s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//注意因为是存放到list，因此按顺序输出就是对应的后缀表达式对应的list
    }
}

//编写一个类Operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1; // +
    private static int SUB = 1; // -
    private static int MUL = 2; // *
    private static int DIV = 2; // /

    //返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符...");
                break;
        }
        return result;
    }
}