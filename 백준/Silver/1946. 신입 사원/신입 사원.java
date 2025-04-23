import java.util.*;
import java.io.*;

// 완전한 상위호환의 지원자가 있으면 하위호환의 지원자는 뽑지 않는다.
public class Main {
    static int t, n;
    static int[][] grade;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            n = Integer.parseInt(br.readLine());
            grade = new int[n][2];  // 0: 서류심사 성적 1: 면접 성적
            for(int j=0; j<n; j++){
                st = new StringTokenizer(br.readLine(), " ");
                grade[j][0] = Integer.parseInt(st.nextToken());
                grade[j][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(grade, (o1, o2) -> o1[0] - o2[0]);  // 서류 성적을 기준으로 정렬
            int cnt = 1;
            int min = grade[0][1];  // 서류 1등의 면접 성적 등수 | 이거보다 높아야 살 수 있다.

            // 서류 성적을 기준으로 사람들을 줄 세운다. 서류 2등부터 꼴등까지 1등의 면접 성적보다 성적이 낮으면 서류, 면접에서 모두 낮은 하위호환이 된다.
            for(int k=1; k<n; k++){
                if(grade[k][1] < min){
                    cnt++;
                    min = grade[k][1];
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
