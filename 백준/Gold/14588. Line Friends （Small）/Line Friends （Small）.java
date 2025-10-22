import java.io.*;
import java.util.*;

public class Main {
    static int n, q;
    static int INF = Integer.MAX_VALUE;
    static int[][] dist;
    static int[] l, r;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dist = new int[n][n];
        l = new int[n];
        r = new int[n];
        for(int i=0; i<n; i++) Arrays.fill(dist[i], INF);
        for(int i=0; i<n; i++) dist[i][i] = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            l[i] = Integer.parseInt(st.nextToken());
            r[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(isOverlap(l[i], r[i], l[j], r[j])){
                    dist[i][j] = dist[j][i] = 1;
                }
            }
        }
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(dist[i][k] != INF && dist[k][j] != INF){
                        if(dist[i][k] + dist[k][j] < dist[i][j]){
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
        q = Integer.parseInt(br.readLine());
        for(int i=0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if(dist[a][b] == INF) sb.append(-1).append("\n");
            else sb.append(dist[a][b]).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static boolean isOverlap(int l1, int r1, int l2, int r2) {
        // 겹치는 여부 반환
        return !(r1 < l2 || r2 < l1);
    }
}