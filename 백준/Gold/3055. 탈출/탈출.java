import java.io.*;
import java.util.*;

// 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 비버의 굴은 'D'로, 고슴도치의 위치는 'S'
// 물과 고슴도치는 돌을 통과할 수 없다
// 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다
// 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다.
public class Main {
    static int R, C;
    static char[][] matrix;
    static boolean[][] visited;
    static Queue<int[]> waterQ = new LinkedList<>();
    static Queue<int[]> hedgehogQ = new LinkedList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());   // 행
        C = Integer.parseInt(st.nextToken());   // 열
        matrix = new char[R][C];
        visited = new boolean[R][C];
        for(int i=0; i<R; i++){
            matrix[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++){
                if(matrix[i][j] == 'S'){
                    hedgehogQ.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
                if(matrix[i][j] == '*'){
                    waterQ.add(new int[]{i, j});
                }
            }
        }
        int result = func();
        System.out.print(result == -1 ? "KAKTUS" : result);
    }

    public static int func(){
        while(!hedgehogQ.isEmpty()){
            // 물이 찰 예정인 곳에 고슴도치는 이동할 수 없으므로 물 먼저 이동
            int waterQSize = waterQ.size();
            for(int i=0; i<waterQSize; i++){
                int[] temp = waterQ.poll();
                for(int d=0; d<4; d++){
                    int nr = temp[0] + dr[d];
                    int nc = temp[1] + dc[d];
                    // 빈 칸이면 물이 채워짐
                    if(0 <= nr && nr < R && 0 <= nc && nc < C && matrix[nr][nc] == '.'){
                        matrix[nr][nc] = '*';
                        waterQ.add(new int[]{nr, nc});
                    }
                }
            }
            int hedgehogQSize = hedgehogQ.size();
            for(int i=0; i<hedgehogQSize; i++){
                int[] temp = hedgehogQ.poll();
                for(int d=0; d<4; d++){
                    int nr = temp[0] + dr[d];
                    int nc = temp[1] + dc[d];
                    if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
                    if(matrix[nr][nc] == 'D') return temp[2] + 1;
                    if(matrix[nr][nc] == '.'){
                        visited[nr][nc] = true;
                        hedgehogQ.add(new int[]{nr, nc, temp[2] + 1});
                    }
                }
            }
        }
        return -1;
    }
}