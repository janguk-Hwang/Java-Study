import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int hoPnt1 = scores[0][0];
        int hoPnt2 = scores[0][1];
        // 1번 항목 내림차순, 2번 항목 오름차순
        Arrays.sort(scores, (a, b)->{
            if(a[0] == b[0]) return a[1] - b[1];    // 오름차순
            return b[0] - a[0];     // 내림차순
        });
        
        int max2 = -1;
        int rst = 1;
        for(int[] temp : scores){
            // 탈락
            if(temp[1] < max2){
                if(temp[0] == hoPnt1 && temp[1] == hoPnt2) return -1;
            }
            // 합격
            else{
                max2 = temp[1];     // 2번 항목 최대값 갱신
                if(temp[0] + temp[1] > hoPnt1 + hoPnt2) rst++;
            }
        }
        return rst;
    }
}