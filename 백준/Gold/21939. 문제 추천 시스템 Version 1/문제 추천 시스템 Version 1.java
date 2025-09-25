import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static TreeSet<Problem> set = new TreeSet<>();
    static Map<Integer, Integer> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            set.add(new Problem(p, l));
            map.put(p, l);
        }
        m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("recommend")){
                int x = Integer.parseInt(st.nextToken());
                if(x == 1) sb.append(set.last().p).append("\n");
                else sb.append(set.first().p).append("\n");
                continue;
            }
            if(cmd.equals("add")){
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                set.add(new Problem(p, l));
                map.put(p, l);
                continue;
            }
            if(cmd.equals("solved")){
                int p = Integer.parseInt(st.nextToken());
                int l = map.get(p);
                set.remove(new Problem(p, l));
                map.remove(p);
            }
        }
        System.out.print(sb);
    }

    static class Problem implements Comparable<Problem>{
        int p; int l;
        Problem(int p, int l){
            this.p = p; this.l = l;
        }
        @Override
        public int compareTo(Problem o){
            if(this.l == o.l) return this.p - o.p;
            return this.l - o.l;
        }
    }
}