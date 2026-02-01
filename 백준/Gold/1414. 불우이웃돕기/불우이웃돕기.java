import java.io.*;
import java.util.*;

public class Main {
    static int n, total;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
        for(int i=0; i<n; i++){
            String line = br.readLine();
            for(int j=0; j<n; j++){
                int cost = charToInt(line.charAt(j));
                total += cost;
                if(i != j && cost > 0) pq.offer(new Node(i, j, cost));
            }
        }
        int sum = 0;
        int edgeCnt = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(find(cur.from) != find(cur.to)){
                union(cur.from, cur.to);
                sum += cur.cost;
                edgeCnt++;
            }
        }
        if(n == 1){
            System.out.println(total);
            return;
        }
        if(edgeCnt == n-1) System.out.println(total - sum);
        else System.out.println(-1);
    }

    static int charToInt(char c){
        if(c == 0) return 0;
        if('a' <= c && c <= 'z') return c - 'a' + 1;
        if('A' <= c && c <= 'Z') return c - 'A' + 27;
        return 0;
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static class Node{
        int from; int to; int cost;
        Node(int from, int to, int cost){
            this.from = from; this.to = to; this.cost = cost;
        }
    }
}