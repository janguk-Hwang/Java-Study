import java.util.*;
import java.io.*;

public class Main {
    static int n, cnt;
    static LinkedHashMap<String, Integer> hs = new LinkedHashMap<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        for(int i=1; i<=n; i++) hs.put(br.readLine(), i);
        cnt = 0;
        for(int i=1; i<n; i++){
            String out = br.readLine();
            Iterator<String> it = hs.keySet().iterator();
            while(it.hasNext()){
                // 먼저 나온 순서대로 현재 나온 차량의 들어간 순서가
                // 다른 모든 차보다 크면(늦게 들어갔으면)
                if(hs.get(out) > hs.get(it.next())){
                    cnt++;
                    break;  // 이미 추월한 차니까 더 이상 진행x
                }
            }
            hs.remove(out); // 다음으로 나온 차와 비교할 필요가 없으므로 제거
        }
        System.out.println(cnt);
    }
}
