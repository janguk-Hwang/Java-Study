import java.io.*;
import java.util.*;

// 상대 숫자의 약수이면 승리, 반대로 상대 숫자의 배수이면 패배, 그 외에는 무승부
// 본인을 제외한 다른 모든 플레이어와 정확히 한 번씩 결투를 하고 나면 게임이 종료
// 플레이어의 숫자는 양수이며, 중복은 존재하지 않는다.
public class Main {
    static Map<Integer, Integer> map = new HashMap<>();
    static int Max = 1_000_000;
    static int[] arr, clone, point;
    static int n;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        point = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        clone = arr.clone();
        Arrays.sort(clone);
        for(int i=0; i<n; i++) map.put(clone[i], i);    // (6, 0), (7, 1), (8, 2), (12, 3)
                                                        // point -> 1 0 0 -1
        // i의 배수들만 순회하는 배수 순회 방식
        for(int i=0; i<n; i++){
            int temp = clone[i];
            for(int j=2*temp; j<=Max; j+=temp){
                Integer k = map.get(j);
                if(k != null){
                    point[i]++;
                    point[k]--;
                }
            }
        }
        for(int i=0; i<n; i++) sb.append(point[map.get(arr[i])]).append(" ");
        System.out.print(sb);
    }
}