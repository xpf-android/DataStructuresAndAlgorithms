package tree.huffmanCode;

public class Test {
    public static void main(String[] args) {
        String strByte = "10101000";
        int value = Integer.parseInt(strByte, 2);
        System.out.println(value);
        byte value2 = (byte)Integer.parseInt(strByte, 2);
        System.out.println(value2);
        String string = Integer.toBinaryString(-1);
        System.out.println(string);
        String string2 = Integer.toBinaryString(1);
        System.out.println(string2);//1
        int temp = 1;
        temp |= 256;
        String string3 = Integer.toBinaryString(temp);
        System.out.println(string3);//100000001
        System.out.println(string3.substring(string3.length() - 8));//00000001
    }
}
