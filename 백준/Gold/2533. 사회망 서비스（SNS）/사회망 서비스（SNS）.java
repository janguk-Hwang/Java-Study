import java.io.*;
import java.util.*;

// 얼리 아답터가 아닌 사람들은 자신의 "모든" 친구들이 얼리 아답터일 때만 이 아이디어를 받아들인다.
// 얼리 어답터의 수를 최소로 하여 모든 사람들이 아이디어를 받아들이게 하는 문제
// 초기에 얼리 어답터가 아닌데 아이디어를 받아들인다고 얼리 어답터가 되는 것은 아니다.
// 트리이므로 dfs로 탐색하면 됨
// 모든 사람이 아이디어를 수용하기 위하여 필요한 최소 얼리 어답터의 수는 어떻게 구해야 하나?
public class Main {
    static int n;
    static int[][] dp;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        visited = new boolean[n + 1];
        dp = new int[n + 1][2];     // dp[i][0] = i번째 친구가 얼리 어답터가 아닌 경우, dp[i][1] = i번째 친구가 얼리 어답터인 경우
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        for(int i=0; i<=n; i++){
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        int root = 1;
        dfs(root);
        System.out.print(Math.min(dp[root][0], dp[root][1]));
    }

    // 부모 노드가 얼리 어답터가 아니면 자식은 얼리 어답터가 되어야 한다.
    // 부모 자식 관계인 두 노드가 모두 얼리 어답터가 아니면 모두에게 아이디어를 전파할 수 없다.
    static void dfs(int node){
        visited[node] = true;   // 바로 방문처리
        // 자식 노드 순회
        for(int child : adj[node]){
            if(!visited[child]){
                dfs(child);     // 리프 노드까지 이동하고 dp에 누적
                // 부모가 얼리 어답터가 아니면 자식은 얼리 어답터여야 함.
                dp[node][0] += dp[child][1];
                // 부모가 얼리 어답터이면 자식이 얼리 어답터일 수도, 아닐 수도 있음
                // 둘 중에 더 작은 값을 부모에게 전달
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}