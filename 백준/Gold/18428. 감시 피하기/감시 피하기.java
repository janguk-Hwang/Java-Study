import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] matrix;
    static List<int[]> eList = new ArrayList<>();
    static List<int[]> tList = new ArrayList<>();
    static boolean flag;
    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new char[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                matrix[i][j] = st.nextToken().charAt(0);
                if(matrix[i][j] == 'X') eList.add(new int[]{i, j});
                else if (matrix[i][j] == 'T') tList.add(new int[]{i, j});
            }
        }
        comb(0, 0);
        if(flag) System.out.print("YES");
        else System.out.print("NO");
    }

    // 장애물 3개 설치 조합
    public static void comb(int start, int depth){
        if(depth == 3){
            if(isPossible()) flag = true;
            return;
        }
        for(int i=start; i<eList.size(); i++){
            int x = eList.get(i)[0];
            int y = eList.get(i)[1];
            // 장애물 설치
            matrix[x][y] = 'O';
            comb(i + 1, depth + 1);
            matrix[x][y] = 'X';
        }
    }

    // 모든 학생이 감시로부터 피할 수 있는지 여부 반환
    public static boolean isPossible(){
        for(int[] t : tList){
            for(int d=0; d<4; d++){
                int nx = t[0];
                int ny = t[1];
                while(true){
                    nx += dx[d];
                    ny += dy[d];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n) break;
                    // 장애물(O)을 만나면 진행 멈춤
                    if(matrix[nx][ny] == 'O') break;
                    // 학생을 만나면 false 반환
                    if(matrix[nx][ny] == 'S') return false;
                }
            }
        }
        return true;
    }
}