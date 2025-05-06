import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, (a, b) -> b[1] - a[1]);
        int start = arr[0][1];
        for(int i=0; i<n; i++){
            start = Math.min(start, arr[i][1]);
            start -= arr[i][0];
        }
        System.out.println(start);
    }
}
