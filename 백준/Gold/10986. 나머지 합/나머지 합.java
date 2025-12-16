import java.io.*;
import java.util.*;

// 부분합으로 빠르게 연속된 구간의 합을 구한다.
// 부분합을 전부 구해서 하나씩 확인하면 봐야하는 구간이 너무 많아 시간 초과
// 나머지가 같은 부분합끼리의 만들 수 있는 조합의 수를 구해서 나누어 떨어지는 연속된 구간의 개수를 구할 수 있다.
public class Main {
    static int n, m;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // preSumCnt[i]: 누적합 % m == i인 경우의 수
        long[] preSumCnt = new long[m];
        st = new StringTokenizer(br.readLine());
        long sum = 0;
        for(int i=0; i<n; i++){
            sum += Integer.parseInt(st.nextToken());
            int remainder = (int)(sum % m);
            preSumCnt[remainder]++;
        }
        long rst = 0;
        rst += preSumCnt[0];
        // 같은 나머지끼리 조합하여 만들 수 있는 개수 rst에 누적
        for(int i=0; i<m; i++){
            long count = preSumCnt[i];
            // nc2 = n x n-1 / 2
            if(count >= 2) rst += count * (count - 1) / 2;
        }
        System.out.print(rst);
    }
}