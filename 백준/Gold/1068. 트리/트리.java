import java.io.*;
import java.util.*;

public class Main {
    static int n, root, x, cnt;
    static ArrayList<Integer>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        cnt = 0;
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n];
        for(int i=0; i<n; i++) adj[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        root = 0;
        for(int i=0; i<n; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp == -1){
                root = i;
                continue;
            }
            adj[temp].add(i);
        }
        x = Integer.parseInt(br.readLine());        // 제거할 노드 번호
        if(root == x){
            System.out.print(0);
            return;
        }
        dfs(root);
        System.out.print(cnt);
    }

    static void dfs(int node){
        boolean flag = false;
        for(int child : adj[node]){
            // 제거할 노드는 더 이상 탐색 x
            if(child == x) continue;
            dfs(child);
            flag = true;    // 리프 노드까지 이동하고 나서 리프노드이면 flag는 false로 유지, 리프 노드가 아니면 falg는 true
        }
        if(!flag) cnt++;
    }
}