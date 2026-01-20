import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        System.out.println(xor(b) ^ xor(a-1));
    }

    static public long xor(long num){
        int remainder = (int)(num % 4);
        if(remainder == 0) return num;
        if(remainder == 1) return 1;
        if(remainder == 2) return num + 1;
        return 0;
    }
}