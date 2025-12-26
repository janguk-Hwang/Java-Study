import java.io.*;
import java.util.*;

// 위상정렬에서는 선행조건들이 제거되야 진행되었지만 이 문제에서는 반대이다.
// 요구하는 모든 선행조건들이 있어야 진행가능하다.
public class Main {
    static int n, m, k;
    static List<Integer>[] adj;
    static int[] indegree;     // 아직 충족되지 않은 선행 조건 개수
    static int[] buildCount;   // 현재 존재하는 건물 개수
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        indegree = new int[n + 1];
        buildCount = new int[n + 1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj[from].add(to);
            indegree[to]++;
        }
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken());
            if(command == 1){
                // 선행 조건이 아직 남아 있다면 "Lier" 출력
                if(indegree[building] != 0){
                    System.out.print("Lier!");
                    return;
                }
                buildCount[building]++;
                // 처음 건설되는 경우, next들의 요구하는 선행조건 개수 감소
                if(buildCount[building] == 1){
                    for(int next : adj[building]){
                        indegree[next]--;
                    }
                }
            }
            else{
                // 존재하지 않는 건물 파괴 시도 시 "Lier" 출력
                if(buildCount[building] == 0){
                    System.out.print("Lier!");
                    return;
                }
                buildCount[building]--;
                // 마지막 하나가 사라질 때, next들의 요구하는 선행조건 개수 증가
                if(buildCount[building] == 0){
                    for(int next : adj[building]){
                        indegree[next]++;
                    }
                }
            }
        }
        System.out.print("King-God-Emperor");
    }
}