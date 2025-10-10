import java.io.*;
import java.util.*;

// 단순히 에라토스테네스의 체로 소수를 구하고 작은 소수로 나눠가면 시간초과
// 각 숫자의 소인수 중 하나를 기억해놓으면 이 소인수로 수를 나누고 나눠진 수를 또 소인수 중 하나로 나누는 과정을 1이 될 때까지 반복한다.
// 에라토스테네스의 체로 찾은 소수들을 순회하면서 해당 수가 소인수로 가지는 소수일 때까지 반복하면 시간이 오래 걸린다.
// 최악의 경우 348,513개의 소수 리스트를 순회하고 또 찾은 소인수로 나누고 또 소수 리스트를 순회하는 것을 모든 소인수를 찾을 때까지 반복해야 한다.
public class Main {
    static int n, max;
    static int[] arr, primeFactor;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        primeFactor = new int[max + 1];
        for(int i=2; i<=max; i++) primeFactor[i] = i;
        for(int i=2; i*i<=max; i++){
            // 자기 자신이면(소수이면)
            if(primeFactor[i] == i){
                for(int j=i*i; j<=max; j+=i){
                    // 아직 소인수가 정해지지 않았다면
                    if(primeFactor[j] == j){
                        primeFactor[j] = i;
                    }
                }
            }
        }
        for(int i : arr){
            while(i > 1){
                // 자신을 자신의 소인수로 나눔
                sb.append(primeFactor[i]).append(" ");
                i /= primeFactor[i];
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}