import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main{
    static int N;
    static int M;
    static boolean[] isPrime;
    static Set<Integer> resultSet; // 중복 제거를 위해 Set 사용
    static boolean[] visited;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        int[] arr = new int[N];
        result = new int[M+1];
        visited = new boolean[N+1];
        resultSet = new HashSet<>(); // 중복을 허용하지 않음

        String[] input = br.readLine().split(" ");
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(input[i]);
        }

        //에라토스테네스의 체로 소수 구하기
        isPrimeFunction(9001);

        //M개의 숫자의 합이 소수인지 확인
        isWeightSumPrime(0, 0, arr);

        //Set을 배열로 변환하고 정렬 후 출력
        Integer[] resultArr = resultSet.toArray(new Integer[0]);
        Arrays.sort(resultArr);

        if (resultArr.length == 0) {
            System.out.println("-1"); // 소수 합이 없으면 -1 출력
        }
        else {
            for(int num : resultArr){
                System.out.print(num + " ");
            }
        }
    }

    // 에라토스테네스의 체로 1000 이하의 소수를 구함
    static void isPrimeFunction(int n){
        isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0과 1은 소수가 아님

        for(int i = 2; i*i <= n; i++){
            if(isPrime[i]){
                for(int j = i*i; j < n; j += i){
                    isPrime[j] = false;
                }
            }
        }
    }

    // M개의 arr[i]를 선택해서 그 합이 소수인지 확인
    static void isWeightSumPrime(int depth, int sum, int[] arr){
        // M개의 숫자를 모두 선택한 경우
        if(depth == M){
            if(isPrime[sum]){
                resultSet.add(sum); // 합이 소수라면 Set에 추가
            }
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                isWeightSumPrime(depth + 1, sum + arr[i], arr);
                visited[i] = false;
            }
        }
    }
}
