import java.io.*;
import java.util.*;

// 얻을 수 있는 점수 = 밑장 빼기 이전의 정상적인 순서의 점수 + 밑장 빼기한 카드 + 밑장 빼기 후 엇갈린 순서의 점수
public class Main {
    static int n;
    static int[] arr, even, odd;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        even = new int[n + 1];
        odd = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            even[i + 1] = even[i];
            odd[i + 1] = odd[i];
            if(i % 2 == 0) even[i + 1] += arr[i];
            else odd[i + 1] += arr[i];
        }
        int bottom = n - 1;
        int rst = even[n];
        for(int i=0; i<n; i++){
            int score = 0;
            score += even[i];
            if(i % 2 == 0) score += arr[bottom];
            score += (odd[bottom] - odd[i]);
            rst = Math.max(rst, score);
        }
        System.out.print(rst);
    }
}