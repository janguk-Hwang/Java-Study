import java.io.*;
import java.util.*;

// 송도는 직사각형 모양으로 생긴 도시이다. 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이 이다. (맨해튼 거리)
public class Main {
    static int t, n;
    static boolean[] visited;
    static Queue<Integer> q;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            int total = n + 2;
            int[][] pos = new int[total][2];
            for(int i=0; i<total; i++){
                st = new StringTokenizer(br.readLine());
                pos[i][0] = Integer.parseInt(st.nextToken());
                pos[i][1] = Integer.parseInt(st.nextToken());
            }
            q = new LinkedList<>();
            visited = new boolean[total];
            boolean flag = false;
            q.add(0);
            visited[0] = true;
            while(!q.isEmpty()){
                int cur = q.poll();
                // 현재 위치에서 도착점까지의 거리
                int distToFestival = Math.abs(pos[cur][0] - pos[total - 1][0]) + Math.abs(pos[cur][1] - pos[total - 1][1]);
                // 편의점 들른 후, 도착점까지 한 번에 갈 수 있으면
                if(distToFestival <= 1000){
                    flag = true;
                    break;
                }
                for(int i=1; i<total; i++){
                    if(!visited[i]){
                        int dist = Math.abs(pos[cur][0] - pos[i][0]) + Math.abs(pos[cur][1] - pos[i][1]);
                        if(dist <= 1000){
                            visited[i] = true;
                            q.add(i);
                        }
                    }
                }
            }
            sb.append(flag ? "happy" : "sad").append("\n");
        }
        System.out.print(sb);
    }
}