class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean flag = false;
        int num = n;
        
        while(num < 1_000_000){
            num++;
            if(Integer.bitCount(n) == Integer.bitCount(num)){
                return num;
            }
        }
        return 0;
    }
}