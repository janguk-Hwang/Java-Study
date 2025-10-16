import java.io.*;
import java.util.*;

public class Main {
    static int a, b, c;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        c = Integer.parseInt(br.readLine());
        System.out.println((a + b - c));
        String s = String.valueOf(a).concat(String.valueOf(b));
        System.out.print(Integer.parseInt(s) - c);
    }
}