import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int[] sensor;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        // 모든 센서가 적어도 하나의 집중국과는 통신을 해야 하므로 첫 센서에서 마지막 센서까지의 길이에 가장 넓은 폭을 k-1개만큼 빼면된다.
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        if(k >= n){
            System.out.println(0);
            return;
        }
        sensor = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) sensor[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(sensor);
        for(int i=1; i<n; i++) pq.add(sensor[i] - sensor[i-1]);
        int total = sensor[n-1] - sensor[0];
        for(int i=0; i<k-1; i++) total -= pq.poll();
        System.out.println(total);
    }
}