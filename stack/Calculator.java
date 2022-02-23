package stack;

public class Calculator {
    public static void main(String[] args) {
        //完成表达式的运算
        String expression = "7*2*2+2*6-2";
        //创建两个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operationStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int operation = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到插入保存到ch
        String keepNum = "";//用于拼接多位数
        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字符
//            ch = expression.substring(index,index + 1).charAt(0);
            ch = expression.charAt(index);
            //判断ch是什么，然后做相应的处理
            if (operationStack.isOperation(ch)) {//如果是操作符
                //判断当前的符号栈是否为空
                if (!operationStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或等于栈中的操作符，
                    //就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，将得到的结果入数栈，然后
                    //将当前的操作符入符号栈
                    if (operationStack.priority(ch) <= operationStack.priority(operationStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operation = operationStack.pop();
                        res = numStack.cal(num1, num2, operation);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operationStack.push(ch);
                    } else {
                        //如果当前操作符的优先级大于栈中的操作符，就直接入符号栈
                        operationStack.push(ch);
                    }
                } else {
                    //如果符号栈为空，直接入栈..
                    operationStack.push(ch);
                }
            } else {
                //如果是数，则直接入数栈,需要注意的是这种写法只适合个位数，比如12，会扫描两次，分别将
                //1 和 2 入栈
                //numStack.push(ch - 48); // "1 + 3"  '1' => 1  ASCII码 字符1 表示 十进制49
                //多位数分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为可能是多位数
                //2. 在处理数，需要向expression的表达式index后再看一位，如果是数就继续扫描，如果是符号，前面的数才入栈
                //3. 因此需要定义一个变量字符串，用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch是expression表达式的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则数字入栈
                    //注意是看后一位，不是index++
                    if (operationStack.isOperation(expression.charAt(index+1))) {
                        //如果后一位是运算符，说明多位数扫描结束，数字入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的!!!，keepNum清空
                        keepNum = "";
                    }
                }
            }
            // 让index + 1，并判断是否扫描到expression表达式最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完成，就顺序的从数栈和符号栈pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字(即运算结果)
            if (operationStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operation = operationStack.pop();
            res = numStack.cal(num1, num2, operation);
            numStack.push(res);//入栈
        }
        //将数栈的最后数，pop出，就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式%s = %d", expression, res2);
    }
}


//定义一个ArrayStack 表示栈
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就放在数组
    private int top = - 1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈顶
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1 ;
    }

    //获取栈顶的值，但不是出栈操作
    public int peek() {
        return stack[top];
    }

    //入栈 push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈已满，无法再添加数据...");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //先判断是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据..");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况(遍历)，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据...");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级由开发者来确定，使用数字表示
    //数字越大，则优先级就越高
    public int priority(int operation) {
        if (operation == '*' || operation == '/') {
            return 1;
        } else if (operation == '+' || operation == '-') {
            return 0;
        } else {
            return -1;//假定目前的表达式只有+,-,*,/
        }
    }

    //判断是不是一个运算符
    public boolean isOperation(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int operation) {
        int res = 0;// res 用于存放计算的结果
        switch (operation) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;//注意顺序
                break;
            case '/':
                res = num2 * num1;//注意顺序
                break;
            default:
                break;
        }
        return res;
    }

}

