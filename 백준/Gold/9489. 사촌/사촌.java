import java.io.*;
import java.util.*;

// 2 이상 차이가 나는 곳을 기준으로 집합이 구분된다.
// 입력은 항상 증가하는 수열이 주어진다.
public class Main {
    static int n, k, kIdx, groupNum;
    static int[] arr, parent;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            if(n == 0) break;
            kIdx = 0;
            arr = new int[n + 1];
            parent = new int[n + 1];
            groupNum = -1;
            arr[0] = -1;
            parent[0] = -1;
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                if(arr[i] == k) kIdx = i;
                // 연속이 끊기면 새로운 그룹의 생성을 의미
                if(arr[i] != arr[i-1] + 1) groupNum++;
                parent[i] = groupNum;
            }
            int rst = 0;
            for(int i=1; i<=n; i++){
                // 단순히 부모가 다르다고 사촌이 아니라 전제 조건으로 부모의 부모가 같아야 한다.
                if(parent[i] != parent[kIdx] && parent[parent[i]] == parent[parent[kIdx]]) rst++;
            }
            sb.append(rst).append("\n");
        }
        System.out.print(sb);
    }
}