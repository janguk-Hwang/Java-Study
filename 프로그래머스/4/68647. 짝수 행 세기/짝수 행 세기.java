// dp[i][j]: i번째 열까지 채웠을 때 홀수 행이 j개인 경우의 수

class Solution {
    public int solution(int[][] a){
        int mod = 10_000_019;
        int n = a.length;
        int m = a[0].length;
        int[] OneInCol = new int[m];
        for(int j=0; j<m; j++){
            for(int i=0; i<n; i++){
                OneInCol[j] += a[i][j];
            }
        }
        // 파스칼의 삼각형으로 조합 값 계산(% mod)
        long[][] comb = new long[n+1][n+1];
        comb[0][0] = 1;
        for(int i=1; i<=n; i++){
            comb[i][0] = 1;
            for(int j=1; j<=i; j++){
                // 위의 좌우 값의 합
                comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]) % mod;
            }
        }

        long[][] dp = new long[m+1][n+1];
        dp[0][0] = 1;
        for(int i=1; i<=m; i++){    // 열
            int needOne = OneInCol[i - 1];    // 배치해야 하는 1의 개수
            for(int j=0; j<=n; j++){    // 홀수 행의 수
                // O: 홀수 행 중에서 1을 배치할 행의 수
                // E: 짝수 행 중에서 1을 배치할 행의 수
                // 7 10 5 2(배치해야 하는 1의 수 - 짝수 행에 배치하고 남은 1의 개수)
                int minO = Math.max(0, needOne-(n-j));      // 홀수 행에 배치할 수 있는 최소의 1의 개수
                int maxO = Math.min(j, needOne);        // 홀수 행에 배치할 수 있는 최대의 1의 개수
                for(int O=minO; O<=maxO; O++){
                    int E = needOne - O;
                    int nextOdd = j - O + E;    // 홀수였던 행의 수 - 홀수 행에 배치한 1의 개수 + 짝수 행에 배치한 1의 개수
                    // 홀수 행에서 뽑는 조합의 수 * 짝수 행에서 뽑는 조합의 수 % mod
                    long num = (comb[j][O] * comb[n-j][E]) % mod;
                    dp[i][nextOdd] = (dp[i][nextOdd] + dp[i-1][j] * num) % mod;
                }
            }
        }
        return (int)dp[m][0];
    }
}