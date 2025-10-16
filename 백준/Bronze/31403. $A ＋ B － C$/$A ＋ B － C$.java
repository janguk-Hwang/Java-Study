import java.io.*;
import java.util.*;

public class Main {
    static int a, b, c;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        c = Integer.parseInt(br.readLine());
        sb.append((a + b - c)).append("\n");
        String s = String.valueOf(a).concat(String.valueOf(b));
        sb.append(Integer.parseInt(s) - c);
        System.out.print(sb);
    }
}