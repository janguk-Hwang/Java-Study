import java.io.*;
import java.util.*;

public class Main{
    static int n;
    static ArrayList<Integer> blue = new ArrayList<>();
    static ArrayList<Integer> white = new ArrayList<>();
    static ArrayList<Integer>[] hate;
    static int[] team; // 0 = 미정 1 = 청팀 2 = 백팀
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)throws Exception{
        n = Integer.parseInt(br.readLine());
        hate = new ArrayList[n + 1];
        team = new int[n + 1];
        for(int i=1; i<=n; i++) hate[i] = new ArrayList<>();
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int j=0; j<num; j++){
                int a = Integer.parseInt(st.nextToken());
                hate[i].add(a);
                hate[a].add(i);
            }
        }
        // 팀이 없는 사람들은 청팀에 넣는다.
        for(int i=1; i<=n; i++) if(team[i] == 0) assignTeam(i, 1);
        for(int i=1; i<=n; i++){
            if(team[i] == 1) blue.add(i);
            else white.add(i);
        }
        blue.sort(null);
        white.sort(null);
        sb.append(blue.size()).append("\n");
        for(int x : blue) sb.append(x).append(" ");
        sb.append("\n");
        sb.append(white.size()).append("\n");
        for(int x : white) sb.append(x).append(" ");
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static void assignTeam(int start, int color){
        team[start] = color;
        // 최초에는 이 사람이 싫어하는 사람들은 백팀으로
        // 또 이 사람이 싫어하는 사람들이 싫어하는 사람들은 청팀으로
        for(int next : hate[start]){
            if(team[next] == 0){
                assignTeam(next, 3 - color);
            }
        }
    }
}