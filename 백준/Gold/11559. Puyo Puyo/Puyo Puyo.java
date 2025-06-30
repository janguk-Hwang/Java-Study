import java.io.*;
import java.util.*;

public class Main {
    static final int row = 12;
    static final int col = 6;
    static char[][] matrix;
    static boolean[][] visited;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        matrix = new char[12][6];
        for(int i=0; i<row; i++) matrix[i] = br.readLine().toCharArray();
        int cascade = 0;
        while(true){
            visited = new boolean[row][col];
            boolean flag = false;
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    if(matrix[i][j] != '.' && !visited[i][j]){
                        if(func(i, j)){
                            flag = true;
                        }
                    }
                }
            }
            if(!flag) break;
            meltDown();
            cascade++;
        }
        System.out.print(cascade);
    }

    public static boolean func(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        List<int[]> list = new ArrayList<>();
        visited[r][c] = true;
        q.add(new int[]{r, c});
        list.add(new int[]{r, c});
        char color = matrix[r][c];
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int d=0; d<4; d++){
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                if(nr < 0 || nr >= row || nc < 0 || nc >= col) continue;
                if(!visited[nr][nc] && matrix[nr][nc] == color){
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                    list.add(new int[]{nr, nc});
                }
            }
        }
        if(list.size() >= 4){
            for(int[] temp : list) matrix[temp[0]][temp[1]] = '.';
            return true;
        }
        return false;
    }

    public static void meltDown(){
        for(int i=row-1; i>=0; i--){
            for(int j=0; j<col; j++){
                if(matrix[i][j] == '.'){
                    int temp = i-1;
                    while(temp >= 0 && matrix[temp][j] == '.') temp--;
                    if(temp >= 0){
                        matrix[i][j] = matrix[temp][j];
                        matrix[temp][j] = '.';
                    }
                }
            }
        }
    }
}