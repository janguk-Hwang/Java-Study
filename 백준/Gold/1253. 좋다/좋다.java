import java.io.*;
import java.util.*;

// 같은 수가 여러개 있을 수 있다.
public class Main {
    static int n, cnt;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        for(int i=0; i<n; i++){
            long target = arr[i];
            int left = 0;
            int right = n-1;
            while(left < right){
                if(left == i){
                    left++;
                    continue;
                }
                if(right == i){
                    right--;
                    continue;
                }
                long sum = arr[left] + arr[right];
                if(sum == target){
                    cnt++;
                    break;
                }
                if(sum < target) left++;
                if(sum > target) right--;
            }
        }
        System.out.print(cnt);
    }
}