import java.io.*;
import java.util.*;

// 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성
// visited[A][B][C] - 세 물통의 현재 물양
public class Main {
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][][] visited;
    static int aBottle, bBottle, cBottle;
    static List<Integer> possibleCCapacity = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        aBottle = Integer.parseInt(st.nextToken());
        bBottle = Integer.parseInt(st.nextToken());
        cBottle = Integer.parseInt(st.nextToken());
        visited = new boolean[aBottle + 1][bBottle + 1][cBottle + 1];

        q.offer(new int[]{0, 0, cBottle});
        visited[0][0][cBottle] = true;
        while(!q.isEmpty()){
            int[] temp = q.poll();
            // A가 비어있을 때, C의 현재 물 양 기록
            if(temp[0] == 0) possibleCCapacity.add(temp[2]);
            // A -> B
            move(temp[0], temp[1], temp[2], 0, 1);
            // A -> C
            move(temp[0], temp[1], temp[2], 0, 2);
            // B -> A
            move(temp[0], temp[1], temp[2], 1, 0);
            // B -> C
            move(temp[0], temp[1], temp[2], 1, 2);
            // C -> A
            move(temp[0], temp[1], temp[2], 2, 0);
            // C -> B
            move(temp[0], temp[1], temp[2], 2, 1);
        }
        Collections.sort(possibleCCapacity);
        for(Integer i : possibleCCapacity) sb.append(i).append(" ");
        System.out.print(sb);
    }

    public static void move(int a, int b, int c, int from, int to){
        int[] bottle = {aBottle, bBottle, cBottle};
        int[] curAmount = {a, b, c};
        int canMoveAmount = Math.min(curAmount[from], bottle[to] - curAmount[to]);  // 옮길 수 있는 물의 양
        if(canMoveAmount == 0) return;
        curAmount[from] -= canMoveAmount;
        curAmount[to] += canMoveAmount;
        if(!visited[curAmount[0]][curAmount[1]][curAmount[2]]){
            visited[curAmount[0]][curAmount[1]][curAmount[2]] = true;
            q.offer(new int[]{curAmount[0], curAmount[1], curAmount[2]});
        }
    }
}