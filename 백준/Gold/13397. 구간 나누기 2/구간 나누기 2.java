import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        // 결정 문제: 구간의 최댓값이 mid를 넘지 않도록 하는 구간의 수가 m보다 작거나 같은가?
        // ffffffffffttttttttttt
        // mid up -> 그룹 수 down     mid down -> 그룹 수 up
        int start = -1; int end = max - min;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            int cnt = 1;
            int curMax = arr[0];
            int curMin = arr[0];
            for(int i=1; i<n; i++){
                curMax = Math.max(curMax, arr[i]);
                curMin = Math.min(curMin, arr[i]);
                if(curMax - curMin > mid){
                    cnt++;
                    curMax = arr[i];
                    curMin = arr[i];
                }
            }
            if(cnt <= m){
                end = mid;
                continue;
            }
            start = mid;
        }
        System.out.print(end);
    }
}