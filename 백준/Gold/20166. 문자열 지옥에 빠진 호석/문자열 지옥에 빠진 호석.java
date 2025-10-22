import java.io.*;
import java.util.*;

public class Main {
    static HashMap<String, Integer> map = new HashMap<>();
    static Queue<Element> q;
    static boolean[][] visited;
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
        for(int i=0; i<n; i++) for(int j=0; j<m; j++) bfs(i, j);
        for(int i=0; i<k; i++){
            String target = br.readLine();
            if(map.get(target) == null) sb.append(0).append("\n");
            else sb.append(map.get(target)).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static void bfs(int r, int c){
        q = new LinkedList<>();
        q.add(new Element(r, c, String.valueOf(matrix[r][c])));
        while(!q.isEmpty()){
            Element cur = q.poll();
            if(cur.str.length() > 5) continue;
            for(int d=0; d<8; d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                // 환형 처리
                nr = (nr + n) % n;
                nc = (nc + m) % m;
                String newStr = cur.str.concat(String.valueOf(matrix[nr][nc]));
                q.add(new Element(nr, nc, newStr));
                map.put(newStr, map.getOrDefault(newStr, 0) + 1);
            }
        }
    }

    static class Element{
        int r; int c; String str;
        Element(int r, int c, String str){
            this.r = r; this.c = c; this.str = str;
        }
    }
}