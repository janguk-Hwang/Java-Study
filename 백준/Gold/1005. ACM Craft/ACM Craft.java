import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            ArrayList<Integer>[] adj = new ArrayList[n+1];
            for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();
            int[] indegree = new int[n+1];
            int[] arr = new int[n+1];
            int[] result = new int[n+1];
            Queue<Integer> q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                result[i] = arr[i];
            }
            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a].add(b);
                indegree[b]++;
            }
            int goal = Integer.parseInt(br.readLine());

            for(int i=1; i<=n; i++){
                if(indegree[i] == 0) q.offer(i);
            }
            while(!q.isEmpty()){
                int now = q.poll();
                for(Integer i : adj[now]){
                    indegree[i]--;
                    result[i] = Math.max(result[i], result[now] + arr[i]);
                    if(indegree[i] == 0) q.offer(i);
                }
            }
            sb.append(result[goal]).append("\n");
        }
        System.out.print(sb);
    }
}