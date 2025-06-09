import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[366];
        int max = 0; int min = 365;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            max = Math.max(max, e);
            min = Math.min(min, s);
            for(int j=s; j<=e; j++) arr[j]++;
        }

        int total = 0;
        int width = 0;
        int height = 0;
        for(int i=min; i<=max; i++){
            if(arr[i] > 0){
                width++;
                height = Math.max(height, arr[i]);
                continue;
            }
            // 끊기는 부분이고 이전에 계산된 width가 있는 경우
            if(width > 0){
                total += width * height;
                width = 0;
                height = 0;
            }
        }
        if(width > 0) total += width * height;
        System.out.print(total);
    }
}