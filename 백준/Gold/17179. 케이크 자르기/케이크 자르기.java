import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> list = new ArrayList<>();
    static List<Integer> qList = new ArrayList<>();
    static int n, m, l;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        for(int i=0; i<m; i++){
            int temp = Integer.parseInt(br.readLine());
            list.add(temp);
        }
        list.add(0); list.add(l);
        list.sort(null);
        for(int i=0; i<n; i++) qList.add(Integer.parseInt(br.readLine()));
        for(int q : qList){
            int start = 0; int end = l;
            while(start + 1 < end){
                int mid = (start + end) / 2;
                if(isPossible(mid, q)) end = mid;
                else start = mid;
            }
            sb.append(start).append("\n");
        }
        System.out.print(sb);
    }

    // 각 조각은 mid보다 더 커야 함
    static boolean isPossible(int mid, int q){
        int cnt = 0;
        int prev = list.get(0);
        for(int i=1; i<list.size(); i++){
            int cur = list.get(i);
            if((cur - prev) >= mid){
                prev = cur;
                cnt++;
            }
            if(cnt - 1 >= q) return false;
        }
        return true;
    }
}