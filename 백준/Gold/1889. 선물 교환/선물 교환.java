import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] indegree;
    static ArrayList<Integer>[] adj;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        indegree = new int[n+1];
        adj = new ArrayList[n+1];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());
            adj[i].add(temp1);
            adj[i].add(temp2);
            indegree[temp1]++;
            indegree[temp2]++;
        }

        boolean[] isInserted = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++){
            if(indegree[i] < 2){
                q.offer(i);
                isInserted[i] = true;
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();
            for(Integer next : adj[now]){
                indegree[next]--;
                if(indegree[next] < 2 && !isInserted[next]){
                    q.offer(next);
                    isInserted[next] = true;
                }
            }
        }
        int total = 0;
        for(int i=1; i<=n; i++){
            if(indegree[i] == 2){
                total++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(total);
        System.out.print(sb);
    }
}