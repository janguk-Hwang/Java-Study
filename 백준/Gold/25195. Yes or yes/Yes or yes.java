import java.io.*;
import java.util.*;

public class Main {
    static int n, m, s;
    static List<Integer>[] adj;
    static boolean[] fanPos;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
        }
        s = Integer.parseInt(br.readLine());
        fanPos = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<s; i++){
            int node = Integer.parseInt(st.nextToken());
            fanPos[node] = true;
        }
        if(fanPos[1]){
            System.out.println("Yes");
            return;
        }
        System.out.print(dfs(1, false) ? "Yes" : "yes");
    }

    static boolean dfs(int node, boolean isMeetFan){
        if(fanPos[node]) isMeetFan = true;
        if(adj[node].isEmpty()) return isMeetFan;
        for(int next : adj[node]) if(!dfs(next, isMeetFan)) return false;
        return true;
    }
}