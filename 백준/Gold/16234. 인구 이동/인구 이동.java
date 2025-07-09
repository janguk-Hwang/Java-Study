import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static ArrayList<int[]> list;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, L, R;
    static int[][] matrix;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.print(play());
    }

    public static int play(){
        int day = 0;
        while(true){
            boolean flag = false;
            visited = new boolean[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(!visited[i][j]){
                        int sum = func(i, j);       // 이동할 인구 수
                        if(list.size() > 1){
                            int average = sum / list.size();
                            for(int[] temp : list) matrix[temp[0]][temp[1]] = average;
                            flag = true;
                        }
                    }
                }
            }
            if(!flag) return day;
            day++;
        }
    }

    // r, c를 기점으로 국경선이 열려 인구 이동할 인구 수 반환
    public static int func(int r, int c){
        list = new ArrayList<>();
        list.add(new int[]{r, c});
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        int sum = matrix[r][c];
        while(!q.isEmpty()){
            int[] temp = q.poll();
            for(int d=0; d<4; d++){
                int nr = temp[0] + dr[d];
                int nc = temp[1] + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc]) continue;
                int diff = Math.abs(matrix[temp[0]][temp[1]] - matrix[nr][nc]);
                if(diff <= R && diff >= L){
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                    list.add(new int[]{nr, nc});
                    sum += matrix[nr][nc];
                }
            }
        }
        return sum;
    }
}