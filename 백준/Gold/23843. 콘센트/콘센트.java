import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> chargeEndTime = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++){
            pq.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<m && !pq.isEmpty(); i++){
            chargeEndTime.add(pq.poll());
        }

        while(!pq.isEmpty()){
            chargeEndTime.add(chargeEndTime.poll() + pq.poll());
        }

        System.out.println(Collections.max(chargeEndTime));
    }
}