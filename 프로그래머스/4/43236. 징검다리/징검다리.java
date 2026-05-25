// 최솟값을 최대한 크도록 해야 한다.
// mid: 사이 거리의 하한선
// 결정 문제: 거리의 최솟값을 mid로 설정했을 때, 바위를 n개 이하로 제거해서 조건을 만족할 수 있는가?
import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int rst = 0;
        int start = 0;
        int end = distance + 1;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            int removed = 0;
            int prev = 0;
            for(int rock : rocks){
                // 바위 사이의 거리가 하한선보다 좁으면 바위 제거
                if(rock - prev < mid) removed++;
                else prev = rock;
            }
            if(distance - prev < mid) removed++;
            if(removed > n) end = mid;
            else{
                rst = mid;
                start = mid;
            }
        }
        return (int)rst;
    }
}