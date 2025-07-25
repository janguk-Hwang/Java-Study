import java.io.*;
import java.util.*;

// 어떤 수를 묶으려고 할 때, 위치에 상관없이 묶을 수 있다.
// 수열의 모든 수는 단 한번만 묶거나, 아니면 묶지 않아야한다.
public class Main {
    static int n, sum;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        // 양수는 큰 수부터 묶고, 음수는 작은 수부터 묶다가 총 개수가 홀수라서 하나가 남으면 해당 수는 묶지 않고 누적
        // 혼자 남은 음수와 0을 곱해서 음수값 상쇄 가능
        // 1은 곱하면 손해이므로 1은 그냥 누적
        sum = 0;
        int left = 0;
        int right = n - 1;
        // 양수 처리
        while(right >= 0 && arr[right] > 1){
            if(right - 1 >= 0 && arr[right - 1] > 1){
                sum += arr[right] * arr[right - 1];
                right -= 2;
                continue;
            }
            sum += arr[right];
            right--;
        }
        while(right >= 0 && arr[right] == 1){
            sum += 1;
            right--;
        }
        // right는 1이 있다면 가장 왼쪽의 1의 왼쪽을 가리키고, 1이 없다면 양수중에서 가장 작은 값의 왼쪽을 가리키고 있다.
        // 즉, right는 양수가 아닌 가장 오른쪽의 수를 가리키고 있다. (0 or 음수)
        while(left < right){
            sum += arr[left] * arr[left + 1];
            left += 2;
        }
        // -1 -1 -1 1 2 3
        // -1 -1 -1 0 1 2 3
        // -1 -1 -1 0 0 1 2 3
        // 음수가 하나 남아있는 경우
        if(left == right) sum += arr[left];
        System.out.print(sum);
    }
}