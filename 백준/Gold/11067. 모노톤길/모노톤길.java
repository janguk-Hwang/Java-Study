import java.io.*;
import java.util.*;

public class Main{
    static HashMap<Integer, TreeSet<Integer>> xMap;
    static HashMap<Integer, TreeSet<Integer>> yMap;
    static TreeSet<Integer> ts;
    static int[] order;
    static Pos[] cafe;
    static int t, n, m;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)throws Exception{
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            cafe = new Pos[n+1];
            xMap = new HashMap<>();
            yMap = new HashMap<>();
            int start = 1;
            for(int i=1; i<=n; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                cafe[i] = new Pos(x, y);
                if(x==0 && y==0) start = i;
                // y좌표가 같으면 key값(카페 번호) 오름차순 다르면
                xMap.computeIfAbsent(x, k -> new TreeSet<>((a,b) -> cafe[a].y - cafe[b].y)).add(i);
                yMap.computeIfAbsent(y, k -> new TreeSet<>((a,b) -> cafe[a].x - cafe[b].x)).add(i);
            }
            order = new int[n+1];
            int idx = start;
            order[1] = idx;
            xMap.get(cafe[idx].x).remove(idx);
            yMap.get(cafe[idx].y).remove(idx);
            for(int i=2; i<=n; i++){
                int cx = cafe[idx].x;
                int cy = cafe[idx].y;
                Integer next = null;
                // x
                ts = xMap.get(cx);
                if(!ts.isEmpty()){
                    Integer low = ts.lower(idx);
                    Integer high = ts.higher(idx);
                    if(low != null) next = low;
                    else if(high != null) next = high;
                }
                // y
                if(next == null){
                    ts = yMap.get(cy);
                    if(!ts.isEmpty()){
                        Integer low = ts.lower(idx);
                        Integer high = ts.higher(idx);
                        if(low != null) next = low;
                        else if(high != null) next = high;
                    }
                }
                if(next != null){
                    idx = next;
                    order[i] = next;
                    xMap.get(cafe[next].x).remove(next);
                    yMap.get(cafe[next].y).remove(next);
                }
            }
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            while(m-- >0){
                int k = Integer.parseInt(st.nextToken());
                sb.append(cafe[order[k]].x).append(" ").append(cafe[order[k]].y).append("\n");
            }
        }
        System.out.print(sb);
    }

    static class Pos{
        int x, y;
        Pos(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}