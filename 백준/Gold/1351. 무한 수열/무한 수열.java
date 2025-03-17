import java.util.*;
import java.io.*;

public class Main {
    static long n, p, q;
    static HashMap<Long, Long> hashmap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());

        hashmap.put(0L, 1L); // A(0) = 1 정의
        System.out.println(getValue(n)); // n번째 값 출력
    }

    public static long getValue(long i) {
        if (hashmap.containsKey(i)) {
            return hashmap.get(i);
        }

        long value = getValue(i / p) + getValue(i / q);
        hashmap.put(i, value);
        return value;
    }
}
