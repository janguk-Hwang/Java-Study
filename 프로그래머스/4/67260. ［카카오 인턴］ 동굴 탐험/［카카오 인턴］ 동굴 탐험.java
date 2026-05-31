// 각 방들은 양방향으로 통행이 가능한 통로로 서로 연결되어 있는데, 서로 다른 두 방을 직접 연결하는 통로는 오직 하나입니다. 임의의 서로 다른 두 방 사이의 최단경로는 딱 한 가지만 있으며, 또한 임의의 두 방 사이에 이동이 불가능한 경우는 없습니다.
// 연결 그래프, 사이클 존재 X -> 트리 구조
// 위상정렬(DAG)

import java.util.*;

class Solution {
    static ArrayList<Integer>[] adj;
    static ArrayList<Integer>[] DAdj;
    static int[] indegree;
    static Queue<Integer> q;
    static boolean[] visited;
    public boolean solution(int n, int[][] path, int[][] order) {
        adj = new ArrayList[n];
        DAdj = new ArrayList[n];
        indegree = new int[n];
        for(int i=0; i<n; i++){
            adj[i] = new ArrayList<>();
            DAdj[i] = new ArrayList<>();
        }
        // 양방향 경로 저장
        for(int[] i : path){
            adj[i[0]].add(i[1]);
            adj[i[1]].add(i[0]);
        }
        q = new LinkedList<>();
        visited = new boolean[n];
        q.add(0);
        visited[0] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : adj[cur]){
                if(!visited[next]){
                    visited[next] = true;
                    DAdj[cur].add(next);
                    indegree[next]++;
                    q.add(next);
                }
            }  
        }
        
        for(int[] i : order){
            int prev = i[0];
            int nxt = i[1];
            DAdj[prev].add(nxt);
            indegree[nxt]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int cnt = 0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            cnt++;
            for(int next : DAdj[cur]){
                indegree[next]--;
                if(indegree[next] == 0) queue.add(next);
            }
        }
        return cnt == n;
    }
}












