import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] temp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        temp = new int[n][2];   // 0: t(작업에 걸리는 시간), 1: s(마감일)
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            temp[i][0] = Integer.parseInt(st.nextToken());
            temp[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(temp, (a, b) -> a[1] - b[1]);
        int cur = temp[n-1][1];
        for(int i=n-1; i>=0; i--){
            cur = Math.min(cur, temp[i][1]);
            cur -= temp[i][0];
        }
        System.out.print(cur < 0 ? -1 : cur);
    }
}