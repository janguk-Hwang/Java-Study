import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) pq.add(Integer.parseInt(br.readLine()));
        while(!pq.isEmpty()) sb.append(pq.poll()).append("\n");
        System.out.print(sb);
    }
}