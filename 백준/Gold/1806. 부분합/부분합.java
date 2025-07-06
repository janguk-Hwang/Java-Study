import java.io.*;
import java.util.*;

public class Main {
    static int n, s, minLength;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        long sum = 0; int start = 0;
        minLength = 100_001;
        for(int end=0; end<n; end++){
            sum += arr[end];
            while(sum >= s){
                minLength = Math.min(minLength, end - start + 1);
                sum -= arr[start++];
            }
        }
        System.out.println(minLength == 100_001 ? 0 : minLength);
    }
}