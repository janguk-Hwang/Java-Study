import java.io.*;
import java.util.*;

public class Main {
    static int k;
    static int[][] arr = new int[5][5];          // 1이면 돌(사과 없음)
    static boolean[][] visited = new boolean[5][5];
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            arr[r][c] = 1;
        }
        arr[0][0] = 0;
        visited[0][0] = true;
        arr[4][4] = 0;
        visited[4][4] = true;
        int rst = backtracking(0, 0, 4, 4, 25 - k - 2);
        System.out.println(rst);
    }

    // j: 준규, h:해빈
    static int backtracking(int jr, int jc, int hr, int hc, int remainApple){
        // 모든 사과를 수확했을 때
        if(remainApple == 0) return (jr == hr && jc == hc) ? 1 : 0;
        // 사과를 덜 수확했는데 만났을 때
        if(jr == hr && jc == hc) return 0;
        int rst = 0;
        for(int d=0; d<4; d++){
            int njr = jr + dr[d];
            int njc = jc + dc[d];
            if(njr < 0 || njc < 0 || njr >= 5 || njc >= 5 || visited[njr][njc]) continue;
            if(arr[njr][njc] == 1) continue;
            for(int hd=0; hd<4; hd++){
                int nhr = hr + dr[hd];
                int nhc = hc + dc[hd];
                if(nhr < 0 || nhc < 0 || nhr >= 5 || nhc >= 5) continue;
                if(arr[nhr][nhc] == 1) continue;
                if(visited[nhr][nhc]) continue;
                // 같은 칸인 경우
                if(njr == nhr && njc == nhc){
                    // 마지막 칸에서 남은 칸이 1개인 경우
                    if(remainApple != 1) continue;
                    visited[njr][njc] = true;
                    rst += backtracking(njr, njc, nhr, nhc, 0);
                    visited[njr][njc] = false;
                }
                // 같은 칸이 아닌 경우
                else{
                    visited[njr][njc] = true;
                    visited[nhr][nhc] = true;
                    rst += backtracking(njr, njc, nhr, nhc, remainApple - 2);
                    visited[njr][njc] = false;
                    visited[nhr][nhc] = false;
                }
            }
        }
        return rst;
    }
}