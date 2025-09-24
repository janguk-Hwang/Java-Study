import java.io.*;
import java.util.*;

public class Main {
    static String str;
    static Stack<Integer> stack = new Stack<>();
    static boolean[] visited;
    static List<int[]> pos = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static List<String> list = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        str = br.readLine();
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '('){
                stack.push(i);
                continue;
            }
            // 짝인 두 괄호의 인덱스를 리스트에 저장
            if(str.charAt(i) == ')') pos.add(new int[]{stack.pop(), i});
        }
        visited = new boolean[str.length()];    // 참이면 해당 문자는 제거되야 함
        comb(0);
        Set<String> set = new TreeSet<>(list);
        for(String s : set) sb.append(s).append("\n");
        System.out.print(sb);
    }

    static void comb(int depth){
        StringBuilder sb1 = new StringBuilder();
        // 종료 조건 (모든 괄호 쌍을 각각 제거할지 안 할지 결정이 끝났으면)
        if(depth == pos.size()){
            for(int i=0; i<str.length(); i++) if(!visited[i]) sb1.append(str.charAt(i));
            if(!String.valueOf(sb1).equals(str)){
                list.add(String.valueOf(sb1));
            }
            return;
        }
        int[] temp = pos.get(depth);
        int left = temp[0]; int right = temp[1];
        visited[left] = true;
        visited[right] = true;
        comb(depth + 1);
        visited[right] = false;
        visited[left] = false;

        // pos.get(depth)에서의 괄호를 제거한 조합으로 모두 탐색했으면 유지하는 경우도 진행
        comb(depth + 1);
    }
}