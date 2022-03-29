package algorithm;

import java.util.Arrays;

/**
 * 暴力匹配
 */
public class BruteForce {
    public static void main(String[] args) {
        //测试暴力匹配算法
//        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
//        String str2 = "尚硅谷你尚硅你";

        String str1 = "aababb";
        String str2 = "ab";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);

        int[] indexArray = bf2(str1.toCharArray(), str1.length(), str2.toCharArray(), str2.length());
        System.out.println("indexArray=" + Arrays.toString(indexArray));
    }
    //暴力匹配算法实现
    //返回第一个匹配的起始下标位置
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;//i索引指向s1
        int j = 0;//j索引指向s2

        while(i < s1Len && j < s2Len) {//保证匹配时，不越界
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {//如果匹配失败
                //即s1[i] != s2[j], 令i = i - (j - 1), j = 0;
                i = i - (j - 1);
                j = 0;
            }
        }
        //判断匹配是否成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }



    /**
     * 返回第一个匹配的起始下标位置
     * @param a 主字符数组
     * @param n 主字符数组长度
     * @param b 模式字符数组
     * @param m 模式字符数组长度
     * @return
     */
    private static int bf(char a[], int n, char b[], int m) {
        if (n - m < 0) return -1;
        for (int i = 0; i < n - m; i++) {
            int j = 0;
            while (j < m) {
                if (a[i + j] != b[j]) {
                    break;
                }
                j++;
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

    //返回所有匹配的起始下标
    private static int[] bf2(char mainStr[], int n, char subStr[], int m) {
        int matchedPos[] = new int[n];
        int matchedNum = 0;
        for (int i = 0; i < n - m; i++) {
            int j = 0;
            while (j < m) {
                if (mainStr[i + j] != subStr[j]) {
                    break;
                }
                j++;
            }
            if (j == m) {
                matchedPos[matchedNum] = i;
                matchedNum++;
            }
        }
        return matchedPos;
    }
}
