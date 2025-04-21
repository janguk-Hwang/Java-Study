import java.io.*;
import java.util.*;
public class Main {
    static int n, k, total;
    static int[] tall, diff;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        tall = new int[n]; diff = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            tall[i] = Integer.parseInt(st.nextToken());
            if(i > 0) {
                diff[i] = tall[i] - tall[i-1];
                pq.add(diff[i]);
            }
        }
        total = tall[n-1] - tall[0];
        for(int i=0; i<k-1; i++) total -= pq.poll();
        System.out.println(total);
    }
}