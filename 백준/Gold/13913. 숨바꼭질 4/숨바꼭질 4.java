import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100_001;
    static int[] time;
    static int[] prev;
    static int n, k;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        time = new int[MAX];
        prev = new int[MAX];
        Arrays.fill(time, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        time[n] = 0;
        prev[n] = -1;

        while(!q.isEmpty()){
            int now = q.poll();
            // 현재 위치에서 갈 수 있는 곳들에 대해 시간을 기록, 경로 추적을 위해 이전 노드 기록
            for(int next : new int[]{now - 1, now + 1, now * 2}){
                if(next < 0 || next >= MAX) continue;
                // 처음 방문하는 곳인 경우
                if(time[next] == -1){
                    time[next] = time[now] + 1;
                    prev[next] = now;
                    q.offer(next);
                }
            }
        }

        System.out.println(time[k]);
        List<Integer> route = new ArrayList<>();
        for(int i=k; i != -1; i=prev[i]) route.add(i);
        Collections.reverse(route);
        for(Integer i : route) sb.append(i).append(" ");
        System.out.print(sb);
    }
}