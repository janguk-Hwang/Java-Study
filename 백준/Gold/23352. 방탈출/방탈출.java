import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] matrix;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        int maxDist = -1;
        int rst = 0;
        boolean flag = false;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 0) continue;
                flag = true;
                int[][] dist = new int[n][m];
                for(int[] row : dist) Arrays.fill(row, -1);
                Queue<Node> q = new LinkedList<>();
                q.offer(new Node(i, j, 0));
                dist[i][j] = 0;
                while(!q.isEmpty()){
                    Node cur = q.poll();
                    for(int d=0; d<4; d++){
                        int nx = cur.x + dx[d];
                        int ny = cur.y + dy[d];
                        if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                        if(matrix[nx][ny] == 0) continue;
                        if(dist[nx][ny] != -1) continue;
                        dist[nx][ny] = cur.dist + 1;
                        q.offer(new Node(nx, ny, dist[nx][ny]));
                    }
                }
                for(int x=0; x<n; x++){
                    for(int y=0; y<m; y++){
                        if(dist[x][y] == -1) continue;
                        int sum = matrix[i][j] + matrix[x][y];
                        if(dist[x][y] > maxDist){
                            maxDist = dist[x][y];
                            rst = sum;
                            continue;
                        }
                        if(dist[x][y] == maxDist) rst = Math.max(rst, sum);
                    }
                }
            }
        }
        if(!flag) System.out.print(0);
        else System.out.print(rst);
    }

    static class Node{
        int x, y, dist;
        Node(int x, int y, int dist){
            this.x = x; this.y = y; this.dist = dist;
        }
    }
}