import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组 11*11
        //0：表示没有棋子，1表示黑子  2 表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr1) {
            for (int data: row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转为稀疏数组思路
        //1. 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j]!=0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][]  = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0; //count用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j]!=0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }





        /**
         * 将稀疏数组存入磁盘（文件）
         *
         */
        /*public static void sparseArrayToIo(int[][] sparseArray) throws Exception {
            File file = new File("sparseArray.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            for(int i =0; i < sparseArray.length; i++) {
                for(int j = 0; j < 3; j++) {
                    writer.write(sparseArray[i][j]);
                }
            }
            writer.flush();
            writer.close();
        }*/


        /**
         * 读文件获取稀疏数组(获取指定行数的稀疏数组)【不足】
         * 这里有个缺陷就是我不能动态的知道稀疏数组一共有几行，所以我选择传参的方式，这样其实是不太友好的
         * @return
         *
         */
        /*public static int[][] sparseArrayFromIo(int lineNums) throws Exception {

            FileReader reader = new FileReader("sparseArray.txt");
            int getNum = 0;
            int[][] sparseArray = new int[lineNums][3];
            for(int i = 0;i < lineNums;i++) {
                for (int j = 0; j < 3; j++) {
                    getNum = reader.read();
                    sparseArray[i][j] = getNum;
                }
            }
            return sparseArray;
        }*/

        //将稀疏数组---> 恢复成 原始的二维数组
        /**
         * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如chessArr2 = int
         * 2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
         *
         */

        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.在读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组即可
        for (int i =1; i < sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];

        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组~~");

        for (int[] row: chessArr2) {
            for (int data: row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }



    }
}
