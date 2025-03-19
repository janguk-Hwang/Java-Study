import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long mod = 1_000_000_007;
    static long[] segTree;
    static int treeSize, startIdx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        treeSize = TreeSize();
        startIdx = treeSize / 2;
        segTree = new long[treeSize];
        Arrays.fill(segTree, 1); // 곱셈이므로 초기값을 1로 설정

        for (int i = 0; i < N; i++) {
            segTree[startIdx + i] = Long.parseLong(br.readLine());
        }
        init();

        int p = M + K;
        while (p-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            // a가 1인 경우 b번째 수를 c로 변경
            if (a == 1) {
                update(b + startIdx - 1, c);
            }
            // a가 2인 경우 b번째 수부터 c번째 수까지 누적곱 출력
            else if (a == 2) {
                sb.append(multi(b + startIdx - 1, (int) (c + startIdx - 1))).append('\n');
            }
        }
        System.out.print(sb);
    }

    // 세그먼트 트리 생성 함수
    static void init() {
        for (int i = startIdx - 1; i > 0; i--) {
            segTree[i] = (segTree[i * 2] * segTree[i * 2 + 1]) % mod;
        }
    }

    // 업데이트 함수
    static void update(int index, long value) {
        segTree[index] = value;
        while (index > 1) {
            index /= 2;
            segTree[index] = (segTree[index * 2] * segTree[index * 2 + 1]) % mod;
        }
    }

    // 부분 곱 함수
    static long multi(int left, int right) {
        // 초기값을 1로 변경
        long result = 1;
        // start와 end가 같아지거나 엇갈리게 되면 구간 내부에 있는 모든 리프노드를 누적곱한것임
        while (left <= right) {
            // 시작이 오른쪽 자식인 경우 multi_sum에 누적곱
            // end_index가 왼쪽 자식이면 multi_sum에 누적곱
            if (left % 2 == 1) result = (result * segTree[left]) % mod;
            if (right % 2 == 0) result = (result * segTree[right]) % mod;
            // 입력으로 주어지는 값이 0보다 크거나 같기 때문에 최적화
            if(result == 0){
                return 0;
            }
            left = (left + 1) / 2;
            right = (right - 1) / 2;
        }
        return result;
    }

    //2^k >= N 을 만족하는 최소 k에 대하여 2^k*2
    public static int TreeSize() {
        int i = 0;
        while(true){
            if(Math.pow(2, i++) >= N){
                // i++가 후위 연산자라서 if문의 조건을 만족하는 i 값에서 증가되고 return
                // 즉, 리프노드만이 아닌 전체 세그먼트 트리의 노드 개수를 아우르는 값을 리턴
                return (int)Math.pow(2, i);
            }
        }
    }
}
