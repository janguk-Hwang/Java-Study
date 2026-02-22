import java.io.*;
import java.util.*;

// 경로의 수를 구하기 위해 dfs로 끝까지 가는 방식으로
// dfs 함수에서 유효한 경로만 계속 진행할 수 있도록 boolean 값을 반환하도록 한다. 이전 경로와
public class Main {
    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1};
    static int r, c;
    static char[][] matrix;
    static boolean[][] visited;
    static int cnt;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        matrix = new char[r][c];
        visited = new boolean[r][c];
        for(int i=0; i<r; i++){
            String s = br.readLine();
            for(int j=0; j<c; j++) matrix[i][j] = s.charAt(j);
        }
        // i행의 0열에서 출발
        for(int i=0; i<r; i++) dfs(i, 0);
        System.out.print(cnt);
    }

    static boolean dfs(int row, int col){
        // 종료 조건(유효한 경우에만 true를 최종적으로 반환)
        if(col == c-1){
            cnt++;
            return true;
        }
        for(int d=0; d<3; d++){
            int nr = row + dr[d];
            int nc = col + dc[d];
            if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
            if(matrix[nr][nc] == 'x' || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            if(dfs(nr, nc)) return true;
        }
        return false;
    }
}