import java.io.*;
import java.util.*;

// s(k) = s(k-1) + m + o: k+2 + s(k-1)
// s(k)는 s(k-1)의 길이 * 2 + 1 + k + 2 = s(k-1) * 2 + k + 3;
// Moo 수열은 앞부분인 s(k-1), 중간 부분인 m + o: k+2, 뒷 부분인 s(k-1)로 나눌 수 있다.
public class Main {
    static long[] length = new long[31];
    static int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        length[0] = 3;
        int k = 0;
        // length[k]는 n보다 큰 상태에서 종료
        while(length[k] < n){
            k++;
            length[k] = 2 * length[k-1] + k + 3;
        }
        System.out.print(func(n, k));
    }
    // n번째 문자열이 s(k) 수열에서 어디 있는지 재귀로 찾음
    public static char func(int n, int k){
        if(k == 0){
            if(n == 1) return 'm';
            return 'o';
        }
        if(n <= length[k-1]){
            return func(n, k-1);
        }
        if(n <= length[k-1] + k + 3){
            if(n - length[k-1] == 1) return 'm';
            return 'o';
        }
        return func((int) (n - length[k-1] - k - 3), k-1);
    }
}