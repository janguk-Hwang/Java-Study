import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int target;
    static int[][] snail;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dr = {1, 0, -1, 0}; // 하 우 상 좌 방향 세팅
    static int[] dc = {0, 1, 0, -1}; // 0  1  2  3  방향이라 가정
    static int r = 0, c = 0, d = 0; // 초기 달팽이의 좌표는 0, 하방향부터 시작
    static int targetR = 0, targetC = 0; // 구하고자 하는 숫자의 좌표값
    static StringBuilder sb = new StringBuilder();

    static void print(int[][] graph) {
        for (int r = 0; r < graph.length; r++) {
            for (int c = 0; c < graph.length; c++) {
                if (target == snail[r][c]) {
                    targetR = r + 1;
                    targetC = c + 1;
                }
                sb.append(snail[r][c] + " ");
            }
            sb.append("\n");
        }
        sb.append(targetR + " " + targetC);
        System.out.println(sb.toString());
    }
    
    public static void main(String[] args)throws IOException {
    	N = Integer.parseInt(br.readLine());
    	target = Integer.parseInt(br.readLine());
    	
    	snail = new int[N][N]; //초기화
    	
    	for(int i=N*N; i>0; i--) {
    		snail[r][c] = i;		//일단 첫 번째 칸에 숫자를 넣음
    		
    		int nr = r + dr[d];		//다음 칸에 대한 좌표를 구함
    		int nc = c + dc[d];		//범위를 벗어나도 call 하지 않아 오류가 발생하지는 않음
    		
    		if(nr<0 || nr>=N || nc<0 || nc>=N || snail[nr][nc] != 0) {	//만약 nr와 nc가 범위를 벗어난다면 또는 이미 값이 있는 칸이라면 방향을 변경해야 한다는 것이므로
    			d = (d+1)%4;		//방향을 변경
    			nr = r + dr[d];		//새로운 칸에 대한 행과 열을 새로 할당
    			nc = c + dc[d];
    		}
    		r = nr;			//snail[r][c]에 들어갈 r,c 값을 할당
    		c = nc;			//이제 다시 for문을 반복하며 snail[r][c]에 해당하는 칸에 i값을 할당하면서 모든 칸을 채워나감
    	}
    	print(snail);
    }
}