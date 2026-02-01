import java.io.*;
import java.util.*;

// f(n) = f(n-1) + f(n-2)
public class Main {
    static long m;
    static long INF = Long.MAX_VALUE;
    static ArrayList<Long> list = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        m = Long.parseLong(br.readLine());
        list.add(0L);
        list.add(5L);
        list.add(13L);
        int n = 2;
        while(list.get(n) < m){
            n++;
            long next = list.get(n - 1) + list.get(n - 2) + 1;
            list.add(next);
        }
        char rst = func(n, m);
        if(rst == ' ') System.out.print("Messi Messi Gimossi");
        else System.out.print(rst);
    }

    static char func(int n, long m){
        if(n == 1) return "Messi".charAt((int) (m - 1));
        if(n == 2) return "Messi Gimossi".charAt((int) (m - 1));
        long leftLen = list.get(n - 1);
        if(m <= leftLen) return func(n - 1, m);
        if(m == leftLen + 1) return ' ';
        return func(n - 2, m - leftLen - 1);
    }
}