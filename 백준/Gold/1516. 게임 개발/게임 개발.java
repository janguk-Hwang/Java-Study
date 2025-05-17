import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static ArrayList<Integer>[] adj;
    static int[] time, indegree, result;
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        time = new int[n+1];
        indegree = new int[n+1];
        result = new int[n+1];
        adj = new ArrayList[n+1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            time[i] = Integer.parseInt(st.nextToken());
            while(true){
                int s = Integer.parseInt(st.nextToken());
                if(s == -1) break;
                adj[s].add(i);
                indegree[i]++;
            }
        }

        for(int i=1; i<=n; i++){
            result[i] = time[i];
            if(indegree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()){
            int now = q.poll();
            for(int next : adj[now]){
                result[next] = Math.max(result[next], result[now] + time[next]);
                indegree[next]--;
                if(indegree[next] == 0) q.offer(next);
            }
        }
        for(int i=1; i<=n; i++) sb.append(result[i]).append("\n");
        System.out.print(sb);
    }
}