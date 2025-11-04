import java.io.*;
import java.util.*;

public class Main {
    static int n, k, a, b;
    static boolean[] visited;
    static String[] arr;
    static int[] prev;
    static List<Integer>[] adj;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new String[n + 1];
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        prev = new int[n + 1];
        Arrays.fill(prev, -1);
        for(int i=1; i<=n; i++) arr[i] = br.readLine();
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                int cnt = 0;
                for(int p=0; p<k; p++){
                    if(arr[i].charAt(p) != arr[j].charAt(p)) cnt++;
                    if(cnt > 1) break;
                }
                if(cnt == 1){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        bfs();
        List<Integer> path = new ArrayList<>();
        for(int i=b; i!=-1; i=prev[i]) path.add(i);
        Collections.reverse(path);
        for(int i : path) sb.append(i).append(" ");
        System.out.print(sb);
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(a);
        visited = new boolean[n + 1];
        visited[a] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : adj[cur]){
                if(!visited[next]){
                    visited[next] = true;
                    prev[next] = cur;
                    if(next == b) return;
                    q.add(next);
                }
            }
        }
        System.out.print(-1);
        System.exit(0);
    }
}