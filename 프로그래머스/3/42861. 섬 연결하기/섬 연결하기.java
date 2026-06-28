import java.util.*;

class Solution {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        int answer = 0;
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
        for(int[] arr : costs){
            int a = arr[0];
            int b = arr[1];
            int cost = arr[2];
            if(find(a) != find(b)){
                union(a, b);
                answer += cost;
            }
        }
        return answer;
    }
    
    public static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
    
    public static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }
}