import java.io.*;
import java.util.*;

public class Main {
    static boolean flag;
    static String num;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        while(!(num = br.readLine()).equals("0")){
            flag = true;
            int left = 0; int right = num.length()-1;
            while(left <= right){
                if(num.charAt(left) != num.charAt(right)){
                    sb.append("no").append("\n");
                    flag = false;
                    break;
                }
                left++; right--;
            }
            if(flag) sb.append("yes").append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}