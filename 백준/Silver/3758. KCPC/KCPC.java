import java.util.*;
import java.io.*;

public class Main {
    static int T, n, k, t, m;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            Team[] teams = new Team[n + 1];
            for(int i=0; i<=n; i++) teams[i] = new Team(i, k);
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int teamId = Integer.parseInt(st.nextToken());
                int problemNum = Integer.parseInt(st.nextToken());
                int acquired = Integer.parseInt(st.nextToken());

                Team team = teams[teamId];
                team.point[problemNum] = Math.max(team.point[problemNum], acquired);
                team.submit++;
                team.finalSubmitTime = i;
            }
            for(int i=1; i<=n; i++) teams[i].updateTotal();
            Arrays.sort(teams);
            int result = 1;
            for(Team team : teams){
                if(team.id == t){
                    sb.append(result).append("\n");
                    break;
                }
                result++;
            }
        }
        System.out.print(sb);
    }
    
    public static class Team implements Comparable<Team>{
        int id;     // 팀 번호
        int[] point;        // 각 문제의 점수
        int totalPoint;     // 최종 점수
        int submit;     // 제출 횟수
        int finalSubmitTime;     // 제출 시간

        public Team(int id, int k){
            this.id = id;
            this.point = new int[k+1];
        }

        public void updateTotal(){
            totalPoint = 0;
            for(int i=1; i<point.length; i++) totalPoint += point[i];
        }

        @Override
        public int compareTo(Team o){
            if(this.totalPoint != o.totalPoint){
                return o.totalPoint - this.totalPoint;
            }
            if(this.submit != o.submit){
                return this.submit - o.submit;
            }
            return this.finalSubmitTime - o.finalSubmitTime;
        }
    }
}