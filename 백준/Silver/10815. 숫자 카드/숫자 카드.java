import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer> arr = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr.add(Integer.parseInt(st.nextToken()));
        arr.sort(null);
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            if(Collections.binarySearch(arr, Integer.parseInt(st.nextToken())) >= 0) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.print(sb);
    }
}