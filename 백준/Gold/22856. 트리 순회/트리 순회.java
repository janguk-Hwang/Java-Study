import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static ArrayList<Integer>[] A;
    static boolean[] V;
    static int answer = 0;
    static boolean flag = false;
    static int N;
    static int lastInOrder = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()) + 1;

        V = new boolean[N];
        A = new ArrayList[N];
        for (int i = 1; i < N; i++) {
            A[i] = new ArrayList<>();
        }
        V[0] = true;

        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int now = Integer.parseInt(st.nextToken());   //현재 노드 번호
            int a = Integer.parseInt(st.nextToken());     //왼쪽 자식 노드 번호
            int b = Integer.parseInt(st.nextToken());     //오른쪽 자식 노드 번호

            A[now].add(a == -1 ? 0 : a);  //왼쪽 자식이 없으면 0, 있으면 자식 노드 번호 추가
            A[now].add(b == -1 ? 0 : b);  //오른쪽 자식이 없으면 0, 있으면 자식 노드 번호 추가
        }

        InOrder(1);  //중위 순회 수행
        sameInOrder(1);  //유사 중위 순회 수행
        System.out.println(answer);
    }

    // 유사 중위 순회를 수행하는 함수
    static public void sameInOrder(int now) {
        V[now] = true;  //현재 노드 방문 처리

        //왼쪽 자식이 존재하고 아직 방문하지 않았다면 방문하고 재귀 호출
        if (A[now].get(0) != 0 && !V[A[now].get(0)]) {
            answer++;  //방문 횟수 증가
            sameInOrder(A[now].get(0));
        }

        //오른쪽 자식이 존재하고 아직 방문하지 않았다면 방문하고 재귀 호출
        if (A[now].get(1) != 0 && !V[A[now].get(1)]) {
            answer++;  //방문 횟수 증가
            sameInOrder(A[now].get(1));
        }

        //현재 노드가 마지막 중위 순회의 노드라면 flag를 true로 설정
        if (lastInOrder == now) flag = true;

        //flag가 true가 되면 즉시 종료
        if (flag) return;

        //아직 마지막 노드에 도달하지 않았다면 방문 횟수 증가
        answer++;
    }

    //중위 순회 함수
    static public void InOrder(int now) {
        // 왼쪽 자식이 있으면 먼저 방문 (재귀 호출)
        if (A[now].get(0) != 0) {
            InOrder(A[now].get(0));
        }

        //중위 순회에서 마지막 방문 노드 번호를 저장
        lastInOrder = now;

        //오른쪽 자식이 있으면 방문(재귀)
        if (A[now].get(1) != 0) {
            InOrder(A[now].get(1));
        }
    }
}