import java.io.*;
import java.util.*;

//          1
//         / \
//   3    2   5
//    \  /
//      4
// 무게가 중간인 구슬이 될 수 없는 구슬 -> 자신보다 가벼운 구슬이 (n+1)/2개 이상이거나 무거운 구슬이 (n+1)/2개 이상이면 중간이 될 수 없다.
// a, b -> a가 b보다 무겁다
public class Main {
    static int n, m;
    static boolean[][] relation;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        relation = new boolean[n + 1][n + 1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relation[a][b] = true;      // a가 b보다 무겁다
        }

        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(relation[i][k] && relation[k][j]){
                        relation[i][j] = true;
                    }
                }
            }
        }

        // 검사
        int mid = (n+1)/2;
        int rst = 0;        // 중간이 될 수 없는 구슬의 수
        for(int i=1; i<=n; i++){
            int heavy = 0;
            int light = 0;
            for(int j=1; j<=n; j++){
                // i가 j보다 무거운 경우
                if(relation[i][j]) light++;
                // j가 i보다 무거운 경우
                if(relation[j][i]) heavy++;
            }
            if(light >= mid || heavy >= mid) rst++;
        }
        System.out.print(rst);
    }
}