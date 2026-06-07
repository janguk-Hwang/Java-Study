import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int r; int c; int dist; long talkTime;
        public Node(int r, int c, int dist, long talkTime){
            this.r = r; this.c = c; this.dist = dist; this.talkTime = talkTime;
        }
        @Override
        public int compareTo(Node o){
            // 우선순위1: 경로의 길이
            // 우선순위2: 친구들과 나눠야 하는 대화 시간의 합
            if(this.dist != o.dist) return Integer.compare(this.dist, o.dist);
            return Long.compare(this.talkTime, o.talkTime);
        }
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static long[][] time;
    static final long INF = Long.MAX_VALUE;
    public int[] solution(int m, int n, int s, int[][] time_map) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        time = new long[m][n];
        for(int i=0; i<m; i++) Arrays.fill(time[i], INF);
        pq.offer(new Node(0, 0, 0, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            // 해당 좌표에서 이전에 기록된 최소 대화 누적 시간보다 현재까지의 누적 시간이 더 크면 continue
            if(cur.talkTime >= time[cur.r][cur.c]) continue;
            time[cur.r][cur.c] = cur.talkTime;
            // 우선순위 큐이므로 처음 도달한 것이 최적의 해
            if(cur.r == m-1 && cur.c == n-1) return new int[]{cur.dist, (int)cur.talkTime};
            for(int d=0; d<4; d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr >= 0 && nr < m && nc >= 0 && nc < n){
                    // 파티 테이블으로 이동 불가
                    if(time_map[nr][nc] == -1) continue;
                    long next = cur.talkTime + time_map[nr][nc];
                    // 대화 시간이 s를 넘지 않는 최소 경로를 찾은 경우 큐에 삽입
                    if(next <= s && next < time[nr][nc]){
                        pq.offer(new Node(nr, nc, cur.dist + 1, next));
                    }
                }
            }
        }
        return new int[]{0, 0};
    }
}