import java.io.*;
import java.util.*;

// 순회하면서 원하는 사람이 나올 때까지 찾지 않고 빠르게 찾기 위해 번호 -> 이름, 이름 -> 번호를 저장해놓는다.
public class Main {
    static String[] name;   // 번호 -> 이름
    static Map<String, Integer> map;
    static ArrayList<Integer>[] adj, childList;
    static int[] indegree;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        name = new String[n];
        map = new HashMap<>();
        adj = new ArrayList[n];
        childList = new ArrayList[n];
        indegree = new int[n];  // 조상 수
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            name[i] = st.nextToken();
            adj[i] = new ArrayList<>();
            childList[i] = new ArrayList<>();
        }
        Arrays.sort(name);
        // 이름 -> 번호
        for(int i=0; i<n; i++) map.put(name[i], i);
        m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int descendant = map.get(st.nextToken());   // 후손 번호
            int ancestor = map.get(st.nextToken());     // 조상 번호
            adj[ancestor].add(descendant);
            // 진입 차수(조상의 수) 증가
            indegree[descendant]++;
        }
        ArrayList<Integer> root = new ArrayList<>();
        topologicalSort(root);
        print(root);
    }

    public static void topologicalSort(ArrayList<Integer> root){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            // 조상의 수가 없으면 큐에 삽입
            if(indegree[i] == 0){
                q.add(i);
                root.add(i);
            }
        }
        while(!q.isEmpty()){
            int cur = q.remove();
            // 자식들의 진입 차수 감소, 진입 차수 0이 된 경우 큐에 삽입
            for(int descendant : adj[cur]){
                indegree[descendant]--;
                // 진입 차수가 0이 되는 순간의 조상이 직속 조상
                if(indegree[descendant] == 0){
                    q.add(descendant);
                    childList[cur].add(descendant);
                }
            }
        }
    }

    public static void print(ArrayList<Integer> root){
        sb.append(root.size()).append("\n");    // 가문의 수
        for(int id : root) sb.append(name[id]).append(" ");     // 가문의 root
        sb.append("\n");
        for(int i=0; i<n; i++){
            sb.append(name[i]).append(" ").append(childList[i].size()).append(" ");
            // 사전순으로 자식들의 이름을 출력
            Collections.sort(childList[i]);
            for(int childId : childList[i]) sb.append(name[childId]).append(" ");
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }
}