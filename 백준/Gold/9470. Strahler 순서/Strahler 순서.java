import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int[] indegree, order, max;
    static ArrayList<Integer>[] adj;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            adj = new ArrayList[m+1];
            for(int i=0; i<=m; i++) adj[i] = new ArrayList<>();
            indegree = new int[m+1];
            order = new int[m+1];
            max = new int[m+1];
            for(int i=0; i<p; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                adj[s].add(e);
                indegree[e]++;
            }
            Queue<Integer> q = new LinkedList<>();
            for(int i=1; i<=m; i++){
                if(indegree[i] == 0){
                    q.add(i);
                    order[i] = 1;
                    max[i] = 1;
                }
            }
            while(!q.isEmpty()){
                boolean flag = false;
                int now = q.poll();
                for(Integer next : adj[now]){
                    if(order[next] < order[now]){
                        order[next] = order[now];   // 들어오는 강의 순서보다 작으면 next의 순서는 now의 순서로 덮어쓴다.
                        max[next] = 1;      // 방금 가장 큰 순서의 강으로부터 들어왔으므로 가장 큰 값의 수는 1개
                        flag = true;
                    }
                    if(!flag && order[next] == order[now]) max[next]++;
                    indegree[next]--;
                    // 들어오는 모든 강에 대해 처리가 끝났으면 순서는 i+1
                    if(indegree[next] == 0){
                        if(max[next] >= 2) order[next]++;
                        q.add(next);
                    }
                }
            }
            sb.append(k).append(" ").append(order[m]).append("\n");
        }
        System.out.print(sb);
    }
}