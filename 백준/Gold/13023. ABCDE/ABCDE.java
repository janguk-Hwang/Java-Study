import java.io.*;
import java.util.*;

// 깊이 4만큼 탐색(dfs)하여 경로가 존재하는지 확인 a - b - c - d - e
public class Main {
    static int n, m;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static boolean flag;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        for(int i=0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 양방향
            adj[a].add(b); adj[b].add(a);
        }
        for(int i=0; i<n; i++){
            visited = new boolean[n];
            visited[i] = true;
            dfs(i, 0);
            if(flag){
                System.out.print(1);
                System.exit(0);
            }
        }
        System.out.print(0);
    }

    static void dfs(int node, int depth){
        if(flag) return;
        if(depth == 4){
            flag = true;
            return;
        }
        for(int next : adj[node]){
            if(!visited[next]){
                visited[next] = true;
                dfs(next, depth + 1);
                visited[next] = false;
                if(flag) return;
            }
        }
    }
}