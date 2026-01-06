import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static long rst = -1;
    static char[][] matrix;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        for(int i=0; i<n; i++) matrix[i] = br.readLine().toCharArray();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int dr=-n+1; dr<=n-1; dr++){
                    for(int dc=-m+1; dc<=m-1; dc++){
                        // if(dr==0 && dc==0) continue;
                        move(i, j, dr, dc);
                    }
                }
            }
        }
        System.out.print(rst);
    }

    public static void move(int r, int c, int dr, int dc){
        long num = 0L;
        while(r >= 0 && r < n && c >= 0 && c < m){
            num = num * 10 + (matrix[r][c] - '0');
            if(isPow(num)) rst = Math.max(num, rst);
            r += dr; c += dc;
            if(dr == 0 && dc == 0) break;
        }
    }

    public static boolean isPow(long num){
        long temp = (long) Math.sqrt(num);
        return temp * temp == num;
    }
}