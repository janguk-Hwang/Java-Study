// 약수의 개수와 관련이 있다.
// 약수의 개수만큼 등장한다. 약수의 개수 = 등장 횟수
// 가장 많이 등장한 수가 여러 개라면 그 중 가장 작은 수를 답해야 합니다.

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] arr = new int[e + 1];     // 약수의 수를 담는 배열
        for(int i=1; i<=e; i++){
            for(int j=i; j<=e; j+=i){   // 배수 탐색
                arr[j]++;
            }
        }
        int[] temp = new int[e + 1];
        int max = e;
        for(int i=e; i>=1; i--){
            // 8과 6중에 6이 새로운 max로 갱신
            if(arr[i] >= arr[max]) max = i;
            temp[i] = max;
        }
        int[] rst = new int[starts.length];
        for(int i=0; i<starts.length; i++) rst[i] = temp[starts[i]];
        return rst;
    }
}