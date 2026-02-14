import java.io.*;
import java.util.*;

//번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
//원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다.
//그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
//없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, m, t;
    static int[][] matrix;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            rotate(x, d, k);
            if(!remove()) average();
        }
        
        int rst = 0;
        for(int i=0; i<n; i++) for(int j=0; j<m; j++) rst += matrix[i][j];
        System.out.print(rst);
    }

    // 회전 함수
    static void rotate(int x, int d, int k){
        // x의 배수 원판 순회
        for(int i=x-1; i<n; i+=x){
            int[] temp = new int[m];
            for(int j=0; j<m; j++){
                if(d == 0) temp[(j + k) % m] = matrix[i][j];
                // 1 ≤ ki < M, +m으로 보정
                else temp[(j - k + m) % m] = matrix[i][j];
            }
            matrix[i] = temp;
        }
    }

    // 인접한 같은 수를 지우고 지운 여부를 반환
    static boolean remove(){
        boolean[][] removed = new boolean[n][m];
        boolean flag = false;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 0) continue;
                for(int d=0; d<4; d++){
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(d == 1 || d == 3) nc = (nc + m) % m;
                    if(nr < 0 || nr >= n) continue;
                    if(matrix[i][j] == matrix[nr][nc]){
                        removed[i][j] = true;
                        removed[nr][nc] = true;
                        flag = true;
                    }
                }
            }
        }
        if(flag){
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++) if(removed[i][j]) matrix[i][j] = 0;
            }
        }
        return flag;
    }
    
    // 인접한 수를 지웠으면 평균보다 큰 수를 감소, 작은 수를 증가
    static void average(){
        int sum = 0; int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] > 0){
                    sum += matrix[i][j]; cnt++;
                }
            }
        }
        if(cnt == 0) return;
        double avg = (double) sum / cnt;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                // 0은 증가하면 안됨
                if(matrix[i][j] == 0) continue;
                if(matrix[i][j] == avg) continue;
                if(matrix[i][j] > avg) matrix[i][j]--;
                else matrix[i][j]++;
            }
        }
    }
}