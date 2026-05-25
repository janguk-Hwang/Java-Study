// 플로이드 워셜

class Solution {
    static boolean[][] arr;
    static int rst = 0;
    public int solution(int n, int[][] results) {
        arr = new boolean[n + 1][n + 1];
        for(int[] a : results){
            int win = a[0];
            int lose = a[1];
            arr[win][lose] = true;
        }
        
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(arr[i][k] && arr[k][j]){
                        arr[i][j] = true;
                    }
                }
            }
        }
        
        for(int i=1; i<=n; i++){
            int cnt = 0;
            for(int j=1; j<=n; j++){
                if(i == j) continue;
                if(arr[i][j] || arr[j][i]) cnt++;
            }
            if(cnt == n-1) rst++;
        }
        return rst;
    }
}