import java.io.*;
import java.util.*;

// 전후 관계를 알면 된다.
// 최소 비용이 아니라 a -> b 간선이 존재하는지만 검사하면 된다.
// 플로이드 워셜은 모든 정점에서 모든 정점으로의 비용을 계산하는 것이므로 경로가 존재한다면 초기값이 아닐 것이다.
// 굳이 이 문제에서는 dist[ ]를 통해 비용을 계산하는 것이 아닌 존재 여부만 필요하기 때문에
// i -> k 간선이 있고 k -> j 간선이 있으면 i -> 간선이 있다고 표시하면 된다.
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n, k, s;
    static boolean[][] graph;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new boolean[n][n];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            graph[s][e] = true;     // s -> e : s가 e보다 먼저 발생했다.
        }
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(graph[i][k] && graph[k][j]){
                        graph[i][j] = true;
                    }
                }
            }
        }
        s = Integer.parseInt(br.readLine());
        for(int i=0; i<s; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            if(graph[start][end]) sb.append(-1).append("\n");
            else if(graph[end][start]) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}