import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static boolean[] isPrime;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        isPrime = new boolean[1001];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for(int i=2; i*i<=1000; i++){
            if(isPrime[i]){
                for(int j=i*i; j<=1000; j+=i){
                    isPrime[j] = false;
                }
            }
        }
        int cnt = 0;
        for(int i : arr) if(isPrime[i]) cnt++;
        System.out.print(cnt);
    }
}