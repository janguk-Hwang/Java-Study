import java.io.*;
import java.util.*;

// 직접 가는 경로 or 텔레포트를 타고 가는 경로
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n, m, t;
    static int[][] dist;
    static City[] cities;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        cities = new City[n + 1];
        dist = new int[n + 1][n + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cities[i] = new City(s, x, y);
        }
        m = Integer.parseInt(br.readLine());
        for(int i=1; i<=n; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i == j) continue;
                int distance = Math.abs(cities[i].x - cities[j].x) + Math.abs(cities[i].y - cities[j].y);
                dist[i][j] = distance;
                if(cities[i].s == 1 && cities[j].s == 1) dist[i][j] = Math.min(distance, t);
            }
        }
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(dist[i][k] == INF || dist[k][j] == INF) continue;
                    if(dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(dist[a][b]).append("\n");
        }
        System.out.print(sb);
    }

    static class City {
        int s; int x; int y;
        City(int s, int x, int y) {
            this.s = s;
            this.x = x;
            this.y = y;
        }
    }
}