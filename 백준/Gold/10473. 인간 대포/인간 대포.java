import java.io.*;
import java.util.*;

public class Main {
    //정점 클래스
    static class Vertex{
        float x, y;
        int idx;

        //생성자
        public Vertex(float x, float y, int idx){
            this.x=x;
            this.y=y;
            this.idx=idx;
        }
    }

    //간선 클래스
    static class Edge{
        int target;
        float dist, sec;

        //생성자
        public Edge(int tg, float d, float sec){
            target = tg;
            dist = d;
            this.sec = sec;
        }
    }

    static final int INF = 1000000000;
    static List<Edge>[] list = new ArrayList[102];
    static final float[] time = new float[102];

    //정점 객체를 매개변수로 받아서 두 정점의 거리를 구함
    static float getDist(Vertex a, Vertex b){
        return (float) Math.sqrt( Math.pow(a.y - b.y, 2) + Math.pow(a.x - b.x, 2) );
    }

    //다익스트라 == 시작 정점에서 모든 정점까지의 최단 거리를 구하는 알고리즘
    //우선순위 큐를 통한 다익스트라 구현
    //1. 출발 노드의 거리(시간)을 갱신하고 우선순위 큐에 넣는다.
    //2. 우선순위 큐에서 맨 앞 노드를 꺼내 인접 노드를 조사한다.
    //3. 인접 노드 중에서 거리값이 최소로 갱신되는 노드만 우선순위 큐에 넣는다.(시간 or 거리 등 필요한 것으로 정렬되도록)
    //4. 2,3을 큐가 비워질 때까지 반복
    static void Dijkstra(int n){

        //sec가 작은 노드가 우선순위 큐의 앞에 온다.
        Queue<Edge> Q = new PriorityQueue<>((a, b) -> (int) (a.sec - b.sec));
        //1.1 출발 노드의 거리(시간)를 갱신하고
        time[0] = 0;
        //1.2 우선순위 큐에 넣는다.
        Q.add(new Edge(0, 0, 0));

        //큐가 비워질 때까지
        while(!Q.isEmpty()){
            //2.1 우선순위 큐에서 맨 앞 노드를 꺼내
            Edge e = Q.poll();
            //2.2 인접 노드를 조사한다.
            int cur = e.target;
            float sec = e.sec;
            //3.1 인접 노드 중에서 거리값이 최소로 갱신되는 노드만
            if(time[cur] < sec) continue;

            //이동하는 3가지 방법에 대해서 거리값이 최소로 갱신되는 경우 우선순위 큐에 노드를 넣는다.
            //main문에서 list.add를 정점 순서대로 실행했기 때문에 정점 번호 순으로 순회
            for (int i = 0; i < list[cur].size(); i++){
                Edge ne = list[cur].get(i); //list[cur]에서 i번째 Edge 객체 가져옴
                int next = ne.target;   //next에 타겟 노드 번호 저장
                float dist = ne.dist;   //dist에 타겟 노드와의 거리 저장

                //대포와 다음 도착점의 거리가 50보다 작으면 대포를 타고 도착점까지 되돌아간다.
                //대포에서 도착점까지의 거리 vs 50m - 대포에서 도착점까지의 거리

                if(cur > 0 && cur <= n){
                    //거리가 50이상이면 대포를 타는 시간인 2초와
                    //50m를 대포로 이동했으니 dist-50을 5m/s로 나눈 값이 nSec(누적 시간)
                    //(1)
                    if(dist >= 50) {
                        float nSec = sec + 2 + (dist - 50) / 5.0f;

                        //3.2 우선순위 큐에 넣는다
                        if (time[next] > nSec) {
                            time[next] = nSec;
                            Q.add(new Edge(next, 0, nSec));
                        }
                    }
                    //(2)
                    else{
                        //대포로 50m를 날라가고 도착점까지 걸어간다.
                        float nSec = sec + 2 + (50 - dist) / 5.0f;

                        //3.2 우선순위 큐에 넣는다
                        if (time[next] > nSec) {
                            time[next] = nSec;
                            Q.add(new Edge(next, 0, nSec));
                        }
                    }
                }
                //(3)
                //오직 걸어간다
                float nSec = sec + (dist / 5.0f);
                if(time[next] > nSec){
                    time[next] = nSec;
                    Q.add(new Edge(next, 0, nSec));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //당신은 5m/s의 속도로 달린다. 모든 대포는 당신을 당신이 원하는 임의의 방향으로 50m 날려줄 수 있다.
        //대포에 올라타고 발사되고 착륙하기까지는 정확히 2초가 걸린다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //Vertex 타입의 배열의 형태로 102만큼의 크기 배열 생성(대포의 개수는 최대 100개라고 문제에 있음), 100+시작 좌표+목표 좌표 = 102
        Vertex[] vertices = new Vertex[102];
        //출발점, 도착점
        for(int i=0; i<2; i++){
            st = new StringTokenizer(br.readLine());
            //처음에는 x와 y에 현재 위치한 좌표를 저장
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            //vertices[0]에는 현재 위치 x,y,0
            //vertices[1]에는 목표 위치 x,y,1
            vertices[i] = new Vertex(x, y, i);
        }

        //대포의 갯수
        int n = Integer.parseInt(br.readLine());

        //vertices[1]에는 목표 위치 x,y,1가 있기 때문에
        //vertices[대포의 갯수+1]에 new Vertex(vertices[1].x, vertices[1].y, n+1)을 통해 마지막 vertices 배열에 목표 위치를 저장
        vertices[n+1] = new Vertex(vertices[1].x, vertices[1].y, n+1);
        //대포의 위치와 인덱스 설정(출발점 인덱스: 0, 도착점 인덱스: n+1(마지막 인덱스))
        //대포의 갯수만큼 반복
        for(int i=1; i<=n; i++){    //vertices[0]은 시작 위치가 있으므로 i=1부터 시작
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            //vertices 배열에 입력받은 대포의 위치와 인덱스 저장
            vertices[i] = new Vertex(x, y, i);
        }

        //인접리스트 구현
        for(int i=0; i<=n+1; i++){
            time[i] = INF;
            //인접리스트 생성
            list[i] = new ArrayList<>();

            for(int j=0; j<=n+1; j++){
                //자기자신과 같으면 continue
                if(i == j) continue;
                float dist = getDist(vertices[i], vertices[j]);
                //public Edge(int tg, float d, float sec){} -> 타겟, 거리, 시간
                //다른 모든 정점과의 거리 정보를 인접리스트에 저장
                //초기화 단계에서는 sec를 0으로 설정,
                list[i].add(new Edge(j, dist, 0));
            }
        }

        Dijkstra(n);

        //출발점(0)부터 도착점(n+1)까지의 시간 출력
        System.out.println(time[n+1]);
    }
}