import java.io.*;
import java.util.*;

//N자리 수 중에서 신기한 소수를 오름차순으로 정렬해서 한 줄에 하나씩 출력한다.
public class Main{
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Solution(0,N);
        System.out.println(sb);
    }

    public static void Solution(int output, int n) {
        //자리를 늘려나갈 때마다 n--하여 n이 0이 되면 조건에 만족하기 때문에 n이 0이되면
        //해당 값이 소수인지 최종확인하고 결과에 추가
        if(n == 0){
            if(isPrime(output)){ 
                sb.append(output).append("\n");
            }
            return;
        }

        //반복문에서 i를 0부터 9까지 반복하여 모든 수에서 신기한 소수를 찾음
        for(int i=0; i<10; i++){
            int next = output*10 + i;
            //next가 소수이면 다음 next 값을 가지고 재귀함수 호출
            if(isPrime(next)) Solution(next, n-1);
        }
    }

    //소수 판정 함수(소수이면 true 반환)
    public static boolean isPrime(int num){
        if(num < 2) return false;

        //num의 제곱근까지만 검사하면 됨
        for(int i=2; i<=Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }

        return true;
    }
}