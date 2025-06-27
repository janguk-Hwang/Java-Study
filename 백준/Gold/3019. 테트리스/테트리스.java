import java.io.*;
import java.util.*;

// 기존 블록과 새로 떨어뜨리는 블록은 최소 한 칸에서 만난다.
// 블록의 가로 길이만큼 기존 블록과 높이를 비교한다.
// 새로운 블록의 i열 높이 - 기존 블록의 i열 높이 == 새로운 블록의 i+1열의 높이 - 기존 블록의 i+1열 높이가 새 블록의 길이 구간에서 모두 만족해야 한다.
// 한 번이라도 다르다면 틈이 생기는 것이다.
public class Main {
    static int c, p;
    static int[] arr;
    static ArrayList<int[][]> list = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        arr = new int[c];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<c; i++) arr[i] = Integer.parseInt(st.nextToken());
        initBlock();
        int cnt = 0;
        for(int[] pattern : list.get(p-1)){
            int length = pattern.length;
            for(int idx=0; idx<=c-length; idx++){
                boolean flag = true;
                for(int i=0; i<length-1; i++){
                    int diff1 = arr[idx + i] - pattern[i];
                    int diff2 = arr[idx + i + 1] - pattern[i + 1] ;
                    if(diff1 != diff2){
                        flag = false;
                        break;
                    }
                }
                if(flag) cnt++;
            }
        }
        System.out.print(cnt);
    }

    public static void initBlock(){
        // 1
        list.add(new int[][]{
                {0}, {0, 0, 0, 0}
        });
        // 2
        list.add(new int[][]{
                {0, 0}
        });
        // 3
        list.add(new int[][]{
                {0, 0, 1}, {1, 0}
        });
        // 4
        list.add(new int[][]{
                {1, 0, 0}, {0, 1}
        });
        // 5
        list.add(new int[][]{
                {0, 0, 0}, {0, 1}, {1, 0, 1}, {1, 0}
        });
        // 6
        list.add(new int[][]{
                {0, 0, 0}, {0, 0}, {0, 1, 1}, {2, 0}
        });
        // 7
        list.add(new int[][]{
                {0, 0, 0}, {0, 2}, {1, 1, 0}, {0, 0}
        });
    }
}