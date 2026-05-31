// [깊이][인덱스]
// dp[i][j]: i층 j번째에 도달했을 때, 합의 최댓값

class Solution {
    public int solution(int[][] triangle) {
        for(int i=1; i<triangle.length; i++){
            for(int j=0; j<triangle[i].length; j++){
                if(j == 0) triangle[i][j] += triangle[i-1][0];
                else if(j == i) triangle[i][j] += triangle[i-1][j-1];
                else triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
            }
        }
        int max = 0;
        for(int i : triangle[triangle.length - 1]) max = Math.max(max, i);
        return max;
    }
}