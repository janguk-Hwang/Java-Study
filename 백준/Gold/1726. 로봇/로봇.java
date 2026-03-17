import java.io.*;
import java.util.*;

// (행, 열, 바라보는 방향)
// 동0 서1 남2 북3
public class Main {
    static int n, m;
    static int startR, startC, startD;
    static int endR, endC, endD;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] matrix;
    static boolean[][][] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 세로
        m = Integer.parseInt(st.nextToken());   // 가로
        matrix = new int[n][m];
        visited = new boolean[n][m][4];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;
        startD = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        endR = Integer.parseInt(st.nextToken()) - 1;
        endC = Integer.parseInt(st.nextToken()) - 1;
        endD = Integer.parseInt(st.nextToken()) - 1;
        System.out.print(bfs());
    }

    static int bfs(){
        Queue<State> q = new LinkedList<>();
        q.offer(new State(startR, startC, startD, 0));
        visited[startR][startC][startD] = true;
        while(!q.isEmpty()){
            State cur = q.poll();
            if(cur.r == endR && cur.c == endC && cur.dir == endD) return cur.cnt;
            // 명령 1
            for(int dist=1; dist<=3; dist++){
                int nr = cur.r + dr[cur.dir] * dist;
                int nc = cur.c + dc[cur.dir] * dist;
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) break;
                if(matrix[nr][nc] == 1) break;
                if(!visited[nr][nc][cur.dir]){
                    visited[nr][nc][cur.dir] = true;
                    q.add(new State(nr, nc, cur.dir, cur.cnt + 1));
                }
            }
            // 명령 2
            // 왼쪽 회전
            int nDir = turnL(cur.dir);
            if(!visited[cur.r][cur.c][nDir]){
                visited[cur.r][cur.c][nDir] = true;
                q.add(new State(cur.r, cur.c, nDir, cur.cnt + 1));
            }
            // 오른쪽 회전
            nDir = turnR(cur.dir);
            if(!visited[cur.r][cur.c][nDir]){
                visited[cur.r][cur.c][nDir] = true;
                q.add(new State(cur.r, cur.c, nDir, cur.cnt + 1));
            }
        }
        return -1;
    }

    static int turnL(int dir){
        if(dir == 0) return 3;
        if(dir == 3) return 1;
        if(dir == 1) return 2;
        return 0;
    }

    static int turnR(int dir){
        if(dir == 0) return 2;
        if(dir == 2) return 1;
        if(dir == 1) return 3;
        return 0;
    }

    static class State{
        int r, c, dir, cnt;
        State(int r, int c, int dir, int cnt){
            this.r = r; this.c = c; this.dir = dir; this.cnt = cnt;
        }
    }
}