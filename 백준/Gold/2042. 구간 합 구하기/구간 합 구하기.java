import java.io.*;
import java.util.*;

public class Main {
    static long[] tree;
    static long[] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // N: 수의 개수, M: 수 변경 횟수, K: 구간 합을 구하는 횟수
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1]; // 1부터 시작하는 배열이므로 크기를 N+1로 설정
        tree = new long[N * 4]; // 세그먼트 트리는 일반적으로 4배 크기로 할당

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 세그먼트 트리 초기화
        init(1, N, 1);

        // M번의 값 변경 및 K번의 구간 합 연산 수행
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()); // 연산 타입 (1: 값 변경, 2: 구간 합)
            int b = Integer.parseInt(st.nextToken()); // 연산에 사용할 인덱스 또는 범위 시작
            long c = Long.parseLong(st.nextToken()); // 값 또는 범위 끝

            if (a == 1) { // 값 변경 연산
                update(1, N, 1, b, c - arr[b]); // 차이값을 업데이트하여 반영
                arr[b] = c; // 원본 배열도 업데이트
            } else if (a == 2) { // 구간 합 연산
                System.out.println(sum(1, N, 1, b, (int) c));
            }
        }
    }

    static long init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start]; // 리프 노드일 경우

        int mid = (start + end) / 2; // 중간 지점 계산
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    static void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) return; // 현재 범위에 포함되지 않는 경우 종료

        tree[node] += diff; // 차이만큼 갱신

        if (start != end) { // 리프 노드가 아닌 경우 자식 노드도 갱신
            int mid = (start + end) / 2;
            update(start, mid, node * 2, index, diff);
            update(mid + 1, end, node * 2 + 1, index, diff);
        }
    }

    static long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0; // 구간이 벗어난 경우 0 반환
        if (left <= start && end <= right) return tree[node]; // 구간 내에 완전히 포함된 경우 해당 노드 값 반환

        int mid = (start + end) / 2; // 중간 지점 계산
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }
}
