class Solution{
    //자연수를 2진수로 변화한 값에서의 1의 개수를 반환하는 함수
    public int countfunc(int num){
            int count = 0;
            while(num > 0){
                if(num % 2 == 1){ //끝 비트가 1이면
                    count++;
                }
                num /= 2; //오른쪽으로 한 비트 이동
            }
            return count;
    }
    
    public int solution(int n){
        int nextnum = n+1;

        //다음 숫자와 1의 개수가 같지않으면 다음 숫자로 넘어감
        while(countfunc(nextnum) != countfunc(n)){
            nextnum++;
        }

        //1의 개수가 같으면 while문을 나와서 실행되어 nextnum을 반환
        return nextnum;
    }
}