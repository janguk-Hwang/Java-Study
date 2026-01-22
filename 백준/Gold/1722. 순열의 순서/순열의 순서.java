import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] visited;
    static long[] factorial;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        factorial = new long[n + 1];
        factorial[0] = 1L;
        for(int i=1; i<=n; i++) factorial[i] = factorial[i - 1] * i;
        st = new StringTokenizer(br.readLine());
        if(Integer.parseInt(st.nextToken()) == 1){
            long k = Long.parseLong(st.nextToken());
            List<Integer> list = new ArrayList<>();
            for(int i=1; i<=n; i++) list.add(i);
            for(int i=n; i>=1; i--){
                long bundle = factorial[i-1];
                int idx = (int)((k - 1) / bundle);
                int digit = list.get(idx);
                sb.append(digit).append(" ");
                k = (k - 1) % bundle + 1;
                list.remove(idx);
            }
            sb.setLength(sb.length()-1);
            System.out.print(sb);
            System.exit(0);
        }
        else{
            arr = new int[n];
            for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
            visited = new boolean[n + 1];
            long ord = 1L;
            for(int i=0; i<n; i++){
                int cur = arr[i];
                int cnt = 0;
                for(int j=1; j<cur; j++) if(!visited[j]) cnt++;
                int remain = n - i - 1;
                ord += cnt * factorial[remain];
                visited[cur] = true;
            }
            System.out.print(ord);
        }
    }
}