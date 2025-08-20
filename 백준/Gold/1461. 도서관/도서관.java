import java.io.*;
import java.util.*;

public class Main {
    static int n, m, rst;
    static List<Integer> left = new ArrayList<>();
    static List<Integer> right = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int t = Integer.parseInt(st.nextToken());
            if(t < 0){
                left.add(-t);
                continue;
            }
            right.add(t);
        }
        Collections.sort(left, Collections.reverseOrder());
        Collections.sort(right, Collections.reverseOrder());
        rst = 0;
        int max = 0;
        // m개씩 들고 왕복(음수)
        for(int i=0; i<left.size(); i+=m){
            rst += left.get(i) * 2;
            max = Math.max(max, left.get(i));
        }
        // (양수)
        for(int i=0; i<right.size(); i+=m){
            rst += right.get(i) * 2;
            max = Math.max(max, right.get(i));
        }
        // 마지막은 돌아오지 않아도 됨
        rst -= max;
        System.out.print(rst);
    }
}
