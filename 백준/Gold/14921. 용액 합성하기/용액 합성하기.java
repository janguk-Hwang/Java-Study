import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int left = 0; int right = n-1;
        int best = Integer.MAX_VALUE;
        int rst = Integer.MAX_VALUE;
        while(left < right){
            int sum = arr[left] + arr[right];
            // 두 수의 합이 기존 최소값보다 작으면 갱신
            if(Math.abs(sum) < best){
                best = Math.abs(sum);
                rst = sum;
            }
            if(sum < 0) left++;
            else if(sum > 0) right--;
            if(sum == 0) break;
        }
        System.out.println(rst);
    }
}