import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int snowman1 = arr[i] + arr[j];
                int left = 0;
                int right = n-1;
                while(left < right){
                    if(left == i || left == j){
                        left++;
                        continue;
                    }
                    if(right == i || right == j){
                        right--;
                        continue;
                    }
                    int snowman2 = arr[left] + arr[right];
                    int diff = Math.abs(snowman1 - snowman2);
                    if(diff < minDiff) minDiff = diff;
                    if(diff == 0){
                        System.out.print(0);
                        return;
                    }
                    if(snowman2 < snowman1) left++;
                    else right--;
                }
            }
        }
        System.out.print(minDiff);
    }
}