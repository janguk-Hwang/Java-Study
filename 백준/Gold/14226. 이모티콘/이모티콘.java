import java.io.*;
import java.util.*;

// X2 or -1
public class Main {
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int s;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        s = Integer.parseInt(br.readLine());
        visited = new boolean[2001][2001];
        q.add(new int[]{1, 0, 0});  // 화면 이모티콘 수, 클립보드 이모티콘 수, 시간
        visited[1][0] = true;       // 화면 이모티콘 수, 클립보드 이모티콘 수
        while(!q.isEmpty()){
            int[] temp = q.poll();
            if(temp[0] == s){
                System.out.print(temp[2]);
                return;
            }
            // 복사해서 클립보드에 저장
            if(!visited[temp[0]][temp[0]]){
                visited[temp[0]][temp[0]] = true;
                q.add(new int[]{temp[0], temp[0], temp[2] + 1});
            }
            // 클립보드가 비어있지 않고, 화면에 클립보드 내용을 붙였을 때 2000개 이하이고, 방문하지 않은 경우
            if(temp[1] > 0 && temp[0] + temp[1] <= 2000 && !visited[temp[0] + temp[1]][temp[1]]){
                visited[temp[0] + temp[1]][temp[1]] = true;
                q.add(new int[]{temp[0] + temp[1], temp[1], temp[2] + 1});
            }
            //
            if(temp[0] > 0 && !visited[temp[0] - 1][temp[1]]){
                visited[temp[0] - 1][temp[1]] = true;
                q.add(new int[]{temp[0] - 1, temp[1], temp[2] + 1});
            }
        }
    }
}