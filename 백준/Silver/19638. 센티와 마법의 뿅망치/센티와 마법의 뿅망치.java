import java.io.*;
import java.util.*;

public class Main {
    static int n, h, t;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++) pq.offer(Integer.parseInt(br.readLine()));
        int cnt = 0;
        while(t-- > 0){
            if(pq.peek() < h){
                sb.append("YES").append("\n").append(cnt);
                System.out.print(sb);
                return;
            }
            int temp = pq.poll();
            if(temp == 1){
                pq.offer(temp);
                break;
            }
            pq.offer(temp / 2);
            cnt++;
        }
        if(sb.length() == 0){
            sb.append((pq.peek() < h ? "YES\n" + cnt : "NO\n" + pq.peek()));
            System.out.print(sb);
        }
    }
}