import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Node>> grpah = new ArrayList<>();
    static int dist[];
    static int V,E,P;
    static boolean solution = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        dist = new int[V+1];
        for (int i = 0; i <= V ; i++) {
            grpah.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            grpah.get(start).add(new Node(end,cost));
            grpah.get(end).add(new Node(start,cost));
        }

        djs(1);
        int tempD = dist[V];
        int temp = dist[P];
        djs(P);
        int temp2 = dist[V];

        if(tempD == (temp + temp2)){
            System.out.println("SAVE HIM");
        }
        else{
            System.out.println("GOOD BYE");
        }

    }

    static void djs(int start){
        Arrays.fill(dist,Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> {
            return o1.cost - o2.cost;
        });

        queue.add(new Node(start,0));
        dist[start] = 0;

        while (!queue.isEmpty()){
            Node now = queue.poll();

            for(Node next : grpah.get(now.vertex)){
                if(dist[next.vertex] > dist[now.vertex] + next.cost){
                    dist[next.vertex] = dist[now.vertex] + next.cost;
                    queue.add(new Node(next.vertex,dist[next.vertex]));
                }
            }
        }
    }

    static class Node{
        int vertex;
        int cost;

        public Node(int vertex,int cost){
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}