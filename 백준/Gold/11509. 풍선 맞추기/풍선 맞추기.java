import java.io.*;
import java.util.*;

// 풍선들은 왼쪽부터 오른쪽까지 일렬로 있다. -> 같은 열에 하나의 풍선만 존재한다 ->
// 왼쪽에서 오른쪽으로 순회하면서 풍선이 있는지만 검사하면 된다.
// 관리해야 하는 것은 화살의 개수
public class Main {
    static int n, rst;
    static int[] cnt;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        cnt = new int[1_000_001];
        rst = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int height = Integer.parseInt(st.nextToken());
            // 화살 추가 사용 X
            if(cnt[height] > 0){
                cnt[height]--;
                cnt[height - 1]++;
            }
            // 화살 추가 사용 O
            else{
                rst++;
                cnt[height - 1]++;
            }
        }
        System.out.println(rst);
    }
}