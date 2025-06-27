import java.io.*;
import java.util.*;

public class Main {
    static int[] c;
    static ArrayList<Integer>[] adj;
    static int n, m, t;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        c = new int[t];
        adj = new ArrayList[n+1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adj[s].add(e);
            adj[e].add(s);
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<t; i++) c[i] = Integer.parseInt(st.nextToken());
        System.out.print(func());
    }

    public static int func(){
        Queue<Node> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n+1][t][2];     // node 번호, time % t, 오리접촉여부
        if(c[0] == 1){
            q.add(new Node(1, 0, 1));
            visited[1][0][1] = true;
        }
        if(c[0] != 1){
            q.add(new Node(1, 0, 0));
            visited[1][0][0] = true;
        }
        while(!q.isEmpty()){
            Node now = q.poll();
            int node = now.node;
            int time = now.time;
            int meetDuck = now.meetDuck;
            if(node == n) return time;  // n번 노드 도착하면 시간 반환하고 종료
            int nextTime = time + 1;
            int nextTimeMod = nextTime % t;
            int nextTimeDuck = c[nextTimeMod];
            // 오리 만난 경우
            if(meetDuck == 1){
                int nextNode = nextTimeDuck;
                if(!visited[nextNode][nextTimeMod][1]){
                    visited[nextNode][nextTimeMod][1] = true;
                    q.add(new Node(nextNode, nextTime, 1));
                }
            }
            // 오리 안 만난 경우
            if(meetDuck == 0){
                // 이동
                for(Integer nextNode : adj[node]){
                    int tempMeet = 0;
                    // 다음 이동할 칸에 오리가 있는 경우
                    if(nextNode == nextTimeDuck) tempMeet = 1;
                    // 방문 안되어 있으면 큐에 추가
                    if(!visited[nextNode][nextTimeMod][tempMeet]){
                        visited[nextNode][nextTimeMod][tempMeet] = true;
                        q.add(new Node(nextNode, nextTime, tempMeet));
                    }
                }
                // 쉬기
                int tempMeet = 0;
                // 내가 지금 쉬는 곳에 다음 시간에 오리가 오면
                if(node == nextTimeDuck) tempMeet = 1;
                if(!visited[node][nextTimeMod][tempMeet]){
                    visited[node][nextTimeMod][tempMeet] = true;
                    q.add(new Node(node, nextTime, tempMeet));
                }
            }
        }
        return -1;
    }

    public static class Node{
        int node; int time; int meetDuck;
        public Node(int node, int time, int meetDuck){
            this.node = node; this.time = time; this.meetDuck = meetDuck;
        }
    }
}