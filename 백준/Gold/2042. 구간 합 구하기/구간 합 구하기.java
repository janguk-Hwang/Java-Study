import java.io.*;
import java.util.*;

import static java.lang.Math.ceil;
// 입력으로 주어지는 모든 수는 -2^63보다 크거나 같고, 2^63-1보다 작거나 같은 정수이다.
public class Main {
    static StringTokenizer st;
    static int N, M, K;
    static long[] seg_tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int tree_size = TreeSize();

        seg_tree = new long[tree_size];

        int start_index = tree_size / 2;

        //입력 받기
        for(int i=0; i<N; i++){
            seg_tree[start_index + i] = Long.parseLong(br.readLine());
        }

        //세그먼트 트리 생성
        init(start_index);

        int p = M+K;

        while(p-- > 0){
            st = new StringTokenizer(br.readLine());
            // a,b의 범위는 1 ~ 1_000_000
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){
                update(b + start_index - 1, c);
            }
            else if(a == 2){
                System.out.println(sum(b + start_index - 1, (int)(c + start_index - 1)));
            }
        }
    }

    public static void init(int start_index) {
        for(int i=start_index; i<start_index+N; i++){
            int idx2 = i;
            while(idx2 > 1){
                idx2 /= 2;
                seg_tree[idx2] += seg_tree[i];
            }
        }
    }

    public static long sum(int start_index, int end_index) {
        long sum = 0;

        while(start_index <= end_index) {
            // 시작이 오른쪽 자식인 경우 독립 노드로
            if(start_index % 2 == 1){
                sum += seg_tree[start_index];
            }
            // 끝이 왼쪽 자식인 경우 독립노드로
            if(end_index % 2 == 0){
                sum += seg_tree[end_index];
            }

            // 더 안쪽 노드로 이동
            start_index = (start_index + 1) / 2;
            end_index = (end_index - 1) / 2;
        }

        return sum;
    }

    //어떤 노드가 값이 바뀌면 해당 노드를 포함하는 부모 노드들을 모두 갱신
    public static void update(int index, long value){    //해당 index를 value로 update
        long diff = value - seg_tree[index];
        //value로 해당 노드를 바꾸고
        seg_tree[index] = value;
        //루트 노드 전까지
        while(index > 1){
            //부모 노드 갱신
            seg_tree[index / 2] += diff;
            index /= 2;
        }
    }

    //2^k >= N 을 만족하는 최소 k에 대하여 2^k*2
    private static int TreeSize() {
        int i = 0;
        while(true){
            if(Math.pow(2, i++) >= N){
                return (int)Math.pow(2, i);
            }
        }
    }
}
