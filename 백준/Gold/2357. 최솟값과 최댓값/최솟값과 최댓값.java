import java.util.*;
import java.io.*;

// a, b 쌍이 입력으로 들어오면 a번째 입력부터 b번째 입력 중에서 최소, 최댓값을 구해야 한다.
// 각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.
// init()함수에서 부모 노드를 누적합이나 누적곱이 아닌 최소값, 최댓값으로 설정해야 한다.
// 그럼 트리를 2개를 만들어서 하나는 최소값 트리와 최댓값 트리로 사용해야 한다.
public class Main {
    static StringTokenizer st;
    static long n, m, a, b;
    static long[] min_seg_tree;
    static long[] max_seg_tree;
    static HashMap<String, Long> hashmap;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int tree_size = tree_size_func();
        min_seg_tree = new long[tree_size];
        max_seg_tree = new long[tree_size];
        int start_index = tree_size / 2;

        // 리프노드 채우기
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            long value = Long.parseLong(st.nextToken());
            min_seg_tree[start_index + i] = value;
            max_seg_tree[start_index + i] = value;
        }

        init(start_index);

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            query(a + start_index - 1, b + start_index - 1);
            sb.append(hashmap.get("minimum")).append(" ").append(hashmap.get("maximum")).append("\n");
        }
        System.out.print(sb);
    }

    // init() 함수
    public static void init(int start_index) {
        for(int i=start_index; i<start_index+n; i++){
            int idx2 = i;
            while(idx2 > 1){
                idx2 /= 2;
                min_seg_tree[idx2] = Math.min(min_seg_tree[idx2 * 2], min_seg_tree[idx2 * 2 + 1]);
                max_seg_tree[idx2] = Math.max(max_seg_tree[idx2 * 2], max_seg_tree[idx2 * 2 + 1]);
            }
        }
    }

    public static void query(long start_index, long end_index){
        hashmap = new HashMap<>();
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        while(start_index <= end_index){
            if(start_index % 2 == 1){
                min = Math.min(min, min_seg_tree[(int) start_index]);
                max = Math.max(max, max_seg_tree[(int) start_index]);
            }
            if(end_index % 2 == 0){
                min = Math.min(min, min_seg_tree[(int) end_index]);
                max = Math.max(max, max_seg_tree[(int) end_index]);
            }

            start_index = (start_index + 1) / 2;
            end_index = (end_index - 1) / 2;
        }

        hashmap.put("minimum", min);
        hashmap.put("maximum", max);
    }

    // 트리 사이즈 반환 함수
    public static int tree_size_func() {
        int i = 0;
        while(true){
            if(Math.pow(2, i++) >= n){
                return (int)Math.pow(2, i);
            }
        }
    }
}
