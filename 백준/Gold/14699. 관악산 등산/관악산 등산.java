import java.io.*;
import java.util.*;

// 쉼터에 도착하면 그 쉼터와 직접 연결된 더 높은 쉼터로 향하는 길들 중 하나를 골라서 그 길을 따라 이동한다. 만약 그런 길이 없다면 등산을 마친다.
public class Main {
    static int n, m;
    static int[] height;
    static ArrayList<Integer>[] adj;
    static int[] rst;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        height = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) height[i] = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(height[a] > height[b]) adj[a].add(b);
            else adj[b].add(a);
        }

        rst = new int[n + 1];       // i번 쉼터에서 출발했을 때 방문할 수 있는 최대 쉼터 수
        Arrays.fill(rst, 1);
        Integer[] order = new Integer[n];
        for(int i=0; i<n; i++) order[i] = i + 1;
        
        Arrays.sort(order, (a, b) -> Integer.compare(height[b], height[a])); // 내림차순으로 정렬
        for(int cur : order){
            for(int next : adj[cur]){
                // 기존 next에서 방문할 수 있는 최대 쉼터 수와 cur의 방문 가능 최대 쉼터 수 + 1 중에 큰 값을 선택
                rst[next] = Math.max(rst[next], rst[cur] + 1);
            }
        }
        for(int i=1; i<=n; i++) sb.append(rst[i]).append('\n');
        System.out.print(sb);
    }
}