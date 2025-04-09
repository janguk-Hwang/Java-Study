import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int h, w, total;
    static int[] height;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        height = new int[w];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<w; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        total = 0;
        solve();

        System.out.println(total);
    }

    public static void solve(){
        for(int i=1; i<w-1; i++){
            int leftMax = 0;
            int rightMax = 0;

            // 왼쪽 최대값
            for(int j=0; j<i; j++){
                leftMax = Math.max(leftMax, height[j]);
            }

            // 오른쪽 최대값
            for(int j=i+1; j<w; j++){
                rightMax = Math.max(rightMax, height[j]);
            }

            int min = Math.min(leftMax, rightMax);
            if(Math.min(leftMax, rightMax) > height[i]){
                total += min - height[i];
            }
        }
    }
}