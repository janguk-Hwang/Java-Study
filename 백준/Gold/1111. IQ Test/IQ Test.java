import java.util.*;
import java.io.*;

// 항상 모든 답을 구하는 규칙은 앞 수*a + b이다. 그리고, a와 b는 정수이다.
// 다음 수가 여러 개일 경우에는 A를 출력하고, 다음 수를 구할 수 없는 경우에는 B를 출력한다.
public class Main {
    static StringTokenizer st;
    static int[] arr;
    static int a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 출력값이 A인 경우(배열의 크기가 1인 경우 다음에 뭐든지 올 수 있다)
        // 출력값이 A인 경우(배열의 크기가 2인 경우면서 arr[0]과 arr[1]이 다른 경우 arr[0]과 arr[1]의 차이만큼을 더해서 arr[2]로 한다)
        if (n == 1 || (n == 2 && arr[0] != arr[1])) {
            System.out.print("A");
        }
        // 배열의 크기가 2인 경우면서 arr[0]과 arr[1]이 같은 경우는 다음 숫자를 같은 숫자로 한다
        else if (n == 2 && arr[0] == arr[1]) {
            System.out.print(arr[0]);
        }
        else {
            // arr[0]과 arr[1]이 같으면 a = 1, b = 0
            if (arr[0] == arr[1]) {
                a = 1;
                b = 0;
            } else {
                // a가 정수가 아니라면 B출력
                if ((arr[1] - arr[2]) % (arr[0] - arr[1]) != 0) {
                    System.out.print("B");
                    return;
                }
                a = (arr[1] - arr[2]) / (arr[0] - arr[1]);
                b = arr[1] - a * arr[0];
            }

            // 규칙이 일관되게 적용되는지 확인, 일관되게 적용되지 않으면 B출력
            // 모두 규칙이 일관되게 적용되면 다음 수를 출력
            for (int i = 1; i < n; i++) {
                if (arr[i] != arr[i - 1] * a + b) {
                    System.out.println("B");
                    return;
                }
            }
            System.out.println(arr[n-1] * a + b);
        }
    }
}