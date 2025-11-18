import java.io.*;
import java.util.*;

// 각 단어마다 a-z까지 단어에 포함되는 알파벳을 표시하는 것보다 자음들이 자신을 포함하는 단어들의 인덱스를 갖고 있으면 훨씬 효율적이다.
// 각 단어마다 잊혀진 자음의 수를 배열로 관리한다.
public class Main {
    static boolean[] visited;
    static int n, m;
    static int[] loseCnt;
    static List<Integer>[] list;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[27];
        for(int i=1; i<=26; i++) list[i] = new ArrayList<>();
        loseCnt = new int[n + 1];
        visited = new boolean[27];
        for(int i=1; i<=n; i++){
            String word = br.readLine();
            Arrays.fill(visited, false);
            for(int j=0; j<word.length(); j++){
                int idx = word.charAt(j) - 'a' + 1;
                if(visited[idx]) continue;
                visited[idx] = true;
                list[idx].add(i);
            }
        }
        int rst = n;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int com = Integer.parseInt(st.nextToken());
            int idx = st.nextToken().charAt(0) - 'a' + 1;
            if(com == 1){
                for(int p : list[idx]){
                    if(loseCnt[p] == 0) rst--;
                    loseCnt[p]++;
                }
            }
            if(com == 2){
                for(int p : list[idx]){
                    loseCnt[p]--;
                    if(loseCnt[p] == 0) rst++;
                }
            }
            sb.append(rst).append("\n");
        }
        System.out.print(sb);
    }
}