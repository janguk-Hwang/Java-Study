import java.io.*;
import java.util.*;

public class Main {
    static Queue<Integer> q = new LinkedList<>();
    static int n, m;
    static ArrayList<Integer>[] adj;
    static int[] indegree;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        indegree = new int[n + 1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            indegree[b]++;
        }
        for(int i=1; i<=n; i++) if(indegree[i] == 0) q.offer(i);
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur).append(" ");
            for(int i : adj[cur]){
                indegree[i]--;
                if(indegree[i] == 0) q.offer(i);
            }
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}