import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
        int ansLeft = 0; int ansRight = 0;
        while(left < right){
            int sum = arr[left] + arr[right];
            if(Math.abs(sum) < best){
                best = Math.abs(sum);
                ansLeft = left;
                ansRight = right;
            }
            if(sum < 0) left++;
            else if(sum > 0) right--;
            // 0이면 바로 종료
            else break;
        }
        System.out.println(arr[ansLeft] + " " + arr[ansRight]);
    }
}