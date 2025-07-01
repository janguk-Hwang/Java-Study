import java.io.*;
import java.util.*;

// 각 모형이 가질 수 있는 모양을 정의하고 모든 칸에서 모든 모양으로 탐색하면서 수의 합을 구한다.
public class Main {
    static int n, m, max;
    static int[][] matrix;
    static int[][][] shape = {
        // -, |
        {{0,0},{0,1},{0,2},{0,3}},
        {{0,0},{1,0},{2,0},{3,0}},
        // ㅁ
        {{0,0},{0,1},{1,0},{1,1}},
        // L의 회전, 대칭
        {{0,0},{1,0},{2,0},{2,1}},
        {{0,0},{0,1},{0,2},{1,0}},
        {{0,0},{0,1},{1,1},{2,1}},
        {{0,2},{1,0},{1,1},{1,2}},
        {{0,1},{1,1},{2,1},{2,0}},
        {{0,0},{1,0},{1,1},{1,2}},
        {{0,0},{0,1},{1,0},{2,0}},
        {{0,0},{0,1},{0,2},{1,2}},
        // S, Z 모양
        {{0,0},{1,0},{1,1},{2,1}},
        {{1,0},{1,1},{0,1},{0,2}},
        {{0,1},{1,1},{1,0},{2,0}},
        {{0,0},{0,1},{1,1},{1,2}},
        // ㅜ, ㅓ, ㅗ, ㅏ
        {{0,0},{0,1},{0,2},{1,1}},
        {{0,1},{1,0},{1,1},{2,1}},
        {{0,1},{1,0},{1,1},{1,2}},
        {{0,0},{1,0},{1,1},{2,0}},
    };
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        max = 0;
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int[][] shape : shape){
                    int sum = 0;
                    boolean flag = true;
                    for(int d=0; d<4; d++){
                        int nr = i + shape[d][0];
                        int nc = j + shape[d][1];
                        if(nr < 0 || nr >= n || nc < 0 || nc >= m){
                            flag = false;
                            break;
                        }
                        sum += matrix[nr][nc];
                    }
                    if(flag) max = Math.max(max, sum);
                }
            }
        }
        System.out.print(max);
    }
}