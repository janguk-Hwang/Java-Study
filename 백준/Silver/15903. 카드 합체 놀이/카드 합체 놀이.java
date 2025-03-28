import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static int n, m;
    static long result;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        // 항상 가장 작은 값끼리 더해야 n장의 카드의 총 합이 최소가 된다.
        for(int i=0; i<n; i++){
            pq.add(Long.parseLong(st.nextToken()));
        }

        // n이 2이상이라고 문제에서 제한했기 때문에 NullPointerException 발생 x
        for (int i = 0; i < m; i++) {
            long sum = pq.poll() + pq.poll();
            for(int j=0; j<2; j++){
                pq.add(sum);
            }
        }

        while(!pq.isEmpty()){
            result += pq.poll();
        }

        System.out.print(result);
    }
}
