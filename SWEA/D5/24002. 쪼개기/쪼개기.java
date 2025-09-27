import java.io.*;
import java.util.*;

public class Solution {
    static int t, n;
    static int[] arr;
    static int rst;
    static List<Map<Integer, Integer>> possibleCost;
    static Set<Integer> set;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            rst = Integer.MAX_VALUE;
            possibleCost = new ArrayList<>();
            set = new HashSet<>();
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
            for(int i=0; i<n; i++){
                Map<Integer, Integer> map = new HashMap<>();
                findPossible(arr[i], 0, map);
                // 각 막대가 만들어 질 수 있는 길이와 연산 횟수가 쌍으로 저장
                possibleCost.add(map);
                // set에 가능한 모든 길이를 저장
                set.addAll(map.keySet());
            }
            // 가능한 길이를 순회
            for(Integer temp : set){
                boolean flag = true;
                int sum = 0;
                for(int i=0; i<n; i++){
                    Map<Integer, Integer> map = possibleCost.get(i);
                    // 만들 수 있으면 sum에 연산 횟수 누적
                    if(map.containsKey(temp)){
                        sum += map.get(temp);
                        continue;
                    }
                    // 하나의 막대라도 불가능하면 flag를 false로
                    flag = false;
                    break;
                }
                if(flag) rst = Math.min(rst, sum);
            }
            sb.append(rst).append("\n");
        }
        System.out.print(sb);
    }

    static void findPossible(int length, int cost, Map<Integer, Integer> map){
        if(length == 0) return;
        // 이전에 length 길이의 막대를 만든 적이 있고, 그 비용이 cost보다 작다면 더 이상 탐색 x
        if(map.containsKey(length) && map.get(length) <= cost) return;
        // 삽입 및 덮어쓰기
        map.put(length, cost);
        if(length >= 2){
            int stick1 = length / 2;
            int stick2 = length - stick1;
            findPossible(stick1, cost+1, map);
            findPossible(stick2, cost+1, map);
        }
    }
}