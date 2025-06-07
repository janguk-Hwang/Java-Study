import java.io.*;
import java.util.*;

// x, y좌표 차이의 합이 짝수이면 가능
public class Main {
    static int t;
    static int[][] matrix;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int startCol = st.nextToken().charAt(0) - 'A' + 1;
            int startRow = Integer.parseInt(st.nextToken());
            int endCol = st.nextToken().charAt(0) - 'A' + 1;
            int endRow = Integer.parseInt(st.nextToken());

            // 다른 색
            if((Math.abs(endCol - startCol) + Math.abs(endRow - startRow)) % 2 != 0){
                sb.append("Impossible").append("\n");
                continue;
            }
            // 동일한 자리
            if(startCol == endCol && startRow == endRow){
                sb.append("0 ").append((char)(startCol + 'A' - 1)).append(" ").append(startRow).append("\n");
                continue;
            }
            // 한 번에 이동
            if(Math.abs(startCol - endCol) == Math.abs(startRow - endRow)){
                sb.append("1 ").append((char)(startCol + 'A' - 1)).append(" ").append(startRow).append(" ")
                .append((char)(endCol + 'A' - 1)).append(" ").append(endRow).append("\n");
                continue;
            }

            // 2번에 이동
            outer:
            for(int i=1; i<=8; i++){
                for(int j=1; j<=8; j++){
                    if(Math.abs(startCol - i) == Math.abs(startRow - j) && Math.abs(endCol - i) == Math.abs(endRow - j)) {
                        sb.append("2 ")
                                .append((char)(startCol + 'A' - 1)).append(" ").append(startRow).append(" ")
                                .append((char)(i + 'A' - 1)).append(" ").append(j).append(" ")
                                .append((char)(endCol + 'A' - 1)).append(" ").append(endRow).append("\n");
                        break outer;
                    }
                }
            }
        }
        System.out.print(sb);
    }
}