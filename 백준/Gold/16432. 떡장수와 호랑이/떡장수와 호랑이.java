import java.io.*;
import java.util.*;

// dfs, 날짜와 떡의 종류 관리
public class Main {
    static int n;
    static int[] arr;
    static boolean[][] visited;
    static ArrayList<Integer>[] list;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        arr = new int[n + 1];
        visited = new boolean[n + 1][10];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            list[i] = new ArrayList<>();
            int m = Integer.parseInt(st.nextToken());
            for(int j=0; j<m; j++) list[i].add(Integer.parseInt(st.nextToken()));
        }
        // 1일, 전날은 떡x
        if(!dfs(1, 0)){
            System.out.print(-1);
            return;
        }
        // 1일부터 n일까지의 떡 순서
        for(int i=1; i<=n; i++) sb.append(arr[i]).append('\n');
        System.out.print(sb);
    }

    public static boolean dfs(int depth, int pre){
        // 종료 조건
        if(depth > n) return true;
        // 이전에 실패(방문)한 상태라면 가지치기
        if(visited[depth][pre]) return false;
        // 가능한 선택
        for(int temp : list[depth]){
            if(temp == pre) continue;
            arr[depth] = temp;
            if(dfs(depth + 1, temp)) return true;
        }
        visited[depth][pre] = true;
        return false;
    }
}