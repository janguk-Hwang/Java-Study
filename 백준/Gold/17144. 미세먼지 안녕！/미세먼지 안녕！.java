import java.io.*;
import java.util.*;

// 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.
public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] matrix;
    static int[][] temp;
    static int R, C, T;
    static int machineTop, machineBottom;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        matrix = new int[R][C];
        boolean flag = false;
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == -1 && !flag){
                    machineTop = i;
                    machineBottom = i+1;
                    flag = true;
                }
            }
        }
        for(int i=0; i<T; i++){
            spread();
            move();
        }
        int result = 0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(matrix[i][j] > 0) result += matrix[i][j];
            }
        }
        System.out.print(result);
    }

    public static void spread(){
        temp = new int[R][C];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(matrix[i][j] > 0){
                    int quantity = matrix[i][j] / 5;
                    int cnt = 0;
                    for(int d=0; d<4; d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr < 0 || nr >= R || nc < 0 || nc >= C || matrix[nr][nc] == -1) continue;
                        temp[nr][nc] += quantity;
                        cnt++;
                    }
                    matrix[i][j] -= quantity * cnt;
                }
            }
        }
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                matrix[i][j] += temp[i][j];
            }
        }
    }

    public static void move(){
        for(int i=machineTop-1; i>0; i--) matrix[i][0] = matrix[i - 1][0];
        for(int j=0; j<C-1; j++) matrix[0][j] = matrix[0][j + 1];
        for(int i=0; i<machineTop; i++) matrix[i][C - 1] = matrix[i + 1][C - 1];
        for(int j=C-1; j>1; j--) matrix[machineTop][j] = matrix[machineTop][j - 1];
        matrix[machineTop][1] = 0;

        for(int i=machineBottom+1; i<R-1; i++) matrix[i][0] = matrix[i + 1][0];
        for(int j=0; j<C-1; j++) matrix[R - 1][j] = matrix[R - 1][j + 1];
        for(int i=R-1; i>machineBottom; i--) matrix[i][C - 1] = matrix[i - 1][C - 1];
        for(int j=C-1; j>1; j--) matrix[machineBottom][j] = matrix[machineBottom][j - 1];
        matrix[machineBottom][1] = 0;

        matrix[machineTop][0] = -1;
        matrix[machineBottom][0] = -1;
    }
}