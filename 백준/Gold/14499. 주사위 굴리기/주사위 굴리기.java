import java.io.*;
import java.util.*;

// 주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
// 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
public class Main {
    static int n, m, r, c, k;
    static int[][] matrix;
    static int[] dice = new int[7];
    static int[] dr = {0, 0, 0, -1, 1};     // 주어지는 명령이 1~4이므로 0번째를 0으로 채움
    static int[] dc = {0, 1, -1, 0, 0};     // 동 서 북 남
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) list.add(Integer.parseInt(st.nextToken()));
        for(Integer d : list){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;        //만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.
            move(d);
            r = nr; c = nc;

            boolean flag = false;
            //이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
            if(matrix[r][c] == 0){
                matrix[r][c] = dice[6];
                flag = true;
            }
            //0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
            if(!flag){
                dice[6] = matrix[r][c];
                matrix[r][c] = 0;
            }
            sb.append(dice[1]).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    public static void move(int dir){
        int up = dice[1], north = dice[2], west = dice[3];
        int east = dice[4], south = dice[5], down = dice[6];
        switch(dir){
            case 1: // 동
                dice[1] = west;
                dice[3] = down;
                dice[6] = east;
                dice[4] = up;
                break;
            case 2: // 서
                dice[1] = east;
                dice[4] = down;
                dice[6] = west;
                dice[3] = up;
                break;
            case 3: // 북
                dice[1] = south;
                dice[2] = up;
                dice[6] = north;
                dice[5] = down;
                break;
            case 4: // 남
                dice[1] = north;
                dice[5] = up;
                dice[6] = south;
                dice[2] = down;
                break;
            default : break;
        }
    }
}