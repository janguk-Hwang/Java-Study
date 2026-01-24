import java.io.*;
import java.util.*;

// 좌표마다 일일히 다 하면 시간초과, 스위핑하면서
// 어떤 x좌표에 대해서 여러 건물들의 시작과 끝이 있을 수 있으므로 반례가 생기지 않도록 가장 높은 높이의 건물의 시작부터 처리해야 한다.
// 우선순위 큐에 끝나는 이벤트는 나중에 처리되도록 음수록 넣는다.
public class Main {
    static int n;
    static TreeMap<Integer, Integer> treeMap = new TreeMap<>(Collections.reverseOrder());
    static PriorityQueue<Building> pq = new PriorityQueue<>((a, b) -> {
        if(a.x == b.x) return b.height - a.height;
        return a.x - b.x;
    });
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());   // 왼쪽 x좌표
            int h = Integer.parseInt(st.nextToken());   // 높이
            int r = Integer.parseInt(st.nextToken());   // 오른쪽 x좌표
            // 이벤트가 발생하는 곳들을 삽입
            pq.offer(new Building(l, h));
            pq.offer(new Building(r, -h));
        }
        treeMap.put(0, 1);
        int prevH = 0;
        while(!pq.isEmpty()){
            Building building = pq.poll();
            // 빌딩의 시작이면
            if(building.height > 0) treeMap.put(building.height, treeMap.getOrDefault(building.height, 0) + 1);
            // 끝이면 감소, 감소한 값이 0이면 제거
            else{
                int height = -building.height;
                int cnt = treeMap.get(height);
                if(cnt == 1) treeMap.remove(height);
                else treeMap.put(height, cnt - 1);
            }
            // 이벤트가 발생한 곳에서 가장 높은 건물의 높이
            int curMaxHeight = treeMap.firstKey();
            if(prevH != curMaxHeight){
                sb.append(building.x).append(" ").append(curMaxHeight).append(" ");
                prevH = curMaxHeight;
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class Building{
        int x; int height;
        Building(int x, int height){
            this.x = x; this.height = height;
        }
    }
}