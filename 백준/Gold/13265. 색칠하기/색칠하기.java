import java.io.*;
import java.util.*;

public class Main {
    static int t, n, m;
    static ArrayList<Integer>[] adj;
    static int[] color;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            adj = new ArrayList[n + 1];
            color = new int[n + 1];
            for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                adj[x].add(y);
                adj[y].add(x);
            }
            boolean flag = true;
            for(int i=1; i<=n; i++){
                if(color[i] == 0){
                    if(!bfs(i)){
                        flag = false;
                        break;
                    }
                }
            }
            System.out.println(flag ? "possible" : "impossible");
        }
    }

    static boolean bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 1;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : adj[cur]){
                // 아직 색이 없으면 반대색으로
                if(color[next] == 0){
                    color[next] = -color[cur];
                    q.add(next);
                }
                // 같은 색이면 이분 그래프 불가능
                else if(color[next] == color[cur]) return false;
            }
        }
        return true;
    }
}