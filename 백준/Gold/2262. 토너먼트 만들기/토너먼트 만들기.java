import java.io.*;
import java.util.*;

// 랭킹이 낮은 사람들을 먼저 탈락시켜야 한다. 여러 번 반영되지 않도록
// 대신 탈락시킬 때, 양 옆을 보고 랭킹 차이를 최소로 하는 사람과 경기를 한다.
// 어차피 랭킹이 높은 사람들은 살아남는다.
public class Main {
    static int n, diff, rst;
    static ArrayList<Integer> list = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) list.add(Integer.parseInt(st.nextToken()));
        rst = 0;
        while(list.size() > 1){
            // 현재 남은 사람 중 랭킹이 낮은 사람의 인덱스
            int idx = list.indexOf(n);
            // 해당 사람이 맨 앞인 경우
            if(idx == 0) diff = list.get(idx) - list.get(idx+1);
            // 해당 사람이 맨 끝인 경우
            else if(idx == list.size()-1) diff = list.get(idx) - list.get(idx-1);
            // 왼쪽 사람과 오른쪽 사람 중 차이가 덜 나는 사람과 경기 진행
            else diff = Math.min(list.get(idx) - list.get(idx-1), list.get(idx) - list.get(idx+1));
            rst += diff;
            list.remove(idx);
            n--;
        }
        System.out.println(rst);
    }
}