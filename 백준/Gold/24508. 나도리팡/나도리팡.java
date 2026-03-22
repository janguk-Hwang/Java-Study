import java.io.*;
import java.util.*;

public class Main {
    static int n, k, t, sum;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int cnt = 0;    // 0이 아닌 바구니 개수
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            if(arr[i] != 0) cnt++;
        }
        if(cnt == 0){
            System.out.print("YES");
            return;
        }
        if(cnt == 1){
            System.out.print("NO");
            return;
        }
        Arrays.sort(arr);
        int left = n - cnt; int right = n-1;
        while(left < right && t > 0){
            int curNeed = k - arr[right];
            // left로 커버 불가능하면 다음 left로
            if(arr[left] < curNeed){
                arr[right] += arr[left];
                t -= arr[left];
                left++;
            }
            // left로 커버 가능하면 다음 right로
            // 정확히 left를 다 사용했으면 다음 left로
            else{
                arr[right] += curNeed;
                arr[left] -= curNeed;
                t -= curNeed;
                right--;
                if(arr[left] == 0) left++;
            }
        }
        if(left > right && t >= 0) System.out.print("YES");
        else System.out.print("NO");
    }
}