import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //str1,2[]에 각각 문자를 잘라서 저장
        String[] str1 = br.readLine().split("");
        String[] str2 = br.readLine().split("");
        dp = new int[str1.length+1][str2.length+1];

        //2중 for문을 통해 str1과 str2를 비교
        for(int i=1; i<=str1.length;i++){
            for(int j=1; j<=str2.length;j++){
                //문자가 같으면
                if(str1[i-1].equals(str2[j-1])){
                    //dp[i][j] => i부터 j까지의 두 수열의 최장 공통 부분 수열의 길이
                    dp[i][j] = dp[i-1][j-1] + 1;    //같으면 LCS 값 증가
                }
                //문자가 같지 않은 경우 마지막에 증가되었던 dp[i][j]값으로 만듦
                else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        System.out.println(dp[str1.length][str2.length]);
    }
}
