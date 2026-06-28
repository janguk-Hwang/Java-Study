import java.util.*;

class Solution {
    static List<int[]> food;
    public int solution(int[] food_times, long k) {
        long total = 0;
        for(int i : food_times) total += i;
        if(total <= k) return -1;
        food = new ArrayList<>();
        // [시간, 원래 인덱스] 저장
        for(int i=0; i<food_times.length; i++) food.add(new int[]{food_times[i], i + 1});
        Collections.sort(food, (a, b) -> Integer.compare(a[0], b[0]));
        int n = food.size();
        int idx = 0;
        long prevTime = 0;
        for(int i=0; i<n; i++){
            long temp = food.get(i)[0] - prevTime;
            long temp1 = temp * (n - i);
            if(temp1 <= k){
                k -= temp1;
                prevTime = food.get(i)[0];
            }
            else{
                idx = i;
                break;
            }
        }
        
        List<int[]> list = food.subList(idx, n);
        list.sort((a, b) -> Integer.compare(a[1], b[1]));
        return list.get((int)(k % list.size()))[1];
    }
}
