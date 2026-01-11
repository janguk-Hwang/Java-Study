import java.io.*;
import java.util.*;

// dist[i]처럼 노드 i까지의 거리만 관리하는 것이 아니라 dist[i][k]로 노드 i까지 부스터를 몇 개 썼는지도 관리해야 함.
public class Main {
    static final Double INF = Double.MAX_VALUE;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int n, k;
    static double rst = INF;
    static int[][] matrix;
    static double[][] dist;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        dist = new double[n][k + 1];    // [도시 번호][포션 사용 횟수]
        for(int i=0; i<n; i++){
            String temp = br.readLine();
            for(int j=0; j<n; j++) matrix[i][j] = temp.charAt(j) - '0';
        }
        for(int i=0; i<n; i++) Arrays.fill(dist[i], INF);
        dist[0][0] = 0;
        pq.add(new Node(0, 0, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int v = cur.v;
            int curK = cur.k;
            double distance = cur.dist;
            if(distance > dist[v][curK]) continue;
            // 인접리스트 순회(여기서는 모든 노드)
            for(int i=0; i<n; i++){
                if(i == v) continue;
                // 포션을 사용하지 않음
                double nDist = distance + matrix[v][i];
                if(nDist < dist[i][curK]){
                    dist[i][curK] = nDist;
                    pq.add(new Node(i, curK, nDist));
                }
                // 포션을 사용
                if(curK < k){
                    nDist = distance + matrix[v][i] / 2.0;
                    if(nDist < dist[i][curK + 1]){
                        dist[i][curK + 1] = nDist;
                        pq.add(new Node(i, curK + 1, nDist));
                    }
                }
            }
        }
        for(int i=0; i<=k; i++) rst = Math.min(rst, dist[1][i]);
        System.out.print(rst);
    }

    static class Node implements Comparable<Node>{
        int v; int k; double dist;
        Node(int v, int k, double dist){
            this.v = v; this.k = k; this.dist = dist;
        }
        @Override
        public int compareTo(Node o){
            return Double.compare(this.dist, o.dist);
        }
    }
}