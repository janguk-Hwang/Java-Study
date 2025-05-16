import java.util.*;
import java.io.*;

public class Main {
    static int d, k;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i=1; i<=k; i++){
            for(int j=i; j<=k; j++){
                int[] dp = new int[d+1];
                dp[1] = i;
                dp[2] = j;
                for(int p=3; p<=d; p++){
                    dp[p] = dp[p-1] + dp[p-2];
                }
                if(dp[d] == k){
                    System.out.println(i + "\n" + j);
                    return;
                }
            }
        }
    }
}