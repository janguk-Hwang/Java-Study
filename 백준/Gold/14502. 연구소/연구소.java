import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, m, safe, max;
    static int[][] matrix;
    static ArrayList<int[]> empty = new ArrayList<>();
    static ArrayList<int[]> virus = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 0) empty.add(new int[]{i, j});
                if(matrix[i][j] == 2) virus.add(new int[]{i, j});
            }
        }

        int size = empty.size();
        for(int i=0; i<size; i++){
            for(int j=i+1; j<size; j++){
                for(int k=j+1; k<size; k++){
                    int[] wall1 = empty.get(i);
                    int[] wall2 = empty.get(j);
                    int[] wall3 = empty.get(k);
                    matrix[wall1[0]][wall1[1]] = 1;
                    matrix[wall2[0]][wall2[1]] = 1;
                    matrix[wall3[0]][wall3[1]] = 1;

                    safe = virusMove();
                    max = Math.max(max, safe);

                    matrix[wall1[0]][wall1[1]] = 0;
                    matrix[wall2[0]][wall2[1]] = 0;
                    matrix[wall3[0]][wall3[1]] = 0;
                }
            }
        }
        System.out.print(max);
    }

    public static int virusMove(){
        int[][] temp = new int[n][m];
        for(int i=0; i<n; i++) temp[i] = matrix[i].clone();
        Queue<int[]> q = new LinkedList<>();
        for(int[] vi : virus) q.offer(vi);
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int d=0; d<4; d++){
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m){
                    if(temp[nr][nc] == 0){
                        temp[nr][nc] = 2;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(temp[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
}