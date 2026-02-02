import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 10000;
    static int n, k;
    static int[] fix;
    static int[][][] dp;    // 날짜, 마지막으로 먹은 음식, 연속으로 먹은 횟수
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        fix = new int[n + 1];
        dp = new int[n + 1][4][3];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            fix[a] = b;
        }
        dp[0][0][0] = 1;
        for(int i=1; i<=n; i++){
            for(int j=0; j<=3; j++){
                for(int p=0; p<=2; p++){
                    int cur = dp[i - 1][j][p];
                    if(cur == 0) continue;
                    // 고정되어 있는 파스타가 있으면
                    if(fix[i] != 0){
                        int temp = fix[i];
                        int newCnt = nextCnt(j, p, temp);
                        if(newCnt != -1) dp[i][temp][newCnt] = (dp[i][temp][newCnt] + cur) % MOD;
                    }
                    else{
                        for(int s=1; s<=3; s++){
                            int newCnt = nextCnt(j, p, s);
                            if(newCnt != -1) dp[i][s][newCnt] = (dp[i][s][newCnt] + cur) % MOD;
                        }
                    }
                }
            }
        }
        int rst = 0;
        for(int p=1; p<=3; p++) for(int cnt=1; cnt<3; cnt++) rst = (rst + dp[n][p][cnt]) % MOD;
        System.out.print(rst);
    }

    static int nextCnt(int last, int cnt, int p) {
        if(last == 0) return 1;          // 시작 상태에서 첫 선택
        if(p != last) return 1;          // 메뉴 변경
        if(cnt == 2) return -1;          // 3번 연속 같은 파스타 x
        return cnt + 1;                  // 같은 메뉴 반복
    }
}
