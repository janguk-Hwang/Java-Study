import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] prefixSum;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        prefixSum = new int[n + 1];
        for(int i=1; i<=n; i++) prefixSum[i] = prefixSum[i-1] + Integer.parseInt(br.readLine());
        int rst = Integer.MIN_VALUE;
        // 두 지점 선택(i, j)
        for(int i=1; i<=n; i++){
            for(int j=i; j<=n; j++){
                // 시계 방향 거리
                int clockwise = prefixSum[j-1] - prefixSum[i-1];
                // 반시계 방향 거리
                int counterClockwise = prefixSum[n] - clockwise;
                int dist = Math.abs(Math.min(clockwise, counterClockwise));
                rst = Math.max(rst, dist);
            }
        }
        System.out.print(rst);
    }
}