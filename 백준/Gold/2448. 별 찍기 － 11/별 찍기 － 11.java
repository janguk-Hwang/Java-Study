import java.io.*;
import java.util.*;

// 재귀 함수, 위치와 크기를 전달받는 함수로 만든다.
public class Main {
    static char[][] matrix;
    static int n;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new char[n][2 * n - 1];
        for(int i=0; i<n; i++) Arrays.fill(matrix[i], ' ');
        func(n - 1, 0, n);
        for(char[] row : matrix) sb.append(row).append("\n");
        System.out.print(sb);
    }

    public static void func(int x, int y, int size){    // size: 삼각형의 높이
        // 기저 사례(삼각형의 최소 단위 형태)
        if(size == 3){
            for(int i=0; i<3; i++){
                for(int j=-i; j<=i; j++){
                    if(i == 0 || Math.abs(j) == i || i == 2){
                        matrix[y+i][x+j] = '*';
                    }
                }
            }
            return;
        }
        // 나눠지는 3개의 삼각형을 위치와 사이즈를 조정해서 재귀 호출
        func(x, y, size / 2);
        func(x - size / 2, y + size / 2, size / 2);
        func(x + size / 2, y + size / 2, size / 2);
    }
}