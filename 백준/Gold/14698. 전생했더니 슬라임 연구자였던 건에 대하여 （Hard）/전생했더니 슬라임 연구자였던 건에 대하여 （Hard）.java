import java.io.*;
import java.util.*;

// 1000000010, 1000000008 를 곱해서 pq에 넣는 것과
// 곱을 MOD로 나눈 나머지을 pq 넣는 것의 차이는?
// MOD로 나눈 나머지를 pq에 넣으면 원래 더 큰 수지만
// MOD로 나눈 나머지가 다른 수보다 작아져서 곱셈의 순서가 달라진다.
// 문제에서 끝까지 합성하고 난 후에 생기는 슬라임의 에너지의 양이
// 2 × 10^18 이하라는 것이 보장된다고 했으므로 오버플로우는 발생하지 않는다.
public class Main {
    static final int MOD = 1_000_000_007;
    static int t, n;
    static PriorityQueue<Long> pq;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            if(n == 1){
                br.readLine();
                sb.append(1).append("\n");
                continue;
            }
            st = new StringTokenizer(br.readLine());
            pq = new PriorityQueue<>();
            for(int i=0; i<n; i++) pq.offer(Long.parseLong(st.nextToken()));
            long sum = 1;
            while(pq.size() > 1){
                long a = pq.poll();
                long b = pq.poll();
                // 발생하는 에너지 양
                long temp = (a % MOD) * (b % MOD) % MOD;
                // 필요한 누적 에너지(누적 곱)
                sum = (sum * temp) % MOD;
                pq.offer(a*b);
            }
            sb.append(sum).append("\n");
        }
        System.out.print(sb);
    }
}