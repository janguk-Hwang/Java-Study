import java.io.*;
import java.util.*;

// 1번 선수를 4번 타자로 미리 결정했다.
public class Main {
    static int n;
    static int[][] arr;
    static boolean[] visited;   // 선수를 이미 배치했는지 여부
    static int[] order;     // 타순
    static int maxScore = 0;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][9];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[9];
        order = new int[9];
        order[3] = 0;   // 1번 선수를 4번 타자로 미리 결정
        visited[0] = true;
        backtracking(0);
        System.out.println(maxScore);
    }

    public static void backtracking(int depth){
        // 종료 조건
        if(depth == 9){
            maxScore = Math.max(maxScore, simulate());
            return;
        }
        // 4번 타자는 이미 있으므로 다음 깊이로 건너뜀
        if(depth == 3){
            backtracking(depth + 1);
            return;     // 없으면 아래 for문이 실행되어 4번 타자에 다른 타자가 덮어씌워짐
        }
        for(int idx=1; idx<9; idx++){  // 8명의 순서 결정
            if(!visited[idx]){
                visited[idx] = true;
                order[depth] = idx;
                backtracking(depth + 1);
                visited[idx] = false;
            }
        }
    }

    public static int simulate(){
        int score = 0;
        int playerIdx = 0;
        for(int inning=0; inning<n; inning++){  // 0 ~ n-1이닝
            int outCnt = 0;
            boolean[] base = new boolean[3];    // 0 = 1루, 1 =2 루, 2 = 3루
            // 3아웃 될 때까지 이닝 진행
            while(outCnt < 3){
                int player = order[playerIdx];  // 현재 타석에 들어선 선수 번호
                int play = arr[inning][player]; // player 선수가 이번 이닝에서 얻는 결과(안타, 2루타, 3루타, 홈런, 아웃)
                // 아웃
                if(play == 0){
                    outCnt++;
                }
                // 안타
                if(play == 1){
                    // 3루에 주자있으면 득점, 3루 비게 됨
                    if(base[2]){
                        score++;
                        base[2] = false;
                    }
                    // 2루 주자는 3루로
                    if(base[1]){
                        base[2] = true;
                        base[1] = false;
                    }
                    if(base[0]){
                        base[1] = true;
                        base[0] = false;
                    }
                    base[0] = true;
                }
                // 2루타
                if(play == 2){
                    if(base[2]){
                        score++;
                        base[2] = false;
                    }
                    if(base[1]){
                        score++;
                        base[1] = false;
                    }
                    if(base[0]){
                        base[2] = true;
                        base[0] = false;
                    }
                    base[1] = true;
                }
                // 3루타
                if(play == 3){
                    if(base[2]){
                        score++;
                        base[2] = false;
                    }
                    if(base[1]){
                        score++;
                        base[1] = false;
                    }
                    if(base[0]){
                        score++;
                        base[0] = false;
                    }
                    base[2] = true;
                }
                // 홈런
                if(play == 4){
                    if(base[2]){
                        score++;
                        base[2] = false;
                    }
                    if(base[1]){
                        score++;
                        base[1] = false;
                    }
                    if(base[0]){
                        score++;
                        base[0] = false;
                    }
                    score++;
                }
                playerIdx++;
                if(playerIdx == 9) playerIdx = 0;   // 9번 타자 -> 1번 타자
            }
        }
        return score;
    }
}
