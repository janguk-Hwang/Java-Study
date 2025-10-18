import java.io.*;
import java.util.*;

public class Main {
    static String goal;
    static int n, k;
    static Queue<Element> q = new LinkedList<>();
    static Set<String> visited = new HashSet<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) sb.append(i);
        goal = sb.toString();
        st = new StringTokenizer(br.readLine());
        sb.setLength(0);
        for(int i=0; i<n; i++) sb.append(Integer.parseInt(st.nextToken()));
        String start = sb.toString();
        System.out.print(bfs(start));
    }

    static int bfs(String start){
        q.add(new Element(start, 0));
        visited.add(start);
        while(!q.isEmpty()){
            Element cur = q.poll();
            if(cur.s.equals(goal)) return cur.time;
            for(int i=0; i<=n-k; i++){
                String sReverse = reverse(cur.s, i, i+k-1);
                if(visited.contains(sReverse)) continue;
                visited.add(sReverse);
                q.add(new Element(sReverse, cur.time + 1));
            }
        }
        return -1;
    }

    static String reverse(String s, int start, int end){
        StringBuilder sb = new StringBuilder();
        sb.append(s, 0, start);     // 뒤집기를 시작할 이전 문자열 추가
        for(int i=end; i>=start; i--) sb.append(s.charAt(i));
        sb.append(s, end + 1, s.length());      // 뒤집기 구간 이후 문자열 추가
        return sb.toString();
    }

    static class Element {
        String s; int time;
        Element(String s, int time) {
            this.s = s; this.time = time;
        }
    }
}