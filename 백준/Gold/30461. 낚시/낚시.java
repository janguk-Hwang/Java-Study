import java.io.*;
import java.util.*;

// prefixSum에는 세로의 합만 저장, 대각선으로 긁어오기 때문에 rst[i-1][j-1]에 prefixSum[i][j]를 더하면 됨
public class Main {
    static int n, m, q;
    static int[][] prefixSum, rst;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        prefixSum = new int[n + 1][m + 1];
        rst = new int[n + 1][m + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) prefixSum[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                prefixSum[j][i] += prefixSum[j - 1][i];
            }
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                rst[i][j] = rst[i-1][j-1] + prefixSum[i][j];
            }
        }
        for(int i=0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            sb.append(rst[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }
}