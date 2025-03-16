import java.util.*;
import java.io.*;

public class Main {
    static int[] parent;
    static int g, p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());  // 게이트 개수
        p = Integer.parseInt(br.readLine());  // 비행기 개수

        parent = new int[g + 1]; // 게이트 번호는 1부터 시작
        for (int i = 1; i <= g; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        for (int i = 0; i < p; i++) {
            int plane = Integer.parseInt(br.readLine());
            int Gate = find(plane);

            if (Gate == 0) break;  // 더 이상 도킹 불가능하면 종료

            union(Gate, Gate - 1); // 현재 게이트를 사용하고, 이전 게이트와 연결
            cnt++;
        }

        System.out.println(cnt);
    }

    public static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[a] = b;
    }
}
