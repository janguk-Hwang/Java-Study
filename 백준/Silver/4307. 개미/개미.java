import java.util.*;
import java.io.*;

// 가장 빠른 시간은 각 개미가 더 가까운 끝으로 이동해서 마지막 개미가 떨어지는 시간
// 가장 늦는 시간은 가운데 인덱스에 있는 개미가 양 옆 개미와 만나면서 왔다갔다를 반복하는 시간? x
// 개미를 구분할 수 없기 때문에 서로 만나면 방향을 바꾸는 것이 아닌 통과한다고 생각해도 된다.
public class Main {
    static int[] ant;
    static HashMap<String, Integer> hs = new HashMap<>();
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for(int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int l = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            ant = new int[n];
            for(int j=0; j<n; j++){
                st = new StringTokenizer(br.readLine());
                ant[j] = Integer.parseInt(st.nextToken());
            }
            query(l, ant);
            sb.append(hs.get("minimum")).append(" ").append(hs.get("maximum")).append("\n");
        }
        System.out.println(sb);
    }

    public static void query(int l, int[] ant){
        int min = 0;
        int max = 0;

        // 가장 작은 or 큰 값이 담기면 모든 개미가 떨어진 상태
        for(Integer index : ant){
            min = Math.max(min, Math.min(index, l - index));
            max = Math.max(max, Math.max(index, l - index));
        }

        hs.put("minimum", min);
        hs.put("maximum", max);
    }
}