import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Long> pq;
    static int t;
    static long sum;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            sum = 0;
            pq = new PriorityQueue<>();
            int k = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<k; i++) pq.offer(Long.parseLong(st.nextToken()));
            while(pq.size() > 1){
                long temp1 = pq.poll();
                long temp2 = pq.poll();
                sum += temp1 + temp2;
                pq.offer(temp1 + temp2);
            }
            sb.append(sum).append("\n");
        }
        System.out.print(sb);
    }
}