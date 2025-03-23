import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        LinkedHashMap<String, Integer> hashmap = new LinkedHashMap<>();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), ".");
            String temp = st.nextToken();
            String extension = st.nextToken();
            hashmap.put(extension, hashmap.getOrDefault(extension, 0)+1);
        }

        // hashmap의 keySet을 리스트로 변환하여 keyset 변수에 저장
        List<String> keyset = new ArrayList<>(hashmap.keySet());
        Collections.sort(keyset);

        for (String key : keyset) {
            bw.write(key + " " + hashmap.get(key) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
