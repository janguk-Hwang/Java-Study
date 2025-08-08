import java.io.*;
import java.util.*;

// 해시 맵 사용해서 수, 수의 등장 횟수 저장
// 해시 맵 정렬 기준은 등장 횟수 순으로, 등장 횟수가 같으면 수가 커지는 순으로
public class Main {
    static int r, c, k;
    static int row = 3;
    static int col = 3;
    static int[][] arr = new int[101][101];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i=1; i<=3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        int time = 0;
        while(time <= 100){
            if(arr[r][c] == k){
                System.out.print(time);
                return;
            }
            if(row >= col) rCalc();
            else cCalc();
            time++;
        }
        System.out.print(-1);
    }

    public static void rCalc(){
        int maxCol = 0;
        for(int i=1; i<=row; i++){
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int j=1; j<=col; j++){
                if(arr[i][j] == 0) continue;
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }
            int[][] temp = new int[map.size()][2];
            int idx = 0;
            for(Map.Entry<Integer, Integer> m : map.entrySet()){
                temp[idx][0] = m.getKey();      // 수
                temp[idx][1] = m.getValue();        // 수 등장 횟수
                idx++;
            }
            Arrays.sort(temp, (o1, o2) -> {
               if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
               return Integer.compare(o1[1], o2[1]);
            });
            // 배열 채우기
            idx = 0;
            for(int[] t : temp){
                if(idx + 1 > 100) break;
                arr[i][++idx] = t[0];
                if(idx + 1 > 100) break;
                arr[i][++idx] = t[1];
            }
            maxCol = Math.max(maxCol, idx);
            for(int j=idx+1; j<=100; j++){
                arr[i][j] = 0;
            }
        }
        col = maxCol;
    }

    public static void cCalc(){
        int maxRow = 0;
        for(int j=1; j<=col; j++){
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int i=1; i<=row; i++){
                if(arr[i][j] == 0) continue;
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }
            int[][] temp = new int[map.size()][2];
            int idx = 0;
            for(Map.Entry<Integer, Integer> m : map.entrySet()){
                temp[idx][0] = m.getKey();      // 수
                temp[idx][1] = m.getValue();        // 수 등장 횟수
                idx++;
            }
            Arrays.sort(temp, (o1, o2) -> {
                if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[1], o2[1]);
            });
            // 배열 채우기
            idx = 0;
            for(int[] t : temp){
                if(idx + 1 > 100) break;
                arr[++idx][j] = t[0];
                if(idx + 1 > 100) break;
                arr[++idx][j] = t[1];
            }
            maxRow = Math.max(maxRow, idx);
            for(int i=idx+1; i<=100; i++) arr[i][j] = 0;
        }
        row = maxRow;
    }
}