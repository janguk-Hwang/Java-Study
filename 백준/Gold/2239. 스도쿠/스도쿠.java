import java.io.*;
import java.util.*;

// 하다 만 스도쿠 퍼즐이 주어졌을 때, 마저 끝내는 프로그램을 작성하시오.
public class Main {
    static int[][] matrix = new int[9][9];
    static boolean completed;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        for(int i=0; i<9; i++){
            String str = br.readLine();
            for(int j=0; j<9; j++){
                matrix[i][j] = str.charAt(j) - '0';
            }
        }
        sudoku(0, 0);
    }

    public static void sudoku(int r, int c){
        if(r == 9){
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    sb.append(matrix[i][j]);
                }
                sb.append("\n");
            }
            System.out.print(sb);
            completed = true;
            return;
        }
        
        // 8열이면 다음 행, 0열로 아니면 같은 행, 다음 열로 이동
        int nextR = (c == 8) ? r + 1 : r;
        int nextC = (c == 8) ? 0 : c + 1;
        // 이미 값이 있으면 다음 칸 채우러
        if(matrix[r][c] != 0){
            sudoku(nextR, nextC);
            return;
        }
        // 빈 칸이면 1~9가 가능한지 확인하고 가능하면 다음 칸 채움
        for(int i=1; i<=9; i++){
            if(isPossible(r, c, i)){
                matrix[r][c] = i;
                sudoku(nextR, nextC);
                if(completed) return;
                matrix[r][c] = 0;
            }
        }
    }

    // r, c 좌표에 num 삽입 가능 여부 반환
    public static boolean isPossible(int r, int c, int num){
        // 행 검사
        for(int i=0; i<9; i++){
            if(matrix[r][i] == num) return false;
        }
        // 열 검사
        for(int i=0; i<9; i++){
            if(matrix[i][c] == num) return false;
        }
        // 3 x 3 검사
        int startR = (r / 3) * 3;
        int startC = (c / 3) * 3;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(matrix[startR + i][startC + j] == num) return false;
            }
        }
        return true;
    }
}