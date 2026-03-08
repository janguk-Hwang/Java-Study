import java.io.*;
import java.util.*;

// 기본 부품은 다른 부품을 사용하여 조립될 수 없는 부품이다
// 중간 부품은 또 다른 중간 부품이나 기본 부품을 이용하여 만들어지는 부품이다
public class Main {
    static int n, m;
    static int[] indegree, cnt;
    static ArrayList<int[]>[] adj;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        indegree = new int[n + 1];
        cnt = new int[n + 1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            // x를 만드는 데에 y가 k개 필요하다
            adj[x].add(new int[]{y, k});
            indegree[y]++;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        cnt[n] = 1;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int[] next : adj[cur]){
                // y의 개수는 x의 개수 X k
                cnt[next[0]] += cnt[cur] * next[1];
                // 진입차수: 어떤 부품 i를 필요로 하는 상위 부품의 개수
                indegree[next[0]]--;
                if(indegree[next[0]] == 0) q.add(next[0]);
            }
        }
        for(int i=1; i<=n; i++){
            if(adj[i].isEmpty() && cnt[i] > 0) System.out.println(i + " " + cnt[i]);
        }
    }
}