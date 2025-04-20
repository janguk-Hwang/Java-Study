import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int[] a;
    static long[] prefixSum;
    static long cnt;
    static HashMap<Long, Integer> hs = new HashMap<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n + 2];
        prefixSum = new long[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합 구해놓기
        prefixSum[0] = 0;
        for(int i=1; i<=n; i++){
            prefixSum[i] = prefixSum[i-1] + (long)a[i];
            // 해시맵에 누적합 안 넣었음
        }

        hs.put(0L, 1);
        for(int i=1; i<=n; i++){
            long cur = prefixSum[i];    // 누적 합
            long target = cur - k;      // 찾고 싶은 누적합 값
            cnt += hs.getOrDefault(target, 0);  // 부분합이 k인 경우의 수를 cnt에 누적
            hs.put(cur, hs.getOrDefault(cur, 0) + 1);
        }

        System.out.println(cnt);
    }
}
