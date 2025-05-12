import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] indegree, time, result;
    static List<Integer>[] graph;
    static Queue<Integer> q = new LinkedList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        time = new int[n+1];
        indegree = new int[n+1];
        result = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            time[i] = Integer.parseInt(st.nextToken());
            int temp = Integer.parseInt(st.nextToken());
            for(int j=0; j<temp; j++){
                int pre = Integer.parseInt(st.nextToken());
                graph[pre].add(i);
                indegree[i]++;
            }
        }
        for(int i=1; i<=n; i++){
            if(indegree[i] == 0){
                q.offer(i);
                result[i] = time[i];
            }
        }
        while(!q.isEmpty()){
            int now = q.poll();
            for(int next : graph[now]){
                result[next] = Math.max(result[next], result[now] + time[next]);
                indegree[next]--;
                if(indegree[next] == 0) q.offer(next);
            }
        }

        int answer = 0;
        for(int i=1; i<=n; i++) answer = Math.max(answer, result[i]);
        System.out.print(answer);
    }
}