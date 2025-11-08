import java.io.*;
import java.util.*;

// 마리오 게임에서 적을 밟고 점프해 적을 제거하고 그 다음 칸으로 이동하는 것과 동일하다.
public class Main {
    static int t;
    static char[][] matrix;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int minPin, min;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            minPin = Integer.MAX_VALUE;
            min = Integer.MAX_VALUE;
            matrix = new char[5][9];
            for(int j=0; j<5; j++) matrix[j] = br.readLine().toCharArray();
            if(i != t-1) br.readLine();
            int pin = 0;
            for(int j=0; j<5; j++) for(int k=0; k<9; k++) if(matrix[j][k] == 'o') pin++;
            func(matrix, pin, 0);
            sb.append(minPin).append(" ").append(min).append("\n");
        }
        System.out.print(sb);
    }

    static void func(char[][] matrix, int pin, int move){
        boolean flag = false;
        for(int r=0; r<5; r++){
            for(int c=0; c<9; c++){
                if(matrix[r][c] == 'o'){
                    for(int d=0; d<4; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        int nnr = nr + dr[d];
                        int nnc = nc + dc[d];
                        if(nnr < 0 || nnr >= 5 || nnc < 0 || nnc >= 9) continue;
                        if(matrix[nr][nc] != 'o' || matrix[nnr][nnc] != '.') continue;
                        matrix[r][c] = '.'; matrix[nr][nc] = '.'; matrix[nnr][nnc] = 'o';
                        flag = true;
                        func(matrix, pin - 1, move + 1);
                        matrix[r][c] = 'o'; matrix[nr][nc] = 'o'; matrix[nnr][nnc] = '.';
                    }
                }
            }
        }
        // 이번 깊이에서 더 이상 게임을 진행 할 수 없었으면 종료
        if(!flag){
            if(pin < minPin){
                minPin = pin;
                min = move;
                return;
            }
            if(pin == minPin) min = Math.min(min, move);
        }
    }
}