import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] hi, arc;
    static StringBuilder sb = new StringBuilder();
    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        hi = new int[n+1]; arc = new int[m+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++) hi[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=m; i++){
            arc[i] = Integer.parseInt(st.nextToken());
            hashMap.put(arc[i], hashMap.getOrDefault(arc[i], 0) + 1);
        }
        Arrays.sort(hi);
        Arrays.sort(arc);

        long hiWin = 0;
        long draw = 0;
        // 결정 문제: hi가 arc를 이기는가?
        for(int i=1; i<=n; i++){
            int start = 0;
            int end = m + 1;
            while(start + 1 < end){
                int mid = (start + end) / 2;
                // start는 hi[i]보다 작은 arc[]중 가장 끝 인덱스
                if(arc[mid] < hi[i]) start = mid;
                else end = mid;
            }
            hiWin += start;
            draw += hashMap.getOrDefault(hi[i], 0);
        }
        long total = (long) n * m;
        long arcWin = total - hiWin - draw;
        sb.append(hiWin).append(" ").append(arcWin).append(" ").append(draw);
        System.out.print(sb);
    }
}