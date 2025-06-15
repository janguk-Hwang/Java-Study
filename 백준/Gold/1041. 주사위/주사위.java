import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[7];
        st = new StringTokenizer(br.readLine());
        int minEye = Integer.MAX_VALUE;
        int maxEye = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=1; i<=6; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            minEye = Math.min(minEye, arr[i]);
            maxEye = Math.max(maxEye, arr[i]);
            sum += arr[i];
        }
        if(n == 1){
            sum -= maxEye;
            System.out.print(sum);
            return;
        }
        int three = Math.min(arr[1], arr[6]) + Math.min(arr[2], arr[5]) + Math.min(arr[3], arr[4]);
        int two = Integer.MAX_VALUE;
        for(int i=1; i<=6; i++){
            for(int j=i+1; j<=6; j++){
                if((i+j) == 7) continue;
                two = Math.min(two, arr[i] + arr[j]);
            }
        }
        int one = minEye;

        int threeNum = 4;
        int twoNum = 8 * n - 12;
        long oneNum = (n - 2) * (5L * n - 6);
        long total = 0;
        total += (long) three * threeNum;
        total += (long) two * twoNum;
        total += (long) one * oneNum;
        System.out.print(total);
    }
}