import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[] impossible;
    static boolean[][] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        impossible = new boolean[n + 1];
        for(int i=0; i<m; i++){
            int x = Integer.parseInt(br.readLine());
            impossible[x] = true;
        }
        visited = new boolean[n + 1][(int)Math.sqrt(2 * n) + 2];
        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 0, 0));
        visited[1][0] = true;
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.pos == n) return cur.cnt;
            for(int i = cur.speed-1; i <= cur.speed+1; i++){
                if(i < 1) continue;
                int next = cur.pos + i;
                if(next > n) continue;
                if(impossible[next]) continue;
                if(visited[next][i]) continue;
                visited[next][i] = true;
                q.add(new Node(next, i, cur.cnt + 1));
            }
        }
        return -1;
    }

    static class Node{
        int pos, speed, cnt;
        Node(int pos, int speed, int cnt){
            this.pos = pos;
            this.speed = speed;
            this.cnt = cnt;
        }
    }
}