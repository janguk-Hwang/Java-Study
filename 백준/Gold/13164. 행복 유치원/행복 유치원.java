import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] arr, diff;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        diff = new int[n - 1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<n-1; i++) diff[i] = arr[i+1] - arr[i];
        Arrays.sort(diff);
        int rst = 0;
        for(int i=0; i<n-k; i++) rst += diff[i];
        System.out.print(rst);
    }
}