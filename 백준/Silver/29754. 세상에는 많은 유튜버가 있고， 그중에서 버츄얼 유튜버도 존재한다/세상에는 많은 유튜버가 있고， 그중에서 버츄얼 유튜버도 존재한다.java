import java.io.*;
import java.util.*;

// 매주 5회, 매주 총 60시간 이상
// 매주 만족해야 함
public class Main {
    static int n, m, week;
    static Map<String, Map<Integer, Youtuber>> map = new HashMap<>();   // 이름, <주차 수, Youtuber(주간 방송 횟수, 주간 총 방송 시간)>
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        week = m / 7;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            String[] temp1 = st.nextToken().split(":");
            String[] temp2 = st.nextToken().split(":");
            int start = Integer.parseInt(temp1[0]) * 60 + Integer.parseInt(temp1[1]);
            int end = Integer.parseInt(temp2[0]) * 60 + Integer.parseInt(temp2[1]);
            int liveTime = end - start;
            int week = (day - 1) / 7;
            map.putIfAbsent(name, new HashMap<>());
            Map<Integer, Youtuber> weekMap = map.get(name);
            weekMap.putIfAbsent(week, new Youtuber());
            weekMap.get(week).add(liveTime);
        }
        List<String> result = new ArrayList<>();
        for(String name : map.keySet()){
            Map<Integer, Youtuber> weekMap = map.get(name);
            boolean flag = true;
            for(int i=0; i<week; i++){
                Youtuber y = weekMap.get(i);
                if(y == null || y.cnt < 5 || y.totalLive < 3600){
                    flag = false;
                    break;
                }
            }
            if(flag) result.add(name);
        }
        if(result.isEmpty()){
           System.out.print(-1);
           return;
        }
        Collections.sort(result);
        for(String name : result) sb.append(name).append("\n");
        System.out.print(sb);
    }

    public static class Youtuber{
        int cnt; int totalLive; // 주간 방송 횟수, 주간 총 방송 시간
        public Youtuber(){
            this.cnt = 0; this.totalLive = 0;
        }
        public void add(int minute){
            this.cnt++;
            this.totalLive += minute;
        }
    }
}