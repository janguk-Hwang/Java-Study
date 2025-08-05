import java.io.*;
import java.util.*;

// 단방향, 두 마을을 왕복하는 경우도 사이클에 포함됨에 주의한다.
// 사이클은 두 정점을 왕복하는 경우(i -> j -> i),
// 3개 이상의 정점으로 이뤄진 사이클에서 (반)시계 방향으로 자기 자신으로 돌아오는 경우(i -> j -> k -> i)
// 두 경우의 공통점은 i에서 출발하여 i로 도착한다는 것이다.
// 그렇다면 두 경우 모두 [ i -> 어떤 정점 -> i ]로 일반화할 수 있다.
// 플로이드 워셜을 통해 모든 정점의 모든 정점간의 최소 거리를 구하고,
// 2중 for문으로 모든 정점에서 모든 정점을 거쳐 자신으로 오는 비용이 가장 작은 경우가 최소 사이클의 도로 길이의 합이다.
public class Main {
    static int INF = 40_000_001;
    static int min = INF;
    static int[][] dist;
    static int v, e;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        dist = new int[v + 1][v + 1];
        for(int i=0; i<=v; i++) Arrays.fill(dist[i], INF);
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[s][e] = c;
        }
        for(int k=1; k<=v; k++){
            for(int i=1; i<=v; i++){
                for(int j=1; j<=v; j++){
                    if(dist[i][k] != INF && dist[k][j] != INF){
                        if(dist[i][j] > dist[i][k] + dist[k][j]){
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
        for(int i=1; i<=v; i++){
            for(int j=1; j<=v; j++){
                min = Math.min(min, dist[i][j] + dist[j][i]);
            }
        }
        System.out.print((min != INF) ? min : -1);
    }
}