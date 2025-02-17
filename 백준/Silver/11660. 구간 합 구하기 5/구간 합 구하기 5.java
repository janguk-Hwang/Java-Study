import java.io.*;
import java.util.*;

public class Main{
    static int N,M;
    static int[][] arr,dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str1 = br.readLine().split(" ");
        N = Integer.parseInt(str1[0]);
        M = Integer.parseInt(str1[1]);

        arr= new int[N+1][N+1];
        dp=new int[N+1][N+1];


        //arr 입력
        for(int i=1; i<=N; i++){
            String[] str = br.readLine().split(" ");
            for(int j=1; j<=N; j++){
                arr[i][j]=Integer.parseInt(str[j-1]);

            }
        }

        //누적 값(dp[i][j]는 0행0열부터 i행j열까지의 누적합을 구한 것)
        //미리 다 dp값들을 구해 놓음
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
            }
        }

        //M개의 테스트 케이스에 대해서 x1, y1, x2, y2 입력 및 각 결과
        for(int i=0; i<M; i++){
            String[] str2 = br.readLine().split(" ");
            int x1=Integer.parseInt(str2[0]);
            int y1=Integer.parseInt(str2[1]);
            int x2=Integer.parseInt(str2[2]);
            int y2=Integer.parseInt(str2[3]);
            
            //결과
            int result = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
            System.out.println(result);
        }
    }
}