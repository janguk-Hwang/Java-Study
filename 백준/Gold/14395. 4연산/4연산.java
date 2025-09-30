import java.io.*;
import java.util.*;

public class Main {
    static Set<Long> visited = new HashSet<>();
    static long s, t;
    static char[] op = {'+', '-', '*', '/'};
    static Map<Long, String> ma;
    static Queue<Map<Long, String>> q = new LinkedList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        s = Long.parseLong(st.nextToken());
        t = Long.parseLong(st.nextToken());
        if(s == t){
            System.out.print(0);
            return;
        }
        Map<Long, String> map = new HashMap<>();
        map.put(s, "");
        q.add(map);
        while(!q.isEmpty()){
            Map<Long, String> m = q.poll();
            for(Map.Entry<Long, String> entry : m.entrySet()){
                Long tempI = entry.getKey();
                String tempS = entry.getValue();
                if(visited.contains(tempI)) continue;
                visited.add(tempI);
                if(tempI == t){
                    System.out.print(tempS);
                    return;
                }

                if(tempI < t){
                    ma = new HashMap<>();
                    ma.put(tempI * tempI, tempS + "*");
                    q.offer(ma);
                }

                if(tempI < t){
                    ma = new HashMap<>();
                    ma.put(tempI * 2, tempS + "+");
                    q.offer(ma);
                }

                ma = new HashMap<>();
                ma.put(0L, tempS + "-");
                q.offer(ma);

                if(tempI != 0){
                    ma = new HashMap<>();
                    ma.put(1L, tempS + "/");
                    q.offer(ma);
                }
            }
        }
        System.out.print(-1);
    }
}