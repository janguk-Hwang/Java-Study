import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 격자 크기
        m = Integer.parseInt(st.nextToken());   // 볼 개수
        k = Integer.parseInt(st.nextToken());   // 이동 횟수
        List<Ball> balls = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            balls.add(new Ball(r, c, m, s, d));
        }
        for(int i=0; i<k; i++){
            List<Ball>[][] temp = new ArrayList[n][n];
            for(int j=0; j<n; j++){
                for(int p=0; p<n; p++){
                    temp[j][p] = new ArrayList<>();
                }
            }
            // 볼 이동
            for(Ball b : balls){
                int nr = (b.r + dr[b.d] * (b.s % n) + n) % n;
                int nc = (b.c + dc[b.d] * (b.s % n) + n) % n;
                temp[nr][nc].add(new Ball(nr, nc, b.m, b.s, b.d));
            }
            // 합체, 분할
            List<Ball> nList = new ArrayList<>();
            for(int j=0; j<n; j++){
                for(int p=0; p<n; p++){
                    int size = temp[j][p].size();
                    if(size == 0) continue;
                    if(size == 1){
                        nList.add(temp[j][p].get(0));
                        continue;
                    }
                    int sumM = 0;
                    int sumS = 0;
                    boolean isAllEven = true;   // 짝수
                    boolean isAllOdd = true;    // 홀수
                    for(Ball b : temp[j][p]){
                        sumM += b.m;
                        sumS += b.s;
                        // 합쳐지는 볼의 방향이 모두 홀수이거나 짝수이면 방향은 0, 2, 4, 6 아니면 1, 3, 5, 7
                        if(b.d % 2 == 0) isAllOdd = false;
                        if(b.d % 2 != 0) isAllEven = false;
                    }
                    // 새 질량, 속력
                    int newM = sumM / 5;
                    if(newM == 0) continue;
                    int newS = sumS / size;
                    // 방향(모두 짝수거나 홀수이면 0, 2, 4, 6 그렇지 않으면 1, 3, 5, 7)
                    int[] t = (isAllOdd || isAllEven) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};
                    // 새로운 네 볼 생성
                    for(int d : t) nList.add(new Ball(j, p, newM, newS, d));
                }
            }
            balls = nList;
        }
        int rst = 0;
        for(Ball b : balls) rst += b.m;
        System.out.print(rst);
    }

    static class Ball{
        int r, c, m, s, d;
        Ball(int r, int c, int m, int s, int d){
            this.r = r; this.c = c; this.m = m; this.s = s; this.d = d;
        }
    }
}