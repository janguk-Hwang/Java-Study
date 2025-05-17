import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr, diff;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        diff = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int diff = Math.abs(arr[1] - arr[0]);
        for(int i=2; i<n; i++){
            // 어차피 두 수간의 차이들을 나누어 떨어지게 하는 수 중 가장 큰 값을 구해야 한다.
            diff = gcd(Math.max(diff, Math.abs(arr[i] - arr[i-1])), Math.min(diff, Math.abs(arr[i] - arr[i-1])));
        }
        System.out.print(diff);
    }

    public static int gcd(int a, int b) {
        if(b == 0) return a;
        if(a % b == 0) return b;
        return gcd(b, a % b);
    }
}