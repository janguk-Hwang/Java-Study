import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int n, k, l;
    static int[][] matrix;
    static ArrayList<int[]> list = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            matrix[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }
        l = Integer.parseInt(br.readLine());
        for(int i=0; i<l; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            list.add(new int[]{time, dir});
        }
        System.out.print(func());
    }

    public static int func(){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        int r = 0, c = 0, d = 0;
        int time = 0, idx = 0;
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        while(true){
            time++;
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= n || nc < 0 || nc >= n) break;       // 벽과 부딪히면 게임이 끝난다.
            if(visited[nr][nc]) break;      // 자기자신의 몸과 부딪히면 게임이 끝난다.
            q.offer(new int[]{nr, nc});
            visited[nr][nc] = true;
            boolean flag = false;
            if(matrix[nr][nc] == 1){
                matrix[nr][nc] = 0;
                flag = true;
            }
            if(!flag){
                int[] tail = q.poll();
                visited[tail[0]][tail[1]] = false;
            }
            if(idx < l && time == list.get(idx)[0]){
                char dir = (char)list.get(idx)[1];
                if(dir == 'L') d = (d + 3) % 4;
                if(dir == 'D') d = (d + 1) % 4;
                idx++;
            }
            r = nr; c = nc;
        }
        return time;
    }
}