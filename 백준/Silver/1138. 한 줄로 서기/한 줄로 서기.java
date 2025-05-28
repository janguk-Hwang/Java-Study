import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=n-1; i>=0; i--) list.add(arr[i], i+1);    // (삽입할 인덱스, 키)
        for(int i=0; i<n; i++) sb.append(list.get(i)).append(" ");
        System.out.print(sb);
    }
}