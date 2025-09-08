import java.io.*;
import java.util.*;

// 왼쪽 위칸이 백, 흑인 두 경우로 나눠서 누적합을 이용해 풀이
public class Main {
    static int n, m, k;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[][] matrix;
    static int[][] preSum;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[n+1][m+1];
        preSum = new int[n+1][m+1];
        // 반전하기 위해 boolean 타입으로
        boolean color = false;      // false: 검은색, true: 흰색
        for(int i=1; i<=n; i++){
            String s = br.readLine();
            for(int j=1; j<=m; j++){
                char c = s.charAt(j-1);
                // 검은색이어야 하는데 흰색인 경우
                if(!color && c == 'W'){
                    matrix[i][j] = 1;   // 1인 곳은 체스판이 되기 위해 바뀌어야 되는 칸을 의미
                }
                // 흰색이어야 하는데 검은색인 경우
                else if(color && c == 'B'){
                    matrix[i][j] = 1;
                }
                // 매번 색 바꾸기
                color = !color;
            }
            // 열의 수가 짝수이면 바꾸기
            if(m % 2 == 0) color = !color;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                // 누적합은 누적합으로부터 계산
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + matrix[i][j];
            }
        }

        // k X k 크기이므로 k부터 n까지 반복
        for(int i=k; i<=n; i++){
            for(int j=k; j<=m; j++){
                int haveToReDraw = preSum[i][j] - preSum[i-k][j] - preSum[i][j-k] + preSum[i-k][j-k];
                min = Math.min(min, haveToReDraw);      // 왼쪽 위가 검은색인 경우의 최솟값
                max = Math.max(max, haveToReDraw);      // 왼쪽 위가 흰색인 경우의 최솟값을 구하기 위해 사용될 값
            }
        }
        System.out.print(Math.min(min, k*k - max));
    }
}