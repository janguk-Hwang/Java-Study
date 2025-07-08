import java.io.*;
import java.util.*;

// 공유기 간의 거리를 mid로 제한한다. mid보다 작은 거리에서는 공유기를 설치할 수 없다.
// mid down -> 필요한 공유기 수 up
// mid up -> 필요한 공유기 수 down
// mid가 작은 수부터 큰 수로 이동함에 따라 설치되는 공유기 수는 점점 적어진다.
// 설치되는 공유기가 n-1개에서 n개가 되는 경계에서 정답을 찾을 수 있다.
// ttttttttffffffff
public class Main {
    static int n, c;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int start = 1; int end = arr[n-1] - arr[0] + 1;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            int cnt = 1;
            int last = arr[0];
            for(int i=1; i<n; i++){
                if(arr[i] - last >= mid){
                    cnt++;
                    last = arr[i];
                }
            }
            if(cnt >= c) start = mid;
            else end = mid;
        }
        System.out.print(start);
    }
}