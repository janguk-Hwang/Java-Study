import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            int[] r = new int[n];
            int[] s = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) r[i] = Integer.parseInt(st.nextToken());
            List<Integer> possibleNum = new ArrayList<>();
            for(int i=1; i<=n; i++) possibleNum.add(i);
            boolean flag = true;
            // 뒤에서부터 s[] 채우기
            for(int i=n-1; i>=0; i--){
                int idx = r[i];
                if(idx >= possibleNum.size()){
                    flag = false;
                    break;
                }
                s[i] = possibleNum.get(idx);
                possibleNum.remove(idx);
            }
            if(!flag){
                sb.append("IMPOSSIBLE\n");
                continue;
            }
            for(Integer temp : s) sb.append(temp).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}