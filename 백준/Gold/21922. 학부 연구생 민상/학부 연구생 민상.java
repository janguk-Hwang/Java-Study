import java.io.*;
import java.util.*;

// 물건의 모양에 따라 그대로 바람이 불 수도, 45도 꺾일 수도
// 물건 1, 2는 현재 방향에 따라 멈추거나 계속 진행할 수 있다.
// 물건 3, 4는 현재 방향에 따라 방향을 바꿔줘야 한다.
// 상 우 하 좌 순으로 0 1 2 3
public class Main {
    static boolean[][][] visited;
    static int n, m;
    static int[][] matrix;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] d3 = {1, 0, 3, 2};
    static int[] d4 = {3, 2, 1, 0};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        visited = new boolean[n][m][4];
        ArrayList<int[]> airconditional = new ArrayList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 9) airconditional.add(new int[]{i, j});
            }
        }
        for(int[] aircon : airconditional){
            for(int d=0; d<4; d++){
                int r = aircon[0];
                int c = aircon[1];
                int dir = d;
                while(true){
                    if(r < 0 || r >= n || c < 0 || c >= m) break;
                    if(visited[r][c][dir]) break;
                    visited[r][c][dir] = true;
                    int mat = matrix[r][c];
                    // 빈 공간이거나 에어컨이 있는 칸
                    if(mat == 0 || mat == 9){
                        r += dr[dir];
                        c += dc[dir];
                        continue;
                    }
                    if(mat == 1){
                        if(dir == 1 || dir == 3) break;
                        r += dr[dir];
                        c += dc[dir];
                        continue;
                    }
                    if(mat == 2){
                        if(dir == 0 || dir == 2) break;
                        r += dr[dir];
                        c += dc[dir];
                        continue;
                    }
                    if(mat == 3){
                        dir = d3[dir];
                        r += dr[dir];
                        c += dc[dir];
                        continue;
                    }
                    if(mat == 4){
                        dir = d4[dir];
                        r += dr[dir];
                        c += dc[dir];
                    }
                }
            }
        }
        int answer = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int d=0; d<4; d++){
                    if(visited[i][j][d]){
                        answer++;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}