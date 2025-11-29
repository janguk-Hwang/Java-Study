import java.io.*;
import java.util.*;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static int n, m;
    static int[][] dist;
    static int[][] pre;
    static List<Integer> path;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n + 1][n + 1];
        pre = new int[n + 1][n + 1];
        for(int i=1; i<=n; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(dist[a][b] > c) {
                dist[a][b] = c;
                pre[a][b] = a;
            }
        }
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(dist[i][k] == INF || dist[k][j] == INF) continue;
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
        // i -> j 최소 비용 출력
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                sb.append(dist[i][j] == INF ? 0 : dist[i][j]);
                if(j < n) sb.append(" ");
            }
            sb.append("\n");
        }
        //
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(dist[i][j] == INF || i == j){
                    sb.append(0).append("\n");
                    continue;
                }
                path = backtracking(i, j);
                sb.append(path.size());
                for(int node : path) sb.append(" ").append(node);
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

    // 역추적해서 경로 반환
    static List<Integer> backtracking(int start, int end){
        List<Integer> route = new LinkedList<>();
        int cur = end;
        while(cur != start){
            route.add(cur);
            cur = pre[start][cur];
        }
        route.add(start);
        Collections.reverse(route);
        return route;
    }
}