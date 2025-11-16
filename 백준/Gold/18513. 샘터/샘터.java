import java.io.*;
import java.util.*;

public class Main {
    static int[] d = {-1, 1};
    static Set<Integer> visited = new HashSet<>();
    static Queue<int[]> q = new LinkedList<>();
    static int n, k, build;
    static long unhappySum;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        build = 0; unhappySum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int pos = Integer.parseInt(st.nextToken());
            visited.add(pos);
            // {샘터의 좌표, 해당 샘터까지의 거리}
            q.add(new int[]{pos, 0});
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i=0; i<2; i++){
                int nPos = cur[0] + d[i];
                if(visited.contains(nPos)) continue;
                visited.add(nPos);
                unhappySum += cur[1] + 1;
                build++;
                if(build == k){
                    System.out.print(unhappySum);
                    return;
                }
                q.add(new int[]{nPos, cur[1] + 1});
            }
        }
    }
}
// 2,500,050,000