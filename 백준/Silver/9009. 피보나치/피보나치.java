import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static ArrayList<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        list.add(0);    // f(0) = 0
        list.add(1);    // f(1) = 1
        list.add(1);    // f(2) = 1
        while(true){
            int next = list.get(list.size()-1) + list.get(list.size()-2);
            if(next >= 1_000_000_000) break;
            list.add(next);
        }
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            ArrayList<Integer> result = new ArrayList<>();
            int n = Integer.parseInt(br.readLine());
            for(int i=list.size()-1; i>=0; i--){
                int temp = list.get(i);
                if(temp <= n){
                    result.add(temp);
                    n -= temp;
                }
                if(n == 0) break;
            }
            Collections.sort(result);
            for(Integer i : result) sb.append(i).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
