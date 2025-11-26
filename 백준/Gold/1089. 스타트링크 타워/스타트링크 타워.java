import java.io.*;
import java.util.*;

// 꺼져있는 전구의 일부가 고장날 수 있으므로
// 입력에서 꺼져있는 전구를 켰을 때 만들어 질 수 있는 숫자를 파악해야 한다.
public class Main {
    static int n;
    static char[][] matrix;
    static char[][][] digit = new char[10][5][3];
    static List<Integer>[] candidates;
    static String[] digit0To10 = {
            "###\n#.#\n#.#\n#.#\n###",  // 0
            "..#\n..#\n..#\n..#\n..#",  // 1
            "###\n..#\n###\n#..\n###",  // 2
            "###\n..#\n###\n..#\n###",  // 3
            "#.#\n#.#\n###\n..#\n..#",  // 4
            "###\n#..\n###\n..#\n###",  // 5
            "###\n#..\n###\n#.#\n###",  // 6
            "###\n..#\n..#\n..#\n..#",  // 7
            "###\n#.#\n###\n#.#\n###",  // 8
            "###\n#.#\n###\n..#\n###"   // 9
    };
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        // 0부터 9까지의 digit 모습
        for(int i=0; i<10; i++){
            String[] lines = digit0To10[i].split("\n");
            for(int r=0; r<5; r++){
                for(int c=0; c<3; c++){
                    digit[i][r][c] = lines[r].charAt(c);
                }
            }
        }
        n = Integer.parseInt(br.readLine());
        matrix = new char[5][4 * n - 1];
        for(int i=0; i<5; i++) matrix[i] = br.readLine().toCharArray();
        candidates = new ArrayList[n];
        for(int i=0; i<n; i++) candidates[i] = new ArrayList<>();
        // n개의 숫자들이 각각 0 ~ 9 숫자로 가능한지 검사하고 candidates 리스트에 가능한 숫자들 저장
        for(int i=0; i<n; i++){
            for(int j=0; j<=9; j++){
                // 꺼져있는 전구의 일부가 고장났을 때 가능할 수 있으면 후보군에 추가
                if(isPossible(i, j)) candidates[i].add(j);
            }
        }
        for(int i=0; i<n; i++){
            if(candidates[i].isEmpty()){
                System.out.println(-1);
                return;
            }
        }
        double rst = 0.0;
        for(int i=0; i<n; i++){
            double placeValue = Math.pow(10, n-i-1);
            double sum = 0.0;
            for(int num : candidates[i]) sum += num;
            double avg = sum / candidates[i].size();
            rst += avg * placeValue;
        }
        System.out.print(rst);
    }

    // (현재 숫자, 비교할 숫자)
    static boolean isPossible(int idx, int d){
        int colStart = idx * 4;
        for(int r=0; r<5; r++){
            for(int c=0; c<3; c++){
                char cur = matrix[r][colStart + c];
                char need = digit[d][r][c];
                // 켜져있는 칸이 비교할 숫자에서는 켜져있으면 안되는 칸이면 불가
                if(cur == '#' && need == '.') return false;
            }
        }
        return true;
    }
}