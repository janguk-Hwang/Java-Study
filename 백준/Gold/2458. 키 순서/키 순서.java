import java.io.*;
import java.util.*;

// dfs or 플로이드 워셜
// 다른 모든 정점과의 비교가 가능해야 자신이 몇 번째인지 알 수 있다.
// 입력을 통해 얻은 대소관계를 플로이드 워셜로 모든 정점간의 비교를 진행한다.
// 비교할 수 있는 정점간의 대소관계가 계산된다.
// 각 정점에서 비교가능한 정점 수가 정점의 수 - 1개라면 모든 정점과 비교가 가능한 것이므로 자신의 순서를 알 수 있다.
public class Main {
    static int[][] dist;
    static int n, m, cnt;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];
        for(int i=0; i<=n; i++) Arrays.fill(dist[i], -1);
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
        }
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(dist[i][k] != -1 && dist[k][j] != -1){
                        dist[i][j] = 1;
                    }
                }
            }
        }
        cnt = 0;
        for(int i=1; i<=n; i++){
            int tempCnt = 0;
            for(int j=1; j<=n; j++){
                if(dist[i][j] == 1 || dist[j][i] == 1) tempCnt++;
            }
            if(tempCnt == n-1) cnt++;
        }
        System.out.print(cnt);
    }
}