// 방법 중 N 사용횟수의 최솟값
class Solution {
    static int rst = -1;
    public int solution(int N, int number) {
        if(N == number) return 1;
        dfs(N, number, 0, 0);
        return rst;
    }
    
    public static void dfs(int N, int number, int cnt, int cur){
        // 종료 조건
        if(cnt > 8) return;
        if(cur == number){
            // 한 번도 최솟값이 갱신되지 않았거나 현재 최솟값이 더 작으면 갱신
            if(rst == -1 || rst > cnt) rst = cnt;
            return;
        }
        int temp = 0;
        for(int i = 1; i <= 8-cnt; i++){
            temp = temp * 10 + N;
            dfs(N, number, cnt+i, cur + temp);
            dfs(N, number, cnt+i, cur - temp);
            dfs(N, number, cnt+i, cur * temp);
            dfs(N, number, cnt+i, cur / temp);
        }
    }
}