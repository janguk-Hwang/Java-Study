import java.io.*;
import java.util.*;

public class Main{
    static int n;
    static int[] cnt;       // 볼 수 있는 건물의 개수
    static int[][] nearest;     // 가장 가까운 건물과의 거리와 건물의 번호
    static Stack<Building> stack;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        Building[] building = new Building[n+1];
        cnt = new int[n+1];
        nearest = new int[n+1][2];
        for(int[] row : nearest) Arrays.fill(row, 100_001);
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            building[i] = new Building(i, Integer.parseInt(st.nextToken()));
        }
        stack = new Stack<>();
        for(int i=1; i<=n; i++){
            // 스택의 top에 자신보다 작거나 같은 빌딩이 있으면 pop()
            while(!stack.isEmpty() && stack.peek().height <= building[i].height){
                stack.pop();
            }
            cnt[i] += stack.size();     // 좌측을 바라볼 때 볼 수 있는 건물의 수
            if(!stack.isEmpty()){
                int dist = Math.abs(stack.peek().num - i);
                if(dist < nearest[i][1]){
                    nearest[i][0] = stack.peek().num;
                    nearest[i][1] = dist;
                }
            }
            stack.push(building[i]);
        }

        stack = new Stack<>();
        for(int i=n; i>=1; i--){
            while(!stack.isEmpty() && stack.peek().height <= building[i].height){
                stack.pop();
            }
            cnt[i] += stack.size();     // 우측을 바라볼 때 볼 수 있는 건물의 수
            if(!stack.isEmpty()){
                int dist = Math.abs(stack.peek().num - i);
                if(dist < nearest[i][1]){
                    nearest[i][0] = stack.peek().num;
                    nearest[i][1] = dist;
                }
                if(dist == nearest[i][1] && stack.peek().num < nearest[i][0]){
                    nearest[i][0] = stack.peek().num;
                }
            }
            stack.push(building[i]);
        }
        for(int i=1; i<=n; i++){
            if(cnt[i] == 0){
                sb.append(0).append("\n");
                continue;
            }
            sb.append(cnt[i]).append(" ").append(nearest[i][0]).append("\n");
        }
        System.out.print(sb);
    }

    public static class Building{
        int num;
        int height;
        public Building(int num, int height){
            this.num = num;
            this.height = height;
        }
    }
}