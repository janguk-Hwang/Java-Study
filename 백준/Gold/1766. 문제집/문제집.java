import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer>[] adj;
    static int[] indegree;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        indegree = new int[n+1];
        adj = new ArrayList[n+1];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adj[s].add(e);
            indegree[e]++;
        }
        List<Integer> list = new ArrayList<>();
        // 가능한 쉬운 문제부터 풀어야 한다.
        for(int i=1; i<=n; i++) if(indegree[i] == 0) pq.offer(i);    // 낮은 번호 문제부터 q에 삽입
        while(!pq.isEmpty()){
            int now = pq.poll();
            list.add(now);
            for(int next : adj[now]){
                indegree[next]--;
                if(indegree[next] == 0) pq.offer(next);
            }
        }
        for(Integer i : list){
            sb.append(i).append(" ");
        }
        System.out.print(sb);
    }
}