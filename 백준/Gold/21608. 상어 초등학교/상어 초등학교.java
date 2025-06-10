import java.io.*;
import java.util.*;

public class Main{
    static int n;
    static int[][] like;
    static int[][] matrix;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<int[]> sort = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        like = new int[n * n + 1][4];
        matrix = new int[n][n];
        for(int i=0; i<n*n; i++){
            st = new StringTokenizer(br.readLine());
            int studentNum = Integer.parseInt(st.nextToken());
            for(int j=0; j<4; j++) like[studentNum][j] = Integer.parseInt(st.nextToken());
            sort.clear();
            for(int r=0; r<n; r++){
                for(int c=0; c<n; c++){
                    if(matrix[r][c] != 0) continue;
                    int emptyNum = 0;
                    int likeNum = 0;
                    for(int d=0; d<4; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                        if(matrix[nr][nc] == 0){
                            emptyNum++;
                            continue;
                        }
                        for(int p=0; p<4; p++){
                            if(matrix[nr][nc] == like[studentNum][p]){
                                likeNum++;
                                break;
                            }
                        }
                    }
                    // 정렬 기준 4가지 앞에서부터 우선순위
                    // 좋아하는 학생 수, 비어있는 칸의 수, 행의 번호, 열의 번호
                    sort.add(new int[]{likeNum, emptyNum, r, c});
                }
            }

            sort.sort((a, b) -> {
               if(a[0] != b[0]) return b[0] - a[0];
               if(a[1] != b[1]) return b[1] - a[1];
               if(a[2] != b[2]) return a[2] - b[2];
               return a[3] - b[3];
            });

            int[] temp = sort.get(0);
            matrix[temp[2]][temp[3]] = studentNum;
        }
        int total = 0;
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                int studentNum = matrix[r][c];
                int likeNum = 0;
                for(int d=0; d<4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    for(int p=0; p<4; p++){
                        if(matrix[nr][nc] == like[studentNum][p]){
                            likeNum++;
                            break;
                        }
                    }
                }
                switch(likeNum) {
                    case 1 : total += -9;
                    case 2 : total += -90;
                    case 3 : total += -900;
                    case 4 : total += 1000;
                    default : break;
                }
            }
        }
        System.out.print(total);
    }
}