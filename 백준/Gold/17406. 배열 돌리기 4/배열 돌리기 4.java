import java.io.*;
import java.util.*;

// 백트래킹, 1 <= k <= 6이므로 6! = 720개의 경우 모두 진행
// 배열 복사 방식 x, 배열 원복 방식 o  -> 배열 반시계 회전도 구현해야 함
public class Main {
    static int min = Integer.MAX_VALUE;
    static Operation[] operations;
    static int[][] matrix;
    static int n, m, k;
    static boolean[] visited;   // 각 연산 사용 여부 기억
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        visited = new boolean[k];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        operations = new Operation[k];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            operations[i] = new Operation(r, c, s);
        }
        backtracking(0);
        System.out.print(min);
    }

    // 백트래킹
    public static void backtracking(int depth){
        // 종료 조건
        if(depth == k){
            min = Math.min(min, minSum());
            return;
        }
        // 가능한 모든 경우 선택
        for(int i=0; i<k; i++){
            if(!visited[i]){
                visited[i] = true;
                rotateCW(operations[i].r, operations[i].c, operations[i].s);
                backtracking(depth + 1);
                rotateCCW(operations[i].r, operations[i].c, operations[i].s);   // 원복
                visited[i] = false;
            }
        }
    }

    // 시계방향 회전
    static void rotateCW(int r, int c, int s){
        r--; c--;
        for(int i=1; i<=s; i++){
            int top = r - i;
            int left = c - i;
            int bottom = r + i;
            int right = c + i;
            int temp = matrix[top][right];
            for(int col=right; col>left; col--) matrix[top][col] = matrix[top][col - 1];
            for(int row=top; row<bottom; row++) matrix[row][left] = matrix[row + 1][left];
            for(int col=left; col<right; col++) matrix[bottom][col] = matrix[bottom][col + 1];
            for(int row=bottom; row>top+1; row--) matrix[row][right] = matrix[row - 1][right];
            matrix[top + 1][right] = temp;
        }
    }

    // 반시계방향 회전
    static void rotateCCW(int r, int c, int s){
        r--; c--;
        for(int i=1; i<=s; i++){
            int top = r - i;
            int left = c - i;
            int bottom = r + i;
            int right = c + i;
            int temp = matrix[top][left];
            for(int col=left; col<right; col++) matrix[top][col] = matrix[top][col+1];
            for(int row=top; row<bottom; row++) matrix[row][right] = matrix[row+1][right];
            for(int col=right; col>left; col--) matrix[bottom][col] = matrix[bottom][col-1];
            for(int row=bottom; row>top+1; row--) matrix[row][left] = matrix[row-1][left];
            matrix[top+1][left] = temp;
        }
    }

    // 각 행 최소값 계산
    public static int minSum(){
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            int sum = 0;
            for(int j=0; j<m; j++){
                sum += matrix[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    public static class Operation{
        int r, c, s;
        Operation(int r, int c, int s){
            this.r = r; this.c = c; this.s = s;
        }
    }
}