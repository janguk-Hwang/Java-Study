import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static ArrayList<Integer>[] map;
    static int[] point;
    static int minPoint = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //모든 친구에 대해서 가장 먼 친구와의 거리가 점수이다.
        //가장 모르는 친구가 친구의 친구의 친구이면 3점

        N = Integer.parseInt(br.readLine());
        map = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<Integer>();
        }

        point = new int[N + 1];

        //입력을 받아서 map에 저장
        while(true) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            if (x == -1 && y == -1) {
                break;
            }

            map[x].add(y);
            map[y].add(x);
        }

        //회원 수 만큼 bfs()를 반복하면서 각 회원의 점수를 point[]배열에 저장
        for (int i = 1; i <= N; i++) {
            int num = bfs(i);
            point[i] = num;
            minPoint = Math.min(minPoint, num);     //가장 낮은 점수를 minPoint에 저장
        }

        int cnt = 0;
        
        //가장 낮은 점수(회장이 될 점수)를 가지고 있는 회원의 수를 계산
        for (int i = 1; i <= N; i++) {
            if (point[i] == minPoint)
                cnt++;
        }

        System.out.println(minPoint + " " + cnt);
        for (int i = 1; i <= N; i++)
            if (point[i] == minPoint)
                System.out.print(i + " ");
    }

    static int bfs(int v) {
        boolean[] visited = new boolean[N + 1];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(v, 0));
        visited[v] = true;
        int cnt = 0;

        //q가 빌 때까지
        while (!q.isEmpty()) {
            Node vertex = q.poll();

            for (int i=0; i<map[vertex.v].size(); i++) {    //map[vertex.v].size는 vertex.v의 친구 수
                int nextV = map[vertex.v].get(i);

                //이미 방문했으면 다음으로
                if(visited[nextV])
                    continue;

                //방문처리
                visited[nextV] = true;
                //offer(친구 정점, 현재 정점까지의 거리+1);
                //방문처리가 되지 않았다는 것은 친구가 아니라는 것, 나 대신 친구를 큐에 넣으면서 대신 거리+1를 해서 추가
                q.offer(new Node(nextV, vertex.cnt + 1));
            }
            //마지막 친구와의 거리를 cnt에 저장
            cnt = vertex.cnt;
        }
        return cnt;
    }
}

class Node {
    int v, cnt;

    Node(int v, int cnt) {
        this.v = v;
        this.cnt = cnt;
    }
}