import java.io.*;
import java.util.*;

// 결정 문제: mid분까지 n번째 아이가 탑승하는가?
public class Main {
    static int n, m;
    static int[] machine;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        machine = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=m; i++) machine[i] = Integer.parseInt(st.nextToken());
        if(n <= m){
            System.out.println(n);
            return;
        }
        // fffffttttt
        long start = -1; long end = 60_000_000_000L;
        while(start + 1 < end){
            long mid = (start + end) / 2;
            if(count(mid) >= n) end = mid;
            else start = mid;
        }
        // n번 아이가 타기 1분 전부터 계산
        long before = count(end - 1);
        for(int i=1; i<=m; i++){
            // 운행시간으로 나누어 떨어지는 놀이기구가 비게 됨
            if(end % machine[i] == 0){
                before++;
                if(before == n){
                    System.out.print(i);
                    return;
                }
            }
        }
    }
    // t분까지 탑승한 총 아이의 수
    static long count(long t){
        if(t < 0) return 0;
        // 0분에는 놀이기구에 탑승
        long total = m;
        for(int i=1; i<=m; i++) total += t / machine[i];
        return total;
    }
}
