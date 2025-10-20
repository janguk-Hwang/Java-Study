import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int g = gcd(a, b);
        System.out.print(g + "\n" + a*b/g);
    }

    static int gcd(int a, int b){
        int temp;
        while(b != 0){
            temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}