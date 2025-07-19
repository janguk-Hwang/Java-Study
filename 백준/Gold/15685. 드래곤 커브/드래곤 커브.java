import java.io.*;
import java.util.*;

// (K > 1)세대 드래곤 커브는 K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙인 것이다.
//0: x좌표가 증가하는 방향 (→)
//1: y좌표가 감소하는 방향 (↑)
//2: x좌표가 감소하는 방향 (←)
//3: y좌표가 증가하는 방향 (↓)
//x와 y는 드래곤 커브의 시작 점, d는 시작 방향, g는 세대이다.

// 다음 세대에서 드래곤 커브가 그려질 방향들의 순서(?)형태(?)를 알면
// 드래곤 커브가 존재하는 꼭짓점들을 체크할 수 있다.
// 드래곤 커브가 그려질 다음 위치는 i-1번째 세대에서 드래곤 커브가 그려진 방향의 역순의 시계방향이다.
// 하나씩 착착착착 그려지는 느낌
public class Main {
    static boolean[][] visited = new boolean[101][101];;
    static int[] dr = {0, -1, 0, 1};    // (→, ↑, ←, ↓)
    static int[] dc = {1, 0, -1, 0};    // (→, ↑, ←, ↓)
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        while(n-- > 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();     // 방향의 번호를 저장
            list.add(d);
            for(int i=1; i<=g; i++){    // 1세대부터 g세대까지
                for(int j=list.size()-1; j>=0; j--){
                    int newDir = (list.get(j) + 1) % 4; // 시계방향으로 90도 회전한 방향
                    list.add(newDir);
                }
            }
            // 위 2중 for문이 완료되면 list에는 g세대까지의 드래곤 커브 방향이 담긴다.
            visited[y][x] = true;
            for(Integer dir : list){
                x += dc[dir];
                y += dr[dir];
                // 입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않는다.
                if(!visited[y][x]) visited[y][x] = true;
            }
        }
        // 0 ≤ x ≤ 100, 0 ≤ y ≤ 100
        int cnt = 0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(visited[i][j] && visited[i][j+1] && visited[i+1][j] && visited[i+1][j+1]){
                    cnt++;
                }
            }
        }
        System.out.print(cnt);
    }
}