import java.io.*;
import java.util.*;

public class Main {
    static int n, m, d, rst;
    static int[][] originMap;
    static int[][] matrix;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        originMap = new int[n][m];
        matrix = new int[n][m];
        for(int i = 0; i< n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< m; j++){
                originMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rst = 0;
        int[] archers = new int[3];
        comb(0, 0, archers);
        System.out.println(rst);
    }

    // m칸 중 3칸을 선택하는 조합
    static void comb(int depth, int idx, int[] archers){
        // 3칸이 선택되면 입력으로 주어진 상태에서 시뮬레이션 시작
        if(depth == 3){
            init();
            func(archers);
            return;
        }
        for(int i=idx; i<m; i++){
            archers[depth] = i;
            comb(depth + 1, i + 1, archers);
        }
    }

    // 시뮬레이션
    static void func(int[] archers){
        int killCount = 0;
        // 모든 적이 격자판에서 제외되면 끝나기 때문에 n번 반복
        for(int turn=0; turn<n; turn++){
            boolean[][] target = new boolean[n][m];
            for(int i=0; i<3; i++){
                int minDist = Integer.MAX_VALUE;
                int bestR = -1;
                int bestC = -1;
                // 격자를 순회하면서 적이 있는 칸이 궁수의 사정거리 안에 있으면
                for(int r=0; r<n; r++){
                    for(int c=0; c<m; c++){
                        if(matrix[r][c] == 1){
                            int dist = Math.abs(n - r) + Math.abs(archers[i] - c);
                            if(dist > d) continue;
                            // 공격할 가장 가까운 적
                            // 가장 가까운 적 선택, 거리가 같으면 더 왼쪽 적을 선택
                            if(dist < minDist || (dist == minDist && c < bestC)){
                                minDist = dist;
                                bestR = r;
                                bestC = c;
                            }
                        }
                    }
                }
                if(bestR != -1) target[bestR][bestC] = true;
            }
            for(int r=0; r<n; r++){
                for(int c=0; c<m; c++){
                    if(target[r][c]){
                        matrix[r][c] = 0;
                        killCount++;
                    }
                }
            }
            nextTurn();
        }
        rst = Math.max(rst, killCount);
    }

    static void nextTurn(){
        for(int r=n-1; r>0; r--) for(int c=0; c<m; c++) matrix[r][c] = matrix[r-1][c];
        for(int c=0; c<m; c++) matrix[0][c] = 0;
    }

    static void init(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                matrix[i][j] = originMap[i][j];
            }
        }
    }
}