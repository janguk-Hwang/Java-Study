import java.io.*;
import java.util.*;

public class Main {
    static int sum = Integer.MAX_VALUE;
    static StringTokenizer minus;
    static StringTokenizer plus;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        minus = new StringTokenizer(br.readLine(), "-");
        while(minus.hasMoreTokens()){
            int temp = 0;
            plus = new StringTokenizer(minus.nextToken(), "+");
            while(plus.hasMoreTokens()) temp += Integer.parseInt(plus.nextToken());
            if(sum == Integer.MAX_VALUE) sum = temp;
            else sum -= temp;
        }
        System.out.println(sum);
    }
}