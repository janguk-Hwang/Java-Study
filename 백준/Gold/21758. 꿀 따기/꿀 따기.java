import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] honey;
    static int[] prefixSum;
    static int maxHoney = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        // 아래 벌통의 위치에 따른 세가지 경우에서 최대값을 구한다.
        // 1. 벌통이 중간
        // 2. 벌통이 왼쪽 끝
        // 3. 벌통이 오른쪽 끝
        n = Integer.parseInt(br.readLine());
        honey = new int[n];
        prefixSum = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            honey[i] = Integer.parseInt(st.nextToken());
        }

        prefixSum[0] = honey[0];
        for(int i=1; i<n; i++){
            prefixSum[i] = prefixSum[i - 1] + honey[i];
        }

        // 1. 벌이 양 끝, 벌통이 중간, i는 벌통의 위치
        for(int i=1; i<n-1; i++){
            int leftBee = prefixSum[i] - honey[0];
            int rightBee = prefixSum[n - 1] - prefixSum[i - 1] - honey[n - 1];
            maxHoney = Math.max(maxHoney, leftBee + rightBee);
        }

        // 2. 벌통이 왼쪽 끝, i는 왼쪽벌의 위치
        for(int i=1; i<n-1; i++){
            int leftBee = prefixSum[i - 1];
            int rightBee = prefixSum[n - 2] - honey[i];
            maxHoney = Math.max(maxHoney, leftBee + rightBee);
        }

        // 3. 벌통이 오른쪽 끝, i는 오른쪽벌의 위치
        for(int i=1; i<n-1; i++){
            int leftBee = prefixSum[n - 1] - honey[i] - honey[0];
            int rightBee = prefixSum[n - 1] - prefixSum[i];
            maxHoney = Math.max(maxHoney, leftBee + rightBee);
        }

        System.out.println(maxHoney);
    }
}