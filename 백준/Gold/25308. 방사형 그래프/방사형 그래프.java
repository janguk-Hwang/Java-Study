import java.io.*;
import java.util.*;

// 인접한 꼭짓점끼리는 45도씩 차이가 난다.
// 각 꼭짓점에서 양 옆에 꼭짓점과의 각의 합이 180도 이하가 되어야 한다.
// 중앙의 점과 어떤 꼭짓점의 양 옆의 꼭짓점으로 만들어지는 삼각형의 넓이가 180도일 때의 넓이를 의미한다.
// 그러므로 어떤 꼭짓점의 양 옆의 꼭짓점과의 넓이가 이 값보다 커야 180도가 넘어서 볼록 다각형이 될 수 있다.
public class Main {
    static int rst;
    static boolean[] visited = new boolean[9];
    static int[] order = new int[9];
    static int[] arr = new int[9];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=8; i++) arr[i] = Integer.parseInt(st.nextToken());
        comb(0);
        System.out.print(rst);
    }

    static void comb(int depth){
        if(depth == 8){
            if(isPossible()) rst++;
            return;
        }
        for(int i=1; i<=8; i++){
            if(!visited[i]){
                visited[i] = true;
                order[depth] = arr[i];
                comb(depth + 1);
                visited[i] = false;
            }
        }
    }

    static boolean isPossible(){
        for(int i=0; i<8; i++){
            int b = (i + 1) % 8;
            int c = (i + 2) % 8;
            double misino = (double) (order[i] * order[c]) / 2;
            double area1 = (double) order[i] * order[b] / Math.sqrt(2) / 2;
            double area2 = (double) order[c] * order[b] / Math.sqrt(2) / 2;
            // 180도보다 작으면(오목하면) false
            if(misino > area1 + area2) return false;
        }
        // 모든 각이 볼록하면 true 반환
        return true;
    }
}