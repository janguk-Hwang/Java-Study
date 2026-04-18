import java.io.*;
import java.util.*;

// 커맨드
// 이동 / 잉크량 + 1 / 잉크 흩뿌리기 /
public class Main {
    static char[][] matrix;
    static boolean[][] obstacle;
    static int i, n, k;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        i = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        // 잉크 문자열
        String s = br.readLine();
        matrix = new char[n][n];
        obstacle = new boolean[n][n];
        int ink = 0; int jumpCnt = 0;
        int row = -1; int col = -1;
        for(int j=0; j<n; j++){
            String line = br.readLine();
            for(int q=0; q<n; q++){
                char c = line.charAt(q);
                // 사각형 초기 위치
                if(c == '@'){
                    row = j;
                    col = q;
                    matrix[j][q] = '.';
                }
                // 장애물
                else if(c == '#'){
                    obstacle[j][q] = true;
                    matrix[j][q] = '#';
                }
                // 빈 칸
                else matrix[j][q] = '.';
            }
        }
        String line = br.readLine();
        for(int j=0; j<k; j++){
            char command = line.charAt(j);
            // 상
            if(command == 'U' && row - 1 >= 0 && !obstacle[row - 1][col]) row--;
            // 하
            else if(command == 'D' && row + 1< n && !obstacle[row + 1][col]) row++;
            // 좌
            else if(command == 'L' && col - 1 >= 0 && !obstacle[row][col - 1]) col--;
            // 우
            else if(command == 'R' && col + 1 < n && !obstacle[row][col + 1]) col++;
            else if(command == 'j') ink++;
            else if(command == 'J'){
                char color = s.charAt(jumpCnt % s.length());
                jumpCnt++;
                for(int q=0; q<n; q++){
                    for(int p=0; p<n; p++){
                        if(obstacle[q][p]){
                            int dist = Math.abs(q - row) + Math.abs(p - col);
                            if(dist <= ink) matrix[q][p] = color;
                        }
                    }
                }
                ink = 0;
            }
        }
        for(int q=0; q<n; q++){
            for(int p=0; p<n; p++){
                if(q == row && p == col) sb.append('@');
                else sb.append(matrix[q][p]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}