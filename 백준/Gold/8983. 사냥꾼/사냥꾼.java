import java.io.*;
import java.util.*;

// 문제에서 주어지는 n, m, l은 숫자가 크다.
// 단순하게 각 동물에 대해서 모든 사대에서 잡을 수 있는지 확인하는 건 불가능
// 각 동물에 대해 잡을 수 있는 사대를 이분탐색으로 찾음
// 안 잡힐 수도 있으므로 이 경우도 처리되도록
public class Main {
    static int n, m, l;
    static int[] sade;
    static int rst = 0;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        sade = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) sade[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(sade);
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            func(a, b);
        }
        System.out.print(rst);
    }

    public static void func(int a, int b){
        int idx = Arrays.binarySearch(sade, a);
        // 정확히 동물의 x좌표와 사대의 x좌표가 같은 경우
        if(idx >= 0 && Math.abs(sade[idx] - a) + b <= l){
            rst++;
            return;
        }
        // 동물의 x좌표와 사대의 x좌표가 다른 경우
        // idx-1, idx번째를 검사
        if(idx < 0){
            idx = -(idx + 1);
            if(idx-1 >= 0 && Math.abs(sade[idx-1] - a) + b <= l){
                rst++;
                return;
            }
            if(idx < m && Math.abs(sade[idx] - a) + b <= l){
                rst++;
                return;
            }
        }
    }
}