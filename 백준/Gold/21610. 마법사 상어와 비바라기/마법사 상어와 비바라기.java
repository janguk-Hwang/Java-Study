import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int n, m;
    static int[][] arr;
    static List<int[]> clouds;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clouds = new ArrayList<>();
        for(int i=1; i<=2; i++){
            for(int j=0; j<=1; j++){
                clouds.add(new int[]{n-i, j});
            }
        }

        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            func(d, s);
        }
        int answer = 0;
        for(int[] row : arr){
            for(int temp : row){
                answer += temp;
            }
        }
        System.out.print(answer);
    }

    public static void func(int d, int s){
        boolean[][] visited = new boolean[n][n];
        List<int[]> newCloud = new ArrayList<>();

        for(int[] cloud : clouds){
            int r = ((cloud[0] + dr[d] * s) % n + n * s) % n;
            int c = ((cloud[1] + dc[d] * s) % n + n * s) % n;
            arr[r][c]++;
            visited[r][c] = true;
            newCloud.add(new int[]{r, c});
        }

        for(int[] cloud : newCloud){
            int r = cloud[0];
            int c = cloud[1];
            int cnt = 0;
            for(int i=-1; i<=1; i+=2){
                for(int j=-1; j<=1; j+=2){
                    int nr = r+i;
                    int nc = c+j;
                    if(nr >= 0 && nr < n && nc >= 0 && nc < n && arr[nr][nc] > 0){
                        cnt++;
                    }
                }
            }
            arr[r][c] += cnt;
        }

        clouds = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j] && arr[i][j] >= 2){
                    arr[i][j] -= 2;
                    clouds.add(new int[]{i, j});
                }
            }
        }
    }
}