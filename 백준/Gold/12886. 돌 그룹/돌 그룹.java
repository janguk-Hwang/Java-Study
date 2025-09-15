import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int totalStone;
    static int a, b, c;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        totalStone = a + b + c;
        if(totalStone % 3 != 0){
            System.out.print(0);
            return;
        }
        visited = new boolean[totalStone][totalStone];      // [a그룹의 돌 수][b그룹의 돌 수], c그룹은 a, b그룹의 돌 수 합에 의해 자연스럽게 결정됨
        if(bfs(a, b, c)) System.out.print(1);
        else System.out.print(0);
    }

    static boolean bfs(int a, int b, int c){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b, c});
        visited[a][b] = true;
        while(!q.isEmpty()){
            int[] now = q.poll();
            if(now[0] == now[1] && now[1] == totalStone - now[0] - now[1]) return true;
            int[][] temp = {{now[0], now[1]}, {now[1], totalStone - now[0] - now[1]}, {now[0], totalStone - now[0] - now[1]}};
            for(int[] t : temp){
                // 크기가 같지 않은 두 그룹을 고른다.
                if(t[0] == t[1]) continue;
                int smallGroup = Math.min(t[0], t[1]);
                int bigGroup = Math.max(t[0], t[1]);
                int newSmallGroup = smallGroup * 2;
                int newBigGroup = bigGroup - smallGroup;
                // 선택된 그룹이 a, b인 경우, b, c인 경우, a, c인 경우에 대해 각각 처리
                int nx = 0; int ny = 0; int nz = 0;
                // a, b 경우
                if(t[0] == now[0] && t[1] == now[1]){
                    nx = newSmallGroup; ny = newBigGroup; nz = totalStone - nx - ny;
                }
                // b, c 경우
                if(t[0] == now[1] && t[1] == totalStone - now[0] - now[1]){
                    nx = now[0]; ny = newSmallGroup; nz = newBigGroup;
                }
                // a, c 경우
                if(t[0] == now[0] && t[1] == totalStone - now[0] - now[1]){
                    nx = newSmallGroup; ny = now[1]; nz = newBigGroup;
                }
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return false;
    }
}