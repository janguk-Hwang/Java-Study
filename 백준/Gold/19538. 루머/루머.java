import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer>[] adj;
    static boolean[] isBelieve;
    static int[] rst, believeCnt;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        rst = new int[n + 1];
        Arrays.fill(rst, -1);
        isBelieve = new boolean[n + 1];
        believeCnt = new int[n + 1];
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            while(true){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 0) break;
                adj[i].add(temp);
            }
        }
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        bfs();
        for(int i=1; i<=n; i++) sb.append(rst[i]).append(" ");
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<m; i++){
            int temp = Integer.parseInt(st.nextToken());
            q.add(temp);
            rst[temp] = 0;
            isBelieve[temp] = true;
        }
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int cur = q.poll();
                for(int next : adj[cur]){
                    if(isBelieve[next]) continue;
                    believeCnt[next]++;
                    if(believeCnt[next] * 2 >= adj[next].size()){
                        isBelieve[next] = true;
                        rst[next] = rst[cur] + 1;
                        q.add(next);
                    }
                }
            }
        }
    }
}