class Solution {
    public int solution(int n, int[] stations, int w) {
        int rst = 0; int idx = 1;
        int range = w * 2 + 1;
        for(int i : stations){
            int start = i - w;
            int end = i + w;
            if(idx < start){
                int len = start - idx;
                rst += (len + range - 1) / range;
            }
            idx = end + 1;
        }
        // idx는 현재 전파가 도달하는 지 확인해야 할 위치이므로 =가 있어야 함.
        if(idx <= n){
            int len = n - idx + 1;
            rst += (len + range - 1) / range;
        }
        return rst;
    }
}