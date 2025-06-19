import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static ArrayList<int[]> virusList = new ArrayList<>();
    static int[][] matrix;
    static int n, k, s, x, y;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] > 0){
                    virusList.add(new int[]{matrix[i][j], i, j});
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        virusList.sort((a, b) -> a[0] - b[0]);
        Queue<int[]> q = new LinkedList<>();
        for(int[] vi : virusList) q.offer(vi);

        int time = 0;
        while(!q.isEmpty()){
            if(time == s) break;
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] now = q.poll();
                int virusNum = now[0];
                int r = now[1];
                int c = now[2];
                for(int d=0; d<4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr >= 0 && nr < n && nc >= 0 && nc < n && matrix[nr][nc] == 0){
                        matrix[nr][nc] = virusNum;
                        q.offer(new int[]{virusNum, nr, nc});
                    }
                }
            }
            time++;
        }
        System.out.print(matrix[x-1][y-1]);
    }
}