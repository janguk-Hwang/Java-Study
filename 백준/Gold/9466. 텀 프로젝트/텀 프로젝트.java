import java.io.*;
import java.util.*;

public class Main {
    static int t, n;
    static int[] arr;
    static boolean[] visited;
    static boolean[] finished; // DFS 종료 여부
    static int cnt; // 팀에 속한 사람 수
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            cnt = 0;
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
            for(int i=1; i<=n; i++) if(!finished[i]) dfs(i);
            sb.append(n - cnt).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int start){
        visited[start] = true;
        int next = arr[start];
        if(!visited[next]) dfs(next);
        else if(!finished[next]){
            cnt++;
            // 해당 사이클의 요소들 한 번에 처리
            for(int i=next; i!=start; i=arr[i]) cnt++;
        }
        finished[start] = true;
    }
}