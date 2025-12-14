import java.io.*;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args) throws IOException {
        // 간선 입력 받기, 그래프에 저장
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        String[] knowinput = br.readLine().split(" ");
        int knownum = Integer.parseInt(knowinput[0]);
        int[] arr = new int[knownum];
        int[] parent = new int[N+1];

        // 사람들의 번호는 1부터 N까지의 수로 이루어짐
        for(int i=1; i <= N; i++){
            parent[i] = i;
        }

        // arr에 진실을 아는 사람 저장
        for(int i=0; i<knownum; i++){
            arr[i] = Integer.parseInt(knowinput[i+1]);
        }

        // 파티 정보를 저장할 배열
        int[][] parties = new int[M][];

        for (int i=0; i<M; i++) {   //각 입력줄마다
            String[] input = br.readLine().split(" ");
            int partySize = Integer.parseInt(input[0]);
            parties[i] = new int[partySize];
            for (int j = 0; j < partySize; j++) {
                //parties[i][j]에 사람들의 번호 싹 다 저장
                parties[i][j] = Integer.parseInt(input[j+1]);
            }
            //parties 배열에서 열을 1씩 증가하면서 파티 내의 사람들끼리 서로 union 해간다
            for (int j = 0; j < partySize - 1; j++) {
                union(parent, parties[i][j], parties[i][j+1]);
            }
        }

        // 진실을 아는 사람들의 루트를 확인하여 진실 그룹 찾기
        boolean[] truthGroup = new boolean[N+1];
        for (int i = 0; i < knownum; i++) {
            int root = find(parent, arr[i]);
            truthGroup[root] = true; // 진실을 아는 사람들의 루트는 truthGroup에 표시
        }

        // 과장된 이야기를 할 수 있는 파티를 카운트
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            boolean canLie = true;
            for (int person : parties[i]) {
                if (truthGroup[find(parent, person)]) {
                    canLie = false;
                    break; // 진실을 아는 사람이 있으면 과장된 이야기를 할 수 없음
                }
            }
            if (canLie) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    // Union
    public static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(x < y) parent[y] = x;
        else parent[x] = y;
    }

    // Find
    public static int find(int[] parent, int x) {
        if(parent[x] == x) return x;
        else return parent[x] = find(parent, parent[x]); // 경로 압축 적용
    }
}
