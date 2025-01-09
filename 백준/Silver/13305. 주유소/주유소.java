import java.io.*;
import java.util.*;
import java.lang.Math;

public class Main {
    static int[] dist;
    static int[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dist = new int[N-1];
        cost = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int minCost = Integer.MAX_VALUE;

        for(int i = 0; i < N - 1; i++) {
            if(cost[i] < minCost) {
                minCost = cost[i];
            }
            sum += (minCost*dist[i]);
        }

        System.out.println(sum);
    }
}