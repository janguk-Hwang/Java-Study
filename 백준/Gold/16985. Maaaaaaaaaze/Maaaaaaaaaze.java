import java.io.*;
import java.util.*;

// 기능에 따라 함수
public class Main {
    static final int SIZE = 5;
    static int[][][] origin = new int[SIZE][SIZE][SIZE];    // 입력 판 배열
    static int[][][] tempMatrix = new int[SIZE][SIZE][SIZE];
    static int[] order = new int[SIZE];     // 판의 순서
    static int[] rotation = new int[SIZE];      // 판의 회전 상태
    static boolean[] visited = new boolean[SIZE];
    static int min = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<SIZE; k++) origin[i][j][k] = Integer.parseInt(st.nextToken());
            }
        }
        makeOrder(0);
        if(min == Integer.MAX_VALUE){
            System.out.print(-1);
            return;
        }
        System.out.print(min);
    }

    // 5개의 판을 쌓는 순서 선택
    // [0, 1, 2, 3, 4]
    // [0, 1, 2, 4, 3]
    // [0, 1, 3, 2, 4]
    // [0, 1, 3, 4, 2]
    // [0, 1, 4, 2, 3]
    // [0, 1, 4, 3, 2]
    // [0, 2, 1, 3, 4]
    // [0, 2, 1, 4, 3]
    // [0, 2, 3, 1, 4]
    // [0, 2, 3, 4, 1]...
    // 총 5!(120)개의 판 쌓는 순서 조합을 생성
    public static void makeOrder(int depth){
        if(depth == SIZE){
            // 판의 순서를 모두 정했으면 판 회전으로 이동
            makeRotate(0);
            return;
        }
        for(int i=0; i<SIZE; i++){
            if(!visited[i]){
                visited[i] = true;
                order[depth] = i;
                makeOrder(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void makeRotate(int depth){
        if(depth == SIZE){
            makeMatrix();
            return;
        }
        // 00000, 00001, 00002, 00003, 00010, 00011, 00012, 00013, 00020, 00021...
        // 총 4^5개의 판 상태 조합을 생성하고 makeMatrix() 실행
        for(int d=0; d<4; d++){
            rotation[depth] = d;
            makeRotate(depth + 1);
        }
    }

    public static void makeMatrix(){
        for(int i=0; i<SIZE; i++){
            // rotate(회전시킬 판(입력), 회전 수)
            int[][] layer = rotate(origin[order[i]], rotation[i]);
            // tempMatrix[z][r][c] (z축, 행, 열)
            for(int r=0; r<SIZE; r++){
                for(int c=0; c<SIZE; c++){
                    tempMatrix[i][r][c] = layer[r][c];
                }
            }
        }
        // 시작점과 도착점이 들어갈 수 있는 칸인 경우만 bfs 실행
        if(tempMatrix[0][0][0] == 1 && tempMatrix[4][4][4] == 1) bfs();
    }
    
    // 90도 회전 함수
    // (회전시킬 층, 회전 횟수(0 ~ 3))
    public static int[][] rotate(int[][] matrix, int num){
        for(int i=0; i<num; i++){
            int[][] temp = new int[SIZE][SIZE];
            for(int j=0; j<SIZE; j++){
                for(int k=0; k<SIZE; k++){
                    // 돌려보면 행은 이전 열과 동일하고, 열은 판의 크기에서 이전 행을 뺀 값이다.
                    // 0-based라서 -1을 추가로 해서 인덱스를 맞춘 것
                    temp[k][SIZE - 1 - j] = matrix[j][k];
                }
            }
            // matrix에 회전된 temp를 할당해야 다음 회전에서도 matrix를 회전한 상태를 temp에 담을 수 있다.
            matrix = temp;
        }
        return matrix;
    }
    
    public static void bfs(){
        boolean[][][] visited = new boolean[SIZE][SIZE][SIZE];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        while(!q.isEmpty()){
            Node now = q.poll();
            // 도착점(4, 4, 4)이고
            if(now.r == 4 && now.c == 4 && now.z == 4){
                // 도착점 도달했으면서 이동 횟수가 12인 경우
                // 12보다 더 적은 이동 횟수는 나올 수 없으므로 탐색 종료
                if(now.cnt == 12){
                    min = 12;
                    return;
                }
                min = Math.min(min, now.cnt);
            }
            for(int d=0; d<6; d++){
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                int nz = now.z + dz[d];
                if(nr < 0 || nr >= SIZE || nc < 0 || nc >= SIZE || nz < 0 || nz >= SIZE) continue;
                // 아직 방문하지 않았고 이동 가능한 칸인 경우, 새로운 좌표와 증가된 이동 횟수를 큐에 삽입
                if(!visited[nz][nr][nc] && tempMatrix[nz][nr][nc] == 1){
                    visited[nz][nr][nc] = true;
                    q.add(new Node(nz, nr, nc, now.cnt + 1));
                }
            }
        }
    }

    public static class Node{
        int z, r, c, cnt;   //
        Node(int z, int r, int c, int cnt){
            this.z = z; this.r = r; this.c = c; this.cnt = cnt;
        }
    }
}