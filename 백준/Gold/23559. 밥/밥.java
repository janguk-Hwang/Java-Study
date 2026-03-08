import java.io.*;
import java.util.*;

// dp로 풀이하면 dp[100000][5000]로 메모리 초과
// 기본적으로 1000원짜리 메뉴를 먹는걸로 깔고 간다.
// 가지고 있는 돈이 적어도 매일 B 메뉴를 먹을 수 있다.
// 맛의 합이 차이가 큰 날부터 5000원짜리 메뉴를 먹어나간다.
public class Main {
    static int n, x;
    static Integer[] diff;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        int sumB = 0;       // B 메뉴만 먹었을 때, 맛의 합
        diff = new Integer[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sumB += b;
            diff[i] = a - b;        // i날에 A메뉴와 B메뉴의 맛 차이
        }
        Arrays.sort(diff, Collections.reverseOrder());
        int onlyBCost = 1000 * n;
        int canReplaceCnt = (x - onlyBCost) / 4000;
        for(int i=0; i<n && canReplaceCnt>0; i++){
            if(diff[i] > 0){
                sumB += diff[i];
                canReplaceCnt--;
            }
            else break;
        }
        System.out.print(sumB);
    }
}