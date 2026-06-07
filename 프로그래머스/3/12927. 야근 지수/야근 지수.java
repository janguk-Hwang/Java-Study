import java.util.*;

class Solution {
    static PriorityQueue<Integer> pq;
    public long solution(int n, int[] works) {
        pq = new PriorityQueue(Collections.reverseOrder());
        for(int i : works) pq.offer(i);
        for(int i=0; i<n; i++){
            int cur = pq.poll();
            if(cur == 0) return 0;
            pq.offer(cur - 1);
        }
        long rst = 0;
        while(!pq.isEmpty()){
            int temp = pq.poll();
            rst += temp * temp;
        }
        return rst;
    }
}