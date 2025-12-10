import java.io.*;
import java.util.*;

public class Main{
    static int n;
    static ArrayList<Long> list = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)throws Exception{
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<=9; i++) backtracking(i, 1);
        list.sort(null);
        if(n >= list.size()) System.out.print(-1);
        else System.out.print(list.get(n));
    }

    // (2, 1), (20, 2), (21, 2), (210, 3), (3, 1) ...
    static void backtracking(long num, int depth){
        list.add(num);
        if(depth >= 10) return;
        // 끝자리 수(1의 자리)
        int lastDigit = (int) num % 10;
        for(int i=0; i<lastDigit; i++) backtracking(num * 10 + i, depth + 1);
    }
}