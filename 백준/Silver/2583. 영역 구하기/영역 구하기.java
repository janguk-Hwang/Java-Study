import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int[][] paper;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int M;
    static int N;
    static int count;   //영역의 넓이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);
        paper = new int[M][N];

        //직사각형 그린 영역은 1로
        for(int i=0; i<K; i++){
            String[] line = br.readLine().split(" ");
            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int x2 = Integer.parseInt(line[2]);
            int y2 = Integer.parseInt(line[3]);
            //어차피 뒤집으면 똑같음
            for(int j=y1; j<y2; j++){
                for(int p=x1; p<x2; p++){
                    paper[j][p] = 1;
                }
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();

        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(paper[i][j] == 0){
                    count = 0;  //count 0으로 초기화
                    dfs(i, j);
                    //count는 static임
                    arr.add(count);
                }
            }
        }

        //리스트는 Collections.sort()
        Collections.sort(arr);

        System.out.println(arr.size());
        for(int i=0; i<arr.size(); i++){
            System.out.println(arr.get(i));
        }
    }

    public static void dfs(int r, int c) {
        //지금 자리를 1로 만들고 진행
        paper[r][c] = 1;
        //현재 자리 카운트
        count++;
        
        for(int p=0; p<4; p++){
            int nr = r + dy[p];
            int nc = c + dx[p];

            if(nr >= 0 && nr < M && nc >= 0 && nc < N){
                if(paper[nr][nc] == 0){
                    //4방 탐색하면서 1로 채워나감
                    paper[nr][nc] = 1;
                    dfs(nr, nc);
                }
            }
        }
    }
}