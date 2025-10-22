import java.io.*;
import java.util.*;

public class Main {
    static HashMap<String, Integer> map = new HashMap<>();
    static int n, m, k;
    static char[][] matrix;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 행
        m = Integer.parseInt(st.nextToken());   // 열
        k = Integer.parseInt(st.nextToken());   // 문자열 수
        matrix = new char[n][m];
        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<m; j++) matrix[i][j] = s.charAt(j);
        }
        for(int i=0; i<n; i++) for(int j=0; j<m; j++) dfs(i, j, 1, String.valueOf(matrix[i][j]));
        for(int i=0; i<k; i++){
            String target = br.readLine();
            if(map.get(target) == null) sb.append(0).append("\n");
            else sb.append(map.get(target)).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static void dfs(int r, int c, int depth, String str){
        map.put(str, map.getOrDefault(str, 0) + 1);
        if(depth == 5) return;
        for(int d=0; d<8; d++){
            // 환형 처리
            int nr = (r + dr[d] + n) % n;
            int nc = (c + dc[d] + m) % m;
            dfs(nr, nc, depth + 1, str + matrix[nr][nc]);
        }
    }
}