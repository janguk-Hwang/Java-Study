import java.io.*;
import java.util.*;

public class Main {
    static int sangmin, jisu, n;
    static PriorityQueue<Gift> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        sangmin = Integer.parseInt(st.nextToken());     // 상민이 포장 시간
        jisu = Integer.parseInt(st.nextToken());        // 지수 포장 시간
        n = Integer.parseInt(st.nextToken());
        int sangminFin = 0;
        int jisuFin = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int m = Integer.parseInt(st.nextToken());
            for(int j=0; j<m; j++){
                if(c == 'B'){
                    if(sangminFin >= t){
                        pq.add(new Gift(sangminFin, 'B'));
                        sangminFin += sangmin;
                    }
                    else{
                        pq.add(new Gift(t, 'B'));
                        sangminFin = t + sangmin;
                    }
                    continue;
                }
                if(jisuFin >= t){
                    pq.add(new Gift(jisuFin, 'R'));
                    jisuFin += jisu;
                }
                else{
                    pq.add(new Gift(t, 'R'));
                    jisuFin = t + jisu;
                }
            }
        }

        ArrayList<Integer> bGift = new ArrayList<>();
        ArrayList<Integer> rGift = new ArrayList<>();
        int num = 1;
        while(!pq.isEmpty()){
            Gift gift = pq.poll();
            if(gift.color == 'B'){
                bGift.add(num);
                num++;
                continue;
            }
            rGift.add(num);
            num++;
        }
        sb.append(bGift.size()).append("\n");
        for(Integer k : bGift) sb.append(k).append(" ");
        sb.append("\n");

        sb.append(rGift.size()).append("\n");
        for(Integer p : rGift) sb.append(p).append(" ");
        System.out.print(sb);
    }

    public static class Gift implements Comparable<Gift> {
        int startTime;
        char color;
        public Gift(int startTime, char color) {
            this.startTime = startTime;
            this.color = color;
        }
        @Override
        public int compareTo(Gift o) {
            if (this.startTime == o.startTime) return this.color - o.color;
            return this.startTime - o.startTime;
        }
    }
}