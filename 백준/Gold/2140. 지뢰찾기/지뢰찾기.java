import java.io.*;
import java.util.*;

// 보드의 테두리가 모두 열려있고, 그 외는 모두 닫혀있는 상태에서 시작한다
public class Main {
    static int n;
    static int[][] matrix;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        matrix = new int[n + 1][n + 1];
        for(int i=1; i<=n; i++){
            String temp = br.readLine();
            for(int j=1; j<=n; j++){
                char c = temp.charAt(j-1);
                if(c == '#') matrix[i][j] = -1;
                else matrix[i][j] = c - '0';
            }
        }
        int cnt = 0;
        // 내부만 탐색
        for(int i=2; i<=n-1; i++){
            for(int j=2; j<=n-1; j++){
                if(matrix[i][j] != -1) continue;
                boolean flag = true;
                // 인접한 8방향에 0이 있으면 해당 칸에는 지뢰가 있을 수 없다
                for(int d=0; d<8; d++){
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(matrix[nr][nc] == 0){
                        flag = false;
                        break;
                    }
                }
                if(!flag) continue;
                // 해당 칸에 지뢰를 두면 8방향 칸들을 1씩 감소(지뢰가 있을 수 있는 내부 칸이면 해당x)
                for(int d=0; d<8; d++){
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(matrix[nr][nc] != -1){
                        matrix[nr][nc]--;
                    }
                }
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}