import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int r, c, n;
    static char[][] matrix;
    static int[] throwHeight;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        matrix = new char[r][c];
        for(int i=0; i<r; i++){
            String s = br.readLine();
            matrix[i] = s.toCharArray();
        }
        n = Integer.parseInt(br.readLine());
        throwHeight = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) throwHeight[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            throwStick(i, throwHeight[i]);
            dropCluster();
        }
        for(int i=0; i<r; i++) sb.append(matrix[i]).append("\n");
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static void throwStick(int turn, int h){     // turn이 짝수이면 왼쪽 홀수이면 오른쪽에서 던짐
        if(turn % 2 == 0){
            for(int i=0; i<c; i++){
                if(matrix[r-h][i] == 'x'){
                    matrix[r-h][i] = '.';
                    break;
                }
            }
            return;
        }
        for(int i=c-1; i>=0; i--){
            if(matrix[r-h][i] == 'x'){
                matrix[r-h][i] = '.';
                break;
            }
        }
    }

    static void dropCluster(){
        visited = new boolean[r][c];
        Queue<int[]> q = new LinkedList<>();
        // 땅과 연결되어 있는 칸들은 방문처리
        for(int i=0; i<c; i++){
            if(matrix[r-1][i] == 'x' && !visited[r-1][i]){
                visited[r-1][i] = true;
                q.offer(new int[]{r-1, i});
                while(!q.isEmpty()){
                    int[] now = q.poll();
                    for(int d=0; d<4; d++){
                        int nr = now[0] + dr[d];
                        int nc = now[1] + dc[d];
                        if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                        if(matrix[nr][nc] == '.') continue;
                        if(!visited[nr][nc]){
                            visited[nr][nc] = true;
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        // 공중에 떠있는 칸들은 중력 작용
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(!visited[i][j] && matrix[i][j] == 'x'){
                    list.add(new int[]{i, j});
                    matrix[i][j] = '.';
                }
            }
        }
        // 중력 작용할 칸이 없으면 종료
        if(list.isEmpty()) return;

        int minDown = r;
        for(int[] temp : list){
            int down = 0;
            int nr = temp[0] + 1;
            int nc = temp[1];
            // 범위를 벗어나지 않고 빈 칸을 만날때마다 계속 내려감
            while(nr < r && matrix[nr][nc] == '.'){
                nr++; down++;
            }
            minDown = Math.min(minDown, down);
        }

        for(int[] t : list) matrix[t[0] + minDown][t[1]] = 'x';
    }
}