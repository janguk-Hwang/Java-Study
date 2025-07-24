import java.io.*;
import java.util.*;

// 수학적 or 그리디 x
// 가장 빠른 시간을 구해야 하고, 가장 빠른 시간으로 동생을 찾는 방법의 수를 출력해야 하므로 bfs로 진행
public class Main {
    static int n, k;
    static int[] time, cnt;
    static Queue<Integer> q = new LinkedList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        time = new int[100_001];    // 해당 위치까지의 최단 시간
        cnt = new int[100_001];     // 해당 위치까지 최단 시간으로 도달한 경우의 수
        Arrays.fill(time, -1);
        q.offer(n); // 수빈 위치
        time[n] = 0;
        cnt[n] = 1;
        while(!q.isEmpty()){
            int now = q.poll();
            for(Integer next : new int[]{now - 1, now + 1, now * 2}){
                if(next < 0 || next > 100000) continue;
                // 처음 방문하는 경우
                if(time[next] == -1){
                    time[next] = time[now] + 1;
                    // 먼저 도달한 경로가 항상 가장 빠른 시간
                    cnt[next] = cnt[now];
                    q.offer(next);
                    continue;
                }
                if(time[next] == time[now] + 1){
                    cnt[next] += cnt[now];
                }
            }
        }
        System.out.println(time[k]);
        System.out.print(cnt[k]);
    }
}