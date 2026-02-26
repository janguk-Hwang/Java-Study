import java.io.*;
import java.util.*;

// 가만히 있을 수 있으므로 큐에 가만히 있는 것도 넣으면 무한루프
// 각 칸에서 다른 모든 칸까지의 거리(최소 시간)를 구하고 3명 모두 도착할 수 있으면 가장 늦게 오는 사람의 시간이 세명이 모인 시간의 최솟값
// 한 명이라도 못 오면 불가능, 맵을 순회하면서 가장 작은 칸을 찾고 이와 같은 지점의 개수를 센다.
public class Main {
    static Queue<int[]> q;
    static int r, c;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] matrix;
    static int[][][] dist;
    static int[][] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        matrix = new int[r][c];
        dist = new int[3][r][c];
        for(int i=0; i<r; i++){
            String[] line = br.readLine().split("");
            for(int j=0; j<c; j++) matrix[i][j] = Integer.parseInt(line[j]);
        }
        arr = new int[3][2];
        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++) arr[i][j] = Integer.parseInt(st.nextToken()) - 1;
        }
        for(int i=0; i<3; i++) bfs(i, arr[i][0], arr[i][1]);
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i=0; i<r; i++){
            Outer:
            for(int j=0; j<c; j++){
                if(matrix[i][j] == 1) continue;
                int temp = 0;
                for(int k=0; k<3; k++){
                    if(dist[k][i][j] == -1) continue Outer;
                    temp = Math.max(temp, dist[k][i][j]);
                }
                if(temp < min){
                    min = temp;
                    cnt = 1;
                }
                else if(temp == min) cnt++;
            }
        }
        if(cnt == 0) System.out.print(-1);
        else{
            System.out.println(min);
            System.out.print(cnt);
        }
    }

    static void bfs(int man, int nr, int nc){
        for(int i=0; i<r; i++) Arrays.fill(dist[man][i], -1);
        q = new LinkedList<>();
        q.offer(new int[]{nr, nc});
        dist[man][nr][nc] = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int nnr = cur[0] + dr[d];
                int nnc = cur[1] + dc[d];
                if(nnr < 0 || nnr >= r || nnc < 0 || nnc >= c || dist[man][nnr][nnc] != -1) continue;
                if(matrix[nnr][nnc] == 1) continue;
                dist[man][nnr][nnc] = dist[man][cur[0]][cur[1]] + 1;
                q.offer(new int[]{nnr, nnc});
            }
        }
    }
}