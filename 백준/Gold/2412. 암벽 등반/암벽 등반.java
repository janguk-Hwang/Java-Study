import java.io.*;
import java.util.*;

// visited를 문자열로
public class Main {
    static int n, t;
    static Map<Integer, Set<Integer>> map = new HashMap<>();
    static Set<String> visited = new HashSet<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map.putIfAbsent(y, new HashSet<>());
            map.get(y).add(x);
        }
        bfs();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        visited.add(key(0, 0));
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.y == t){
                System.out.print(cur.time);
                return;
            }
            for(int nx=cur.x-2; nx<=cur.x+2; nx++){
                for(int ny=cur.y-2; ny<=cur.y+2; ny++){
                    if(!map.containsKey(ny)) continue;
                    if(!map.get(ny).contains(nx)) continue;
                    String k = key(nx, ny);
                    if(visited.contains(k)) continue;
                    visited.add(k);
                    q.add(new Node(nx, ny, cur.time + 1));
                }
            }
        }
        System.out.print(-1);
    }

    static String key(int x, int y) {
        return x + "," + y;
    }

    static class Node {
        int x, y, time;
        Node(int x, int y, int time) {
            this.x = x; this.y = y; this.time = time;
        }
    }
}