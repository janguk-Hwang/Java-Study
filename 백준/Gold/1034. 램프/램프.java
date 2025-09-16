import java.io.*;
import java.util.*;

// 여러 행의 모습이 있을 것인데 k번 눌러서 모든 행이 켜지는 패턴 중에서 가장 많은 행을 선택하면 된다.
// k번 누른 후 모든 열이 켜져있는 행의 수를 찾는 것이기 때문
public class Main {
    static int n, m, k;
    static boolean[] visited;
    static int[][] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        arr = new int[n][m];
        for(int i=0; i<n; i++){
            String[] s = br.readLine().split("");
            for(int j=0; j<m; j++) arr[i][j] = Integer.parseInt(s[j]);
        }
        k = Integer.parseInt(br.readLine());

        int rst = 0;
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            int cnt = 0;    // 0의 개수
            for(int j=0; j<m; j++) if(arr[i][j] == 0) cnt++;
            // k번 이내로 가능하고, 남은 횟수가 짝수여야 가능(홀수이면 불가)
            if(cnt <= k && (k - cnt) % 2 == 0){
                int count = 0;      // 같은 패턴인 행의 수
                // 동일 패턴의 행의 수를 세고 방문처리
                for(int j=0; j<n; j++){
                    if(!visited[j] && isSame(arr[i], arr[j])){
                        visited[j] = true;
                        count++;
                    }
                }
                rst = Math.max(rst, count);
            }
        }
        System.out.print(rst);
    }

    static boolean isSame(int[] r1, int[] r2){
        for(int i=0; i<m; i++) if(r1[i] != r2[i]) return false;
        return true;
    }
}