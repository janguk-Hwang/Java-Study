import java.io.*;
import java.util.*;

// 1 2 4 5
// 1 2 1 5
// 5의 입장에서 봤을 때 dp[i]는 i-1번째 숫자를 사용할 것인지 아닌지를 선택하는 것
// 앞에서부터 매번 j번째 숫자를 사용할 것인지 아닌지를 선택해나가는 것
public class Main {
    static int n;
    static int[] dpLeft, dpRight, arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dpLeft = new int[n];
        dpRight = new int[n];
        Arrays.fill(dpLeft, 1);
        Arrays.fill(dpRight, 1);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(arr[j] < arr[i]){
                    dpLeft[i] = Math.max(dpLeft[i], dpLeft[j] + 1);
                }
            }
        }
        for(int i=n-1; i>=0; i--){
            for(int j=n-1; j>i; j--){
                if(arr[j] < arr[i]){
                    dpRight[i] = Math.max(dpRight[i], dpRight[j] + 1);
                }
            }
        }
        // i번째 숫자의 dpLeft[i]와 dpRight[i]에는 각각 자신을 포함한 증가, 감소하는 수열의 길이가 담겨있다.
        // dpLeft[i] + dpRight[i] - 1
        int max = 0;
        for(int i=0; i<n; i++){
            max = Math.max(max, dpLeft[i] + dpRight[i]);
        }
        System.out.print(max - 1);
    }
}