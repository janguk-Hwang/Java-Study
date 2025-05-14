import java.util.*;
import java.io.*;

// (6, 13) => 작년에는 팀13이 높았는데 올해는 6이 높다  => 앞 숫자가 선행 조건
// 확실한 올해 순위를 만들 수 없는 경우가 있을 수도 있고, 일관성이 없는 잘못된 정보일 수도 있다.
public class Main {
    static int t, n, m;
    static int[] last, indegree;
    static List<Integer>[] adj;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for(int k=0; k<t; k++){
            n = Integer.parseInt(br.readLine());
            indegree = new int[n+1];
            adj = new ArrayList[n+1];
            for(int i=1; i<=n; i++) adj[i]= new ArrayList<>();
            last = new int[n+1];
            st = new StringTokenizer(br.readLine(), " ");
            for(int i=1; i<=n; i++) last[i] = Integer.parseInt(st.nextToken());
            for(int i=1; i<n; i++){
                for(int j=i+1; j<=n; j++){
                    adj[last[i]].add(last[j]);
                    indegree[last[j]]++;
                }
            }
            m = Integer.parseInt(br.readLine());
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                // 기존 선행조건을 벗어나고 역으로 선행조건 추가
                if(adj[s].contains(e)){
                    adj[s].remove((Integer)e);
                    indegree[e]--;
                    adj[e].add(s);
                    indegree[s]++;
                }
                else{
                    adj[e].remove((Integer)s);
                    indegree[s]--;
                    adj[s].add(e);
                    indegree[e]++;
                }
            }
            Queue<Integer> q = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            boolean flag = false;
            for(int i=1; i<=n; i++) if(indegree[i] == 0) q.offer(i);
            while(!q.isEmpty()){
                if(q.size() > 1){
                    flag = true;
                    break;
                }
                int now = q.poll();
                list.add(now);
                for(int next : adj[now]){
                    indegree[next]--;
                    if(indegree[next] == 0) q.offer(next);
                }
            }
            // 사이클이 생긴 경우: 데이터에 일관성이 없어서 순위를 정할 수 없는 경우에는 "IMPOSSIBLE"을 출력한다.
            // 확실한 순위를 찾을 수 없다면: "?"를 출력한다. => 정답이 여러 개가 되는 경우
            if(list.size() != n) sb.append("IMPOSSIBLE").append("\n");
            else if(flag) sb.append("?").append("\n");
            else{
                for(Integer i : list){
                    sb.append(i).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}