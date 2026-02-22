import java.io.*;
import java.util.*;

// 출발하고 도착했을 때, 시간이 감소되려면 중간에 음수 사이클이 존재해야 한다.
// 일반적인 최단 경로 알고리즘에서는 n-1인 최대 간선의 개수만큼만 간선 완화를 하고 나서는 더 이상 간선 완화가 발생하지 않는다.
// 그런데 그 이후에도 간선 완화가 발생하면 음수 사이클이 있다는 것이다.
public class Main {
    static List<Node> adj;
    static int[] dist;
    static int t, n, m, w;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            adj = new ArrayList<>();
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                adj.add(new Node(s, e, t));
                adj.add(new Node(e, s, t));
            }
            for(int i=0; i<w; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                adj.add(new Node(s, e, -t));        // 시작, 도착, 감소하는 시간
            }
            boolean flag = false;
            dist = new int[n + 1];
            // n번 반복
            for(int i=1; i<=n; i++){
                for(Node node : adj){
                    int from = node.from;
                    int to = node.to;
                    int cost = node.cost;
                    // 최단 경로 업데이트
                    if(dist[from] + cost < dist[to]) {
                        dist[to] = dist[from] + cost;
                        // n번째에서도 간선 완화가 발생했으면 음수 사이클 존재를 의미
                        if(i == n) flag = true;
                    }
                }
            }
            sb.append(flag ? "YES\n" : "NO\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }

    static class Node{
        int from, to, cost;
        Node(int from, int to, int cost){
            this.from = from; this.to = to; this.cost = cost;
        }
    }
}