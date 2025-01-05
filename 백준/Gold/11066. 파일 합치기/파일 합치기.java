import java.util.*;
import java.io.*;
import java.lang.Math;

public class Main {
    static int dp[][];
    static int file[];
    static int sum[];
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        //반복문을 돌면서 각 테스트케이스에 관하여 실행
        while (T>0) {
            k = Integer.parseInt(br.readLine());
            file = new int[k + 1];
            sum = new int[k + 1];
            String[] input = br.readLine().split(" ");
            for (int i=0; i<k; i++) {
                sum[0] = 0;
                file[i] = Integer.parseInt(input[i]);   //0 ~ k-1
                //sum[1]부터 sum[k]까지 // sum[k] => from file[1] to file[k]까지의 합
                sum[i+1] = sum[i]+file[i];
            }
            dpfunc();
            T--;
        }
    }

    public static void dpfunc() {
        dp = new int[k + 1][k + 1];
        //구간의 길이를 1부터 k까지
        for (int p=1; p<=k; p++) {
            //구간의 시작점과 끝점을 결정(i to i+p)
            for (int i=1; i+p<=k; i++) {
                int j = i+p;    //j = i + 구간의 길이, 즉 마지막 파일의 인덱스(i to j)
                dp[i][j] = Integer.MAX_VALUE;
                //분할 지점을 결정
                for (int x = i; x < j; x++) {   //x를 i부터 i+p까지 진행하면서 분할 지점을 결정
                    dp[i][j] = Math.min(dp[i][j], dp[i][x] + dp[x + 1][j] + sum[j] - sum[i - 1]);
                }
            }
        }
        System.out.println(dp[1][k]);   //dp[1][k] => 1부터 k까지 최소비용
    }
}