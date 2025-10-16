import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int sum = 1;
        for(int i=0; i<3; i++) sum *= Integer.parseInt(br.readLine());
        char[] c = String.valueOf(sum).toCharArray();
        int[] arr = new int[10];
        for(int i=0; i<c.length; i++) arr[Integer.parseInt(String.valueOf(c[i]))]++;
        for(int i : arr) sb.append(i).append("\n");
        System.out.print(sb);
    }
}