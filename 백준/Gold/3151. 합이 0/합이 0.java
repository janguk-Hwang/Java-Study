import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long rst;
    static int[] arr, cnt;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n]; cnt = new int[20001]; // -10000 ~ 0 ~ 10000 --> 0 ~ 20001
        st = new StringTokenizer(br.readLine());
        // 음수는 건드리지 않고 인덱스만 0부터 20001까지
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            cnt[arr[i]+10000]++;
        }
        rst = 0;
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int sum = arr[i] + arr[j];
                int target = -sum;
                if(Math.abs(target) > 10000) continue;
                rst += cnt[target + 10000];
                if(target == arr[i]) rst--;
                if(target == arr[j]) rst--;
            }
        }
        System.out.print(rst / 3);
    }
}