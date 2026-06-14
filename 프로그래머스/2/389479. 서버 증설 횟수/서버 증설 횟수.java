class Solution {
    // i x m명의 사람이 있으면 i개의 서버가 필요
    // i x m + (m - 1)명의 사람이 있으면 i개의 서버가 필요
    static int[] returnServer;
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int curActivatedServer = 0;
        returnServer = new int[24 + k + 1];
        for(int i=0; i<24; i++){
            curActivatedServer -= returnServer[i];
                
            int curNeed = players[i] / m;
            if(curActivatedServer < curNeed){
                int temp = curNeed - curActivatedServer;
                answer += temp;
                curActivatedServer += temp;
                returnServer[i + k] += temp;
            }
        }
        return answer;
    }
}