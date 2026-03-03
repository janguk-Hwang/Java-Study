import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);
            int left = 0; int right = n-1;
            int min = Integer.MAX_VALUE;
            int rst = 0;
            // 서로 다른 두 정수
            while(left < right){
                int sum = arr[left] + arr[right];
                int diff = Math.abs(sum - k);
                if(diff < min){
                    min = diff;
                    rst = 1;
                }
                else if(diff == min) rst++;
                if(sum >= k) right--;
                else left++;
            }
            sb.append(rst).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }
}