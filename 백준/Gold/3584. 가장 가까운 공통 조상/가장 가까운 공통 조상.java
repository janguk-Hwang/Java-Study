import java.util.*;
import java.io.*;

public class Main {
    static int t;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            boolean[] visited = new boolean[n+1];
            int[] parent = new int[n+1];
            for(int i=0; i<n-1; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                parent[b] = a;
            }
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 루트 노드의 부모는 0이므로 종료
            while(x != 0){
                visited[x] = true;
                x = parent[x];
            }
            while(y != 0){
                if(visited[y]){
                    sb.append(y).append("\n");
                    break;
                }
                y = parent[y];
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }
}