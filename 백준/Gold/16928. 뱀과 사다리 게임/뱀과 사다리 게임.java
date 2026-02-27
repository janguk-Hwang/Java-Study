import java.io.*;
import java.util.*;

// matrix[i] = j  ---> i로 오면 j로 이동을 의미
public class Main {
    static int n, m;
    static int[] matrix = new int[101];
    static boolean[] visited = new boolean[101];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i=1; i<=100; i++) matrix[i] = i;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            matrix[x] = y;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            matrix[u] = v;
        }
        System.out.print(bfs());
    }

    static int bfs(){
        // 현재 위치, 주사위 횟수
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 0});
        visited[1] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == 100) return cur[1];
            for(int d=1; d<=6; d++){
                int nIdx = cur[0] + d;
                if(nIdx > 100) continue;
                nIdx = matrix[nIdx];
                if(!visited[nIdx]){
                    visited[nIdx] = true;
                    q.offer(new int[]{nIdx, cur[1] + 1});
                }
            }
        }
        return -1;
    }
}