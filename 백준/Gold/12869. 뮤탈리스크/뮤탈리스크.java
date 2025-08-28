import java.io.*;
import java.util.*;

// 숨바꼭질 문제랑 비슷하다.
// 9, 3, 1의 체력을 깎을 수 있다.
// 깎는 순서에 따라 결과가 달라질 수 있다.
// 그러므로 모두 해봐야 한다.
public class Main {
    static int n;
    static int[] arr;
    static boolean[][][] visited;
    static int[][] attack;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        attack = new int[][]{
                {9, 3, 1}, {9, 1, 3},
                {3, 9, 1}, {3, 1, 9},
                {1, 3, 9}, {1, 9, 3}
        };
        visited = new boolean[61][61][61];
        arr = new int[3];       // 1 ≤ N ≤ 3
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{arr[0], arr[1], arr[2]});
        visited[arr[0]][arr[1]][arr[2]] = true;
        int rst = 0;
        while(!q.isEmpty()){
            rst++;      // 어떤 횟수에 대해 모두 완료되고 횟수 증가
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] now = q.poll();
                for(int[] temp : attack){   // 공격하는 6가지 경우의 수에 대해 모두 진행
                    int h1 = Math.max(0, now[0] - temp[0]);
                    int h2 = Math.max(0, now[1] - temp[1]);
                    int h3 = Math.max(0, now[2] - temp[2]);
                    if(!visited[h1][h2][h3]){
                        // 모두 파괴되면 rst 출력하고 종료
                        if(h1 == 0 && h2 == 0 && h3 == 0){
                            System.out.print(rst);
                            return;
                        }
                        visited[h1][h2][h3] = true;
                        q.add(new int[]{h1, h2, h3});
                    }
                }
            }
        }
    }
}