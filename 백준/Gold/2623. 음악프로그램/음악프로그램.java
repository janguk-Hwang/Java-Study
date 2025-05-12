import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer>[] adj;
    static int[] indegree;
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        indegree = new int[n+1];
        adj = new ArrayList[n+1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int temp = Integer.parseInt(st.nextToken());
            int[] order = new int[temp];
            for(int j=0; j<temp; j++){
                order[j] = Integer.parseInt(st.nextToken());
            }
            for(int j=0; j<temp-1; j++){
                int s = order[j];
                int e = order[j+1];
                adj[s].add(e);
                indegree[e]++;
            }
        }
        for(int i=1; i<=n; i++){
            if(indegree[i] == 0) q.offer(i);
        }

        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()){
            int now = q.poll();
            result.add(now);
            for(int next : adj[now]){
                indegree[next]--;
                if(indegree[next] == 0) q.offer(next);
            }
        }

        if(result.size() != n) System.out.println(0);
        else for(Integer res : result){
            sb.append(res).append("\n");
        }
        System.out.print(sb);
    }
}