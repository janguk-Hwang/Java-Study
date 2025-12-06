import java.io.*;
import java.util.*;

// 전위 순회의 첫 번째 노드가 루트 노드.
// 중위 순회에서 전위 순회에서 찾은 루트 노드를 기준으로 좌, 우가 서브 트리를 의미.
// 좌, 우의 서브트리를 탐색하면서 후위 순회 결과 구성.
public class Main {
    static int t, n, preIdx;
    static int[] preOrder, inOrder, idx;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            preOrder = new int[n];
            inOrder = new int[n];
            idx = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) preOrder[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                inOrder[i] = Integer.parseInt(st.nextToken());
                // 5 6 8 4 3 1 2 7 (중위 순서)
                idx[inOrder[i]] = i;
            }
            preIdx = 0;
            postOrder(0, n - 1);
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }
    static void postOrder(int left, int right){
        if(left > right) return;
        // 루트 노드는 전위 순회에서
        int root = preOrder[preIdx++];
        // 서브 트리의 루트
        int mid = idx[root];
        // 서브 트리의 좌, 우 서브 트리를 순회
        // (0, 0), (2, 3)
        postOrder(left, mid - 1);
        postOrder(mid + 1, right);
        sb.append(root).append(" ");
    }
}