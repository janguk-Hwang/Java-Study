import java.io.*;
import java.util.*;

public class Main {
    static int[] indegree, dp;
    static ArrayList<int[]>[] adj;
    static int n, m;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        indegree = new int[n+1];
        dp = new int[n+1];
        adj = new ArrayList[n+1];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new int[]{e, c});
            indegree[e]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++){
            if(indegree[i] == 0){
                q.add(i);
                dp[i] = 1;
            }
        }
        while(!q.isEmpty()){
            int now = q.poll();
            for(int[] next : adj[now]){
                int ne = next[0];
                int wait = next[1];
                if(wait >= 7) dp[ne] = Math.max(dp[ne], dp[now] + wait + 1);
                else dp[ne] = Math.max(dp[ne], dp[now] + wait);
                indegree[ne]--;
                if(indegree[ne] == 0) q.add(ne);
            }
        }
        int result = 0;
        for(Integer i : dp) result = Math.max(result, i);
        System.out.print(result);
    }
}