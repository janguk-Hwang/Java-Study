import java.io.*;
import java.util.*;

// +1 연산의 횟수는 수의 1 비트 개수 합이다.
// x2 연산의 횟수는 수의 가장 큰 수의 비트 길이이다.
public class Main {
    static int n, bitCnt, maxBitLen;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int sum = 0;
        int max = 0;
        for(int i : arr){
            bitCnt = Integer.bitCount(i);
            max = Math.max(max, i);
            sum += bitCnt;
        }
        maxBitLen = Integer.toBinaryString(max).length() - 1;
        System.out.print(sum + maxBitLen);
    }
}