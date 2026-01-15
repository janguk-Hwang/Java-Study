import java.io.*;
import java.util.*;

// 정점은 상관없지만 간선은 한 번만 지나가야 한다. -> 한 붓 그리기
// 한 붓 그리기가 되기 위한 조건: 진입차수가 홀수인 정점의 개수가 0개이거나 2개여야 한다.
// 0개인 경우는 시작점과 끝점이 같은 경우(시작점이 홀수가 되지 않으려면 들어오는 것이 있어야 한다.)
// 2개인 경우는 시작점과 끝점이 다른 경우(시작점은 들어오는 것이 없고 끝점은 나가는 것이 없어 시작점과 끝점이 각각 홀수)
// 차수가 홀수인 정점이 0개인 경우
//     A —— B
//     |    |
//     D —— C
// 차수가 홀수인 정점이 2개인 경우
//       B
//       |
//   A — C — D — E
//       |       |
//       F -------
public class Main {
    static int v, e;
    static int[] parent, degree;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        parent = new int[v + 1];
        degree = new int[v + 1];
        for(int i=1; i<=v; i++) parent[i] = i;
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
            degree[a]++;
            degree[b]++;
        }
        // 모든 정점이 연결되었는지 검사
        int root = find(1);
        for(int i=2; i<=v; i++){
            if(find(i) != root){
                System.out.print("NO");
                return;
            }
        }

        int oddVertex = 0;
        // 차수가 홀수인 정점의 개수 확인
        for(int i=1; i<=v; i++) if(degree[i] % 2 == 1) oddVertex++;
        System.out.print((oddVertex == 0 || oddVertex == 2) ? "YES" : "NO");
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[b] = a;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}