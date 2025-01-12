import java.util.*;
import java.io.*;

public class Main{
    public static class Node {
        int x;
        int y;
        int type;

        public Node(int x, int y, int type) {
            this.x= x;
            this.y= y;
            this.type = type;   //type은 0, 6, 1~5
        }
    }

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    //북(0), 남(1), 서(2), 동(3)
    public static int[][][] mode = {{{0}},
            {{0}, {1}, {2}, {3}},
            {{2, 3}, {0, 1}},
            {{0, 3}, {1, 3}, {1, 2}, {0, 2}},
            {{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}},
            {{0, 1, 2, 3}}};
    public static ArrayList<Node> cctv;
    public static int n, m, ans;
    public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cctv = new ArrayList<>();
        int zero_cnt = 0;
        int[][] map = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5){
                    //좌표와 type으로 타입을 저장하여 리스트에 추가
                    cctv.add(new Node(i, j, map[i][j]));
                }
                //빈 칸 갯수 세기
                else if(map[i][j] == 0) zero_cnt++;
            }
        }
        ans = zero_cnt;
        combination(0, cctv.size(), map);   //cctv.size()는 최대 8
        System.out.println(ans);
    }

    
    public static void combination(int depth, int r, int[][] map) {
        //깊이가 r(CCTV의 총 갯수)일 때 return
        if(depth == r) {
            int cnt = blinkNumCheck(map);
            ans = Math.min(ans, cnt);
            return;
        }

        int cctv_type = cctv.get(depth).type;   //현재 CCTV의 타입(1~5)
        int x = cctv.get(depth).x;  //현재 CCTV의 x좌표
        int y = cctv.get(depth).y;  //현재 CCTV의 y좌표
        
        for(int i=0; i<mode[cctv_type].length; i++){    //각 cctv의 타입마다 mode의 갯수가 다름, cctv 타입에 따른 모드의 갯수 만큼 반복
            int[][] map_copy = new int[n][m];

            for(int k=0; k<n; k++) {
                //clone()은 깊은 복사를 해서 기존 map[] 배열과 독립적으로 수정 가능
                map_copy[k] = map[k].clone();
            }

            //방향의 갯수까지 세분화
            for(int j=0; j<mode[cctv_type][i].length; j++){ //ex) mode[3][1] => {1, 3}      //mode[3][1].length = 2
                int dir = mode[cctv_type][i][j];    //mode[3][1][0] => 1(남)    mode[3][1][1] => 3(동)
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                //사방탐색
                while(true){
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                        break;
                    }

                    if(map[nx][ny] == 6){
                        break;
                    }
                    
                    map_copy[nx][ny] = -1;
                    nx += dx[dir];
                    ny += dy[dir];
                }
            }

            combination(depth+1, r, map_copy);
        }
    }

    //빈 칸의 갯수를 세는 함수
    public static int blinkNumCheck(int[][] map) {
        int cnt = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}