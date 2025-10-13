import java.io.*;
import java.util.*;

// 건물 X 의 접근성은 X 에서 가장 가까운 호석이 두마리 치킨집까지 왕복하는 최단 시간이다
public class Main {
    static int INF = Integer.MAX_VALUE;
    static int[][] dist;
    static int n, m;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];
        for(int i=1; i<=n; i++) Arrays.fill(dist[i], INF);
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            dist[s][e] = 1;
            dist[e][s] = 1;
        }
        for(int i=1; i<=n; i++) dist[i][i] = 0;
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(dist[i][k] != INF && dist[k][j] != INF){
                        if(dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        int orgMin = Integer.MAX_VALUE;
        int orgI = 0;
        int orgJ = 0;
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                int sum = 0;
                // sum: 모든 건물에서 가장 가까운 치킨집까지의 최단 시간의 총합
                for(int k=1; k<=n; k++) sum += Math.min(dist[k][i], dist[k][j]);
                // 왕복 시간의 합이 작은 경우 || 왕복 시간이 같으면 작은 번호가 더 작은 것 || 작은 번호도 같으면 큰 번호가 더 작은 것
                if(sum < orgMin || (sum == orgMin && (Math.min(i, j) < Math.min(orgI, orgJ)
                        || (Math.min(i, j) == Math.min(orgI, orgJ) && Math.max(i, j) < Math.max(orgI, orgJ))))){
                    orgMin = sum;
                    orgI = i;
                    orgJ = j;
                }
            }
        }
        System.out.print(Math.min(orgI, orgJ) + " " + Math.max(orgI, orgJ) + " " + orgMin * 2);
    }
}