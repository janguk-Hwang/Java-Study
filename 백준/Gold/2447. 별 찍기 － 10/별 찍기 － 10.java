import java.io.*;

public class Main {
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        arr = new char[size][size];

        // 배열을 공백(' ')으로 초기화
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = ' ';
            }
        }

        star(0, 0, size);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < size; i++) {
            bw.write(arr[i]);
            bw.write("\n");
        }
        bw.flush();
    }

    public static void star(int x, int y, int size) {
        if (size == 1) {
            arr[x][y] = '*';
            return;
        }

        int newSize = size / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue; // 중앙 공간 비우기
                star(x + i * newSize, y + j * newSize, newSize);
            }
        }
    }
}