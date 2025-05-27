import java.io.*;
import java.util.*;

public class Main {
    static int r, c;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] matrix;
    static char[][] result;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        matrix = new char[r][c];
        result = new char[r][c];
        for(int i=0; i<r; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j=0; j<c; j++){
                matrix[i][j] = temp[j];
                result[i][j] = temp[j];
            }
        }
        int x1 = c; int y1 = r; int x2 = 0; int y2 = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(matrix[i][j] == 'X'){
                    int cnt = 0;
                    for(int k=0; k<4; k++){
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if(nr < 0 || nr >= r || nc < 0 || nc >= c || matrix[nr][nc] == '.'){
                            cnt++;
                        }
                    }
                    if(cnt >= 3){
                        result[i][j] = '.';
                    }
                    else{
                        x1 = Math.min(x1, j);
                        y1 = Math.min(y1, i);
                        x2 = Math.max(x2, j);
                        y2 = Math.max(y2, i);
                    }
                }
            }
        }
        for(int i=y1; i<=y2; i++){
            for(int j=x1; j<=x2; j++){
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}