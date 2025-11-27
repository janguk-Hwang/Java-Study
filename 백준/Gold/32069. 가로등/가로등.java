import java.io.*;
import java.util.*;

public class Main {
    static Queue<long[]> q = new LinkedList<>();
    static Set<Long> visited = new HashSet<>();
    static long l;
    static int n, k;
    static long[] d = {-1, 1};
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        l = Long.parseLong(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            long a = Long.parseLong(st.nextToken());
            q.add(new long[]{a, 0});
            visited.add(a);
        }
        while(!q.isEmpty()){
            long[] cur = q.poll();
            long pos = cur[0];
            long dist = cur[1];
            sb.append(dist).append("\n");
            if(--k == 0){
                System.out.print(sb);
                return;
            }
            for(int i=0; i<2; i++){
                long nextPos = pos + d[i];
                if(nextPos < 0 || nextPos > l) continue;
                if(!visited.contains(nextPos)){
                    visited.add(nextPos);
                    q.add(new long[]{nextPos, dist + 1});
                }
            }
        }
        System.out.print(sb);
    }
}