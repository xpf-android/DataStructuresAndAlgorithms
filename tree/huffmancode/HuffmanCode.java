package tree.huffmanCode;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 哈夫曼编码(压缩)
 */
public class HuffmanCode {
    public static void main(String[] args) {

        //压缩文件测试
        /*String srcFile = "d://src.png";
        String dstFile = "d://dst.zip";
        zipFile(srcFile,dstFile);
        System.out.println("文件压缩完成...");*/

        //解压文件测试
        String zipFile = "d://dst.zip";
        String dstFile = "d://src2.png";
        unZipFile(zipFile,dstFile);
        System.out.println("解压完成...");

        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);//40个字节(byte)

        //分步推导便于理解
        *//*List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes = " + nodes);

        //测试创建的二叉树
        System.out.println("哈夫曼树...");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历哈夫曼树");
        preOrderTraverse(huffmanTreeRoot);

        //测试是否生成了哈夫曼编码
//        getCodes(huffmanTreeRoot,"",stringBuilder);
        //使用重载的方法
        getCodes(huffmanTreeRoot);
        System.out.println("生成的哈夫曼编码" + huffmanCodes);

        //测试压缩过后的字节数组
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));//17个字节*//*

        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("huffmanCodesBytes=" + Arrays.toString(huffmanCodesBytes));//17个字节
        //huffmanCodesBytes=[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]

        //压缩率为(40-17)/40=57.5%,那么问题来了，拿到哈夫曼编码(压缩处理后)的数据(字节数组),如何解码(解压)还原成对应的字符串呢？

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println("解码后的字符串=" + new String(sourceBytes));*/
    }

    //编写方法，将一个文件进行压缩

    /**
     *
     * @param srcFile 待压缩文件的全路径
     * @param dstFile 压缩后的文件存放目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建文件的输入流
        FileInputStream is = null;
        //创建文件的输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把哈夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里以对象流的方式写入哈夫曼编码，是为了以后恢复源文件时使用
            //一定要把哈夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    //编写一个方法对压缩文件解压

    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到路径下
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取哈夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将byte数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到dstFile文件
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //数据的解压
    //思路：
    //1.将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //  重新转成哈夫曼编码对应的二进制的字符串 "1010100010111..."
    //2.哈夫曼编码对应的二进制的字符串"1010100010111..." => 对照 哈夫曼编码 => "i like like like java do you like a java"

    //编写一个方法，完成对压缩数据的解码

    /**
     *
     * @param huffmanCodes 哈夫曼编码表map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数据
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制的字符串，形式1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        System.out.println("哈夫曼字节数组对应的二进制字符串=" + stringBuilder.toString());
        //把字符串按照指定的哈夫曼编码进行解码
        //把哈夫曼编码进行调换，因为a(97)->100 100->a(97)
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建集合存放byte
        List<Byte> list = new ArrayList<>();
        //i可以理解为索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //取出一个"1" "0"
                String key = stringBuilder.substring(i,i+count);//i不动，让count移动，直到匹配一个字符
                b = map.get(key);

                if (b == null) {//说明没有匹配到
                    count++;
                } else {//说明匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//移动一个字符(对应0和1的总数)
        }
        //当for循环结束后，list中就存放了所有的字符"i like like like java do you like a java"
        //把list中的数据放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }

        return b;
    }


    /**
     * 将一个byte转成一个二进制的字符串
     * @Param flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false，表示不补，如果是最后一个字节不需要补高位
     * @param b 传入的byte
     *
     * @return 是该b对应的二进制的字符串(注意是按补码)
     */
    private static String byteToBitString(boolean flag,byte b) {
        //使用变量保存
        int temp = b;//将b转成int
        //如果是正数还需要补高位
        if (flag) {
            temp |= 256;//按位或256 1 0000 0000 | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
//        System.out.println("str=" +str);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }



    //将前面的分步操作封装起来，便于调用

    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过哈夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的哈夫曼编码(根据哈夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的哈夫曼编码，压缩得到(压缩后)哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes,huffmanCodes);
        return huffmanCodeBytes;
    }



    //编写一个方法，将字符串对应的byte[]数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]

    /**
     *
     * @param bytes 原始的字符串对应的byte[]
     * @param huffmanCodes huffmanCodes 生成的哈夫曼编码map
     * @return 返回哈夫曼编码处理后的byte[]
     * 举例：String content = "i like like like java do you like a java" => byte[] contentBytes = content.getBytes();
     * 返回的是字符串 "101010011011110111101001101101111...."对应的byte[] huffmanCodeBytes 即8位对应一个byte，放入到huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101001(补码)=> byte [推导  10101000 - 1=> 10100111(反码) => 11011000(原码) = -88]
     * 即huffmanCodeBytes[] 中第一个元素是-88
     */
    private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes) {
        //1.利用huffmanCode将bytes转成哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        System.out.println("测试stringBuilder =" + stringBuilder);

        //将"101010001011111..."转成byte[]
        //统计返回 byte[] huffmanCodeBytes 长度
        //一句话 int len = (stringBuilder.length + 7) / 8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i< stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {//最后不够8位
                strByte = stringBuilder.substring(i);//从i截取到最后
            } else {
                strByte = stringBuilder.substring(i,i+8);
            }
            //将strByte转成一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //生成哈夫曼树对应的哈夫曼编码
    //思路：
    //1.将哈夫曼编标表存放在Map<Byte,String> 形式
    //  32->01 97(a的ASCII码)->100 100->11000等等
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2.在生成哈夫曼编码表时，需要去拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了测试方便，重载getCodes 少传参数
    private static Map<Byte,String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }


    /**
     * 将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入到huffmanCodes集合
     * @param node 传入的节点
     * @param code 路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到StringBuilder2
        stringBuilder2.append(code);
        if (node != null) {//如果node==null不处理
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {//非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1",stringBuilder2);
            } else {//说明是一个叶子节点
                //就表示找到某个叶子节点的最后
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }


    //前序遍历的方法
    private static void preOrderTraverse(Node root) {
        if (root != null) {
            root.preOrderTraverse();
        } else {
            System.out.println("空树,自己去体会...");
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回的就是List形式 nodes = [Node{data=32, weight=9}, Node{data=97, weight=5}...}]
     */
    private static List<Node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        /**
         * 我们知道在HashMap中，如果key相同就会被覆盖，那IdentityHashMap是怎么实现这个功能的呢？
         * java jdk源码中，IdentityHashMap类上写了100来行注释的代码，如果用一句话来总结的话：
         * IdentityhashMap类利用哈希表实现Map接口，比较键（和值）时使用引用相等性代替对象相等性，
         * 也就是说key（value）比较的时候只比较两个key是否引用同一个对象，比较的是对象的地址；
         */
        //遍历bytes 统计 每一个byte出现的次数->map[key,value]，key相同，value会被覆盖
        Map<Byte, Integer> counts = new HashMap<>();
        //循环统计每个字符权值(出现次数)
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转成一个Node对象，并加入到nodes集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过List，创建对应的哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出最小的二叉树(节点)
            Node leftNode = nodes.get(0);
            //取出次最小的二叉树(节点)
            Node rightNode = nodes.get(1);
            //创建一棵新的二叉树，它的根结点 没有date，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将已经处理的两棵二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes
            nodes.add(parent);
        }
        //nodes最后的节点，就是哈夫曼树的根节点
        return nodes.get(0);
    }
}

//创建Node,带数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据(字符)本身，比如'a'=>97(ASCII码) ' '=>32
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte date, int weight) {
        this.data = date;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //表示比较时从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrderTraverse() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrderTraverse();
        }
        if (this.right != null) {
            this.right.preOrderTraverse();
        }
    }
}
