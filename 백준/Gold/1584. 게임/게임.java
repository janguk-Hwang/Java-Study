import java.io.*;
import java.util.*;

// 들어갈 수 없는 죽음의 영역, 생명이 감소되는 위험한 구역
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] matrix = new int[501][501];
    static int[][] dist = new int[501][501];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<501; i++) Arrays.fill(dist[i], INF);
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int x=Math.min(x1, x2); x<=Math.max(x1, x2); x++){
                for(int y=Math.min(y1, y2); y<=Math.max(y1, y2); y++){
                    matrix[x][y] = 1;
                }
            }
        }
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int x=Math.min(x1, x2); x<=Math.max(x1, x2); x++){
                for(int y=Math.min(y1, y2); y<=Math.max(y1, y2); y++){
                    matrix[x][y] = -1;
                }
            }
        }
        int rst = dijkstra();
        System.out.print(rst == INF ? -1 : rst);
    }

    public static int dijkstra(){
        dist[0][0] = 0;
        pq.offer(new Node(0, 0, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.cost > dist[cur.x][cur.y]) continue;
            if(cur.x == 500 && cur.y == 500) return cur.cost;
            for(int d=0; d<4; d++){
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if(nx < 0 || nx >= 501 || ny < 0 || ny >= 501) continue;
                // 죽음 영역은 불가
                if(matrix[nx][ny] == -1) continue;
                int nextCost = matrix[nx][ny] == 1 ? 1: 0;
                if(dist[nx][ny] > cur.cost + nextCost){
                    dist[nx][ny] = cur.cost + nextCost;
                    pq.offer(new Node(nx, ny, cur.cost + nextCost));
                }
            }
        }
        return INF;
    }

    public static class Node implements Comparable<Node>{
        int x, y, cost;
        Node(int x, int y, int cost){
            this.x = x; this.y = y; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}