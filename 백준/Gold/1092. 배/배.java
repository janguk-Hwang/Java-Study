import java.io.*;
import java.util.*;

// 무거운 박스는 무게 제한이 높은 크레인으로 옮긴다.
// 무게 제한이 높은 크레인부터 자신이 들 수 있는 박스 중 무거운 박스부터 옮긴다.
public class Main {
    static int n, m;
    static int[] crain;
    static List<Integer> box = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        crain = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) crain[i] = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) box.add(Integer.parseInt(st.nextToken()));

        Arrays.sort(crain);     // 오름차순
        box.sort(Collections.reverseOrder());   // 내림차순
        // 크레인으로 옮길 수 없는 무거운 박스가 있는 경우
        if(box.get(0) > crain[n-1]){
            System.out.print(-1);
            return;
        }

        int time = 0;
        // 모든 박스를 옮길 때까지
        while(!box.isEmpty()){
            int boxIdx = 0;
            // 크레인 수만큼 한 사이클
            for(int i=n-1; i>=0 && boxIdx<box.size(); i--){
                // 크레인이 박스를 들 수 있으면 박스를 옮김
                if(crain[i] >= box.get(boxIdx)){
                    box.remove(boxIdx);
                    continue;
                }
                boxIdx++;
                i++;
            }
            // 크레인들이 한 번씩 옮기는 데 1초가 걸림
            time++;
        }
        System.out.print(time);
    }
}