import java.io.*;
import java.util.*;

// 15!을 완전탐색을 불가
// 백트래킹에서 가지치기하면 가능
public class Main {
    static boolean[] visited;
    static int[][] arr;
    static int n, mp, mf, ms, mv;
    static PriorityQueue<Food> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());
        arr = new int[n][5];    // 단백질, 지방, 탄수화물, 비타민, 가격
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        backtracking(0, new int[5], new ArrayList<>());
        if(pq.isEmpty()){
            System.out.print(-1);
            return;
        }
        Food rst = pq.poll();
        sb.append(rst.cost).append("\n");
        for(Integer i : rst.foodList) sb.append(i).append(" ");
        System.out.print(sb);
    }

    // 매개변수에는 각 영양분의 양과 총 비용, 종료 기준은 모든 식재료를 선택한 경우
    public static void backtracking(int idx, int[] total, List<Integer> chosenFood){
        // 가지치기(기존 최소 비용보다 크면 더 이상 탐색 x)
        if(!pq.isEmpty() && total[4] >= pq.peek().cost) return;
        // 조건을 만족하면 우선순위 큐에 삽입
        if(total[0] >= mp && total[1] >= mf && total[2] >= ms && total[3] >= mv){
            pq.offer(new Food(total[4], new ArrayList<>(chosenFood)));
        }
        // 가능한 모든 경우 선택
        for(int i=idx; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                chosenFood.add(i+1);
                for(int j=0; j<5; j++) total[j] += arr[i][j];

                backtracking(i+1, total, chosenFood);

                for(int j=0; j<5; j++) total[j] -= arr[i][j];
                chosenFood.remove(chosenFood.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static class Food implements Comparable<Food>{
        int cost;
        List<Integer> foodList;       // 선택한 식재료 리스트
        public Food(int cost, List<Integer> ingredientList){
            this.cost = cost; this.foodList = ingredientList;
        }
        @Override
        public int compareTo(Food o){
            // 비용 순으로 정렬
            if(this.cost != o.cost) return this.cost - o.cost;
            
            // 사전순으로 정렬
            int len = Math.min(this.foodList.size(), o.foodList.size());
            for(int i=0; i<len; i++){
                if(this.foodList.get(i).equals(o.foodList.get(i))){
                    return this.foodList.get(i) - o.foodList.get(i);
                }
            }
            return 0;
        }
    }
}