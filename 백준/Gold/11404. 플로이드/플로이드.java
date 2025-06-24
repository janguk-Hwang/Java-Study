import java.io.*;
import java.util.*;

// 플로이드 워셜
// 인접 행렬을 INF로 초기화하고, i == j는 자기 자신에서 자기 자신으로 이동이므로 0 (거쳐가는 간선 0개)
// 연결상태를 고려하여 인접 행렬 갱신 (거쳐가는 간선 1개)
// 1번 노드부터 n번 노드까지 모든 노드를 중간에 거쳐 가는 경우를 고려하여 거리 갱신 (거쳐가는 간선 2개)
// ex) 3번노드에서 6번 노드로 이동, 2번 노드를 거쳐가는 경우 -> dist[3][6] = Math.min(dist[3][6], dist[3][2] + dist[2][6]);
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int INF = Integer.MAX_VALUE;
    static int n, m;
    static int[][] dist;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            // 시작 도시와 도착 도시가 같은 경우는 없다. -> 3 3 5 이 불가능하다는 의미.
            // 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다. -> 3 4 5   3 4 2 이렇게 시작과 끝이 같지만 비용이 다른 경우가 있다.
            dist[r][c] = Math.min(dist[r][c], cost);
        }
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(dist[i][k] != INF && dist[k][j] != INF){
                        if(dist[i][j] > dist[i][k] + dist[k][j]){
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(dist[i][j] == INF) sb.append(0).append(" ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}