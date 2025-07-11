import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] adj;
    static int k;
    static int[] visitSame;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        while(k-- > 0){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            visitSame = new int[v + 1];
            adj = new ArrayList[v + 1];
            for(int i=1; i<=v; i++) adj[i] = new ArrayList<>();
            for(int i=0; i<e; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a].add(b);
                adj[b].add(a);
            }
            boolean flag = true;
            for(int i=1; i<=v; i++){
                if(visitSame[i] == 0){
                    if(!func(i)){
                        flag = false;
                        break;
                    }
                }
            }
            sb.append(flag ? "YES\n" : "NO\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    public static boolean func(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visitSame[start] = 1;
        while(!q.isEmpty()){
            int now = q.poll();
            for(Integer next : adj[now]){
                // 방문되지 않은 곳은 반대 번호로 지정하고 큐에 삽입하여 또 인접한 곳은 반대 번호로 지정해 나감
                if(visitSame[next] == 0){
                    visitSame[next] = -visitSame[now];
                    q.offer(next);
                }
                if(visitSame[next] == visitSame[now]){
                    return false;
                }
            }
        }
        return true;
    }
}