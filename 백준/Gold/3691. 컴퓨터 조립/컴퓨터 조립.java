import java.io.*;
import java.util.*;

// 맵에 <부품 - (비용, 성능)> 형태로 저장, 이름은 어차피 중복이 없고 출력 시 이름을 출력할 필요없음.
public class Main {
    static int t, n, b, max;
    static Map<String, List<int[]>> map;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            map = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            max = 0;
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                st.nextToken();
                int price = Integer.parseInt(st.nextToken());
                int quality = Integer.parseInt(st.nextToken());
                // type이 이미 있으면 type에 추가, 없으면 리스트 생성 후 가격, 성능 추가
                map.computeIfAbsent(type, k->new ArrayList<>()).add(new int[]{price, quality});
            }
            Set<Integer> set = new HashSet<>();
            for(List<int[]> list : map.values()){
                for(int[] temp : list){
                    set.add(temp[1]);
                }
            }
            List<Integer> list = new ArrayList<>(set);
            list.sort(Collections.reverseOrder());

            int rst = 0;
            for(int i : list){
                if(isPossible(i)){
                    rst = i;
                    break;
                }
            }
            sb.append(rst).append("\n");
        }
        System.out.print(sb);
    }

    public static boolean isPossible(int q){    // q : 성능 하한선
        int sum = 0;
        // 모든 type 순회
        for(List<int[]> list : map.values()){
            int min = Integer.MAX_VALUE;    // 부품의 최소 가격
            // type 별 부품 순회
            for(int[] temp : list){
                if(temp[1] >= q){
                    min = Math.min(min, temp[0]);
                }
            }
            // q보다 성능이 좋은 부품이 없으면 조립 불가하므로 false 반환
            if(min == Integer.MAX_VALUE) return false;
            sum += min;
            if(sum > b) return false;
        }
        // 모든 type에서 q이상의 부품 선택이 예산 내에 가능하면 true 반환
        return true;
    }
}