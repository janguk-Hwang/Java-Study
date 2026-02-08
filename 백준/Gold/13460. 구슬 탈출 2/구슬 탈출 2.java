import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.util.*;

// 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
public class Main {
    static int n, m;
    static int redR, redC, blueR, blueC;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[][] matrix;
    static boolean[][][][] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        for(int i=0; i<n; i++){
            String line = br.readLine();
            for(int j=0; j<m; j++){
                char c = line.charAt(j);
                matrix[i][j] = c;
                if(c == 'R'){
                    redR = i; redC = j;
                    matrix[i][j] = '.';
                }
                else if(c == 'B'){
                    blueR = i; blueC = j;
                    matrix[i][j] = '.';
                }
            }
        }
        // 빨간 공과 파란 공 동시에 관리
        visited = new boolean[n][m][n][m];
        Queue<State> q = new LinkedList<>();
        visited[redR][redC][blueR][blueC] = true;
        q.add(new State(redR, redC, blueR, blueC, 0));
        while(!q.isEmpty()){
            State cur = q.poll();
            // 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.
            if(cur.depth == 10) continue;
            for(int d=0; d<4; d++){
                MoveResult redMoveResult = move(cur.readR, cur.redC, d);
                MoveResult blueMoveResult = move(cur.blueR, cur.blueC, d);
                // 파란 구슬이 빠지면 안 됨
                if(blueMoveResult.escape) continue;
                // 빨간 구슬만 빠진 경우
                if(redMoveResult.escape){
                    System.out.print(cur.depth + 1);
                    return;
                }
                // 두 구슬이 같은 칸으로 이동하려고 할 경우 먼저 온 구슬이 해당 칸으로, 이후에 온 구슬을 직전 칸으로
                if(redMoveResult.r == blueMoveResult.r && redMoveResult.c == blueMoveResult.c){
                    if(redMoveResult.dist > blueMoveResult.dist){
                        redMoveResult.r -= dr[d];
                        redMoveResult.c -= dc[d];
                    }
                    else{
                        blueMoveResult.r -= dr[d];
                        blueMoveResult.c -= dc[d];
                    }
                }
                if(!visited[redMoveResult.r][redMoveResult.c][blueMoveResult.r][blueMoveResult.c]){
                    visited[redMoveResult.r][redMoveResult.c][blueMoveResult.r][blueMoveResult.c] = true;
                    q.add(new State(redMoveResult.r, redMoveResult.c, blueMoveResult.r, blueMoveResult.c, cur.depth + 1));
                }
            }
        }
        System.out.print(-1);
    }

    // d방향으로 끝까지 굴리는 함수
    public static MoveResult move(int r, int c, int d){
        int dist = 0;
        while(true){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(matrix[nr][nc] == '#') return new MoveResult(r, c, dist, false);
            dist++;
            if(matrix[nr][nc] == 'O') return new MoveResult(nr, nc, dist, true);
            r = nr; c = nc;
        }
    }

    // 빨간 구슬, 파란 구슬 좌표, 이동 횟수
    static class State{
        int readR, redC, blueR, blueC, depth;
        public State(int readR, int redC, int blueR, int blueC, int depth){
            this.readR = readR; this.redC = redC; this.blueR = blueR; this.blueC = blueC; this.depth = depth;
        }
    }

    // 이동 후 좌표, 이동 거리, 탈출 여부
    static class MoveResult{
        int r, c, dist;
        boolean escape;
        public MoveResult(int r, int c, int dist, boolean escape){
            this.r = r; this.c = c; this.dist = dist; this.escape = escape;
        }
    }
}