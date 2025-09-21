import java.io.*;
import java.util.*;

// 문제는 길지만 마지막 문장만 있으면 된다.
// 등수의 차이가 k를 넘지 않으면서 이름의 길이가 같은 친구의 수를 구해야 한다.
public class Main {
    static int n, k;
    static String[] arr;
    static int[] cnt;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new String[n];
        for(int i=0; i<n; i++) arr[i] = br.readLine();
        cnt = new int[21];      // 이름의 길이마다 학생의 수

        long rst = 0;
        // 시작부터 끝까지 순회하면서
        for(int i=0; i<n; i++){
            int len = arr[i].length();  // i번째 학생의 이름 길이
            rst += cnt[len];
            cnt[len]++;
            // 윈도우의 맨 앞 학생 제거
            if(i >= k) cnt[arr[i-k].length()]--;
        }
        System.out.print(rst);
    }
}