import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main{

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        //진입차수
        int[] edgeCount =new int[N + 1];
        //위상정렬에 사용할 그래프 2차원 리스트
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= N+1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            graph.get(Integer.parseInt(temp[0])).add(Integer.parseInt(temp[1]));
            edgeCount[Integer.parseInt(temp[1])]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < edgeCount.length; i++) {
            if (edgeCount[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int studentNo = q.poll();

            bw.write(String.valueOf(studentNo) + " ");

            List<Integer> list = graph.get(studentNo);

            for (int i = 0; i < list.size(); i++) {
                int temp = list.get(i);
                edgeCount[temp] -- ;
                if (edgeCount[temp] == 0) {
                    q.offer(temp);
                }
            }
        }
        bw.flush();
    }
}