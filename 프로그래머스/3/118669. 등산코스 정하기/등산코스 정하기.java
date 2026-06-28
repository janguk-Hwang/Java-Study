import java.util.*;

class Solution {
    static List<Node>[] adj;
    static boolean[] isGate;
    static boolean[] isSummit;
    static int[] intensity;
    static PriorityQueue<Node> pq;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        // 양방향
        for(int[] path : paths){
            adj[path[0]].add(new Node(path[1], path[2]));
            adj[path[1]].add(new Node(path[0], path[2]));
        }
        isGate = new boolean[n + 1];
        for (int gate : gates) isGate[gate] = true;
        isSummit = new boolean[n + 1];
        for (int summit : summits) isSummit[summit] = true;
        intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        pq = new PriorityQueue<>();
        for(int gate : gates){
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0;
        }
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.cost > intensity[cur.dest]) continue;
            if(isSummit[cur.dest]) continue;
            for(Node next : adj[cur.dest]){
                if(isGate[next.dest]) continue;
                int max = Math.max(cur.cost, next.cost);
                if(max < intensity[next.dest]){
                    intensity[next.dest] = max;
                    pq.offer(new Node(next.dest, max));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int minSummit = Integer.MAX_VALUE;
        Arrays.sort(summits);
        for(int i : summits){
            if(intensity[i] < min){
                min = intensity[i];
                minSummit = i;
            }
        }
        return new int[]{minSummit, min};
    }
    
    static class Node implements Comparable<Node>{
        int dest; int cost;
        Node(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}