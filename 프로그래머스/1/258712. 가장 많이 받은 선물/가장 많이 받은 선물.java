import java.io.*;
import java.util.*;

// 이번 달까지 준 선물이 더 많은 사람이 다음 달에 선물을 받고,
// 주고받은 기록이 없거나 주고받은 수가 같다면 선물 지수가 더 큰 사람이 선물을 받는다.
// 선물 지수: 준 선물의 수에서 받은 선물의 수를 뺀 값
// 선물 지수도 같다면 다음 달에 선물을 주고받지 않는다.
// 다음 달에 선물을 가장 많이 받을 사람이 받을 선물의 수를 알아내야 한다.
class Solution {
    static int n, max;
    static int[][] inout;     // i가 j에게 준 선물 횟수 저장
    static int[] giftIdx;       // 선물 지수
    static Map<String, Integer> map;
    public int solution(String[] friends, String[] gifts) {
        n = friends.length;
        giftIdx = new int[n];
        inout = new int[n][n];
        map = new HashMap<>();
        for(int i=0; i<n; i++) map.put(friends[i], i);  // 이름과 인덱스 저장
        for(String str : gifts){
            String[] s = str.split(" ");
            int a = map.get(s[0]);
            int b = map.get(s[1]);
            inout[a][b]++;
            giftIdx[a]++;   giftIdx[b]--;
        }
        
        max = 0;
        for(int i=0; i<n; i++){
            int cnt = 0;    // 다음 달에 받을 선물 수
            for(int j=0; j<n; j++){
                if(i == j) continue;
                if(inout[i][j] > inout[j][i]){
                    cnt++;
                    continue;
                }
                if(inout[i][j] == inout[j][i] && giftIdx[i] > giftIdx[j]) cnt++;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}