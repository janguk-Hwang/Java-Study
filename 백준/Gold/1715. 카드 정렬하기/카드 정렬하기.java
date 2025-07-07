import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            int temp = Integer.parseInt(br.readLine());
            pq.offer(temp);
        }
        int total = 0;
        for(int i=0; i<n-1; i++){
            int num1 = 0;
            int num2 = 0;
            while(pq.size() >= 2){
                num1 = pq.poll();
                num2 = pq.poll();
                total += num1 + num2;
                pq.offer(num1 + num2);
            }
        }
        System.out.print(total);
    }
}