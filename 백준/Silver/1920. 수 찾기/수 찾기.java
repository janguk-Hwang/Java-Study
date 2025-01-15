import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        while (M > 0) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(bs(arr, target, 0, N - 1)).append("\n");
            M--;
        }

        System.out.println(sb);
    }

    public static int bs(int[] arr, int target, int start, int end) {
        //start가 end보다 뒤에 있지 않도록 하는 경우에서
        while (start <= end) {
            //이분탐색을 위한 (start+end)/2
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return 1;
            }
            else if(arr[mid] > target){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        return 0;
    }
}