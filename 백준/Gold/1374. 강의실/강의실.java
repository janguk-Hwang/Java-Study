import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] arr;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        int rst = 0;
        for(int i=0; i<n; i++){
            int left = arr[i][0];
            int right = arr[i][1];
            if(!pq.isEmpty() && pq.peek() <= left) pq.poll();
            pq.offer(right);
            rst = Math.max(rst, pq.size());
        }
        System.out.print(rst);
    }
}