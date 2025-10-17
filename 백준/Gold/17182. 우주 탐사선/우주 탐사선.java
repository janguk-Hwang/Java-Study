import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int INF = Integer.MAX_VALUE;
    static int[][] dist;
    static int n, k;
    static int rst = INF;
    static int[][] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        dist = new int[n][n];
        for(int i=0; i<n; i++) Arrays.fill(dist[i], INF);
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) dist[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int p=0; p<n; p++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(dist[i][p] != INF && dist[p][j] != INF){
                        if(dist[i][j] > dist[i][p] + dist[p][j]) dist[i][j] = dist[i][p] + dist[p][j];
                    }
                }
            }
        }
        visited[k] = true;
        backtracking(1, 0, k);
        System.out.print(rst);
    }

    static void backtracking(int depth, int sum, int idx){
        // 종료 조건
        if(depth == n){
            rst = Math.min(rst, sum);
            return;
        }
        // 가지치기(이미 넘은 건 건너뜀)
        if(sum >= rst) return;
        // 가능한 선택
        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                backtracking(depth + 1, sum + dist[idx][i], i);
                visited[i] = false;
            }
        }
    }
}