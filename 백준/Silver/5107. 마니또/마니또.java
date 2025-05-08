import java.io.*;
import java.util.*;

// 첫 번째 이름을 시작으로 꼬리물면서 방문체크
public class Main {
    static int n, testCaseNum, groupCnt;
    static HashMap<String, String> hashMap = new HashMap<>();
    static Set<String> visited = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        testCaseNum = 0;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            hashMap.clear();
            visited.clear();
            testCaseNum++;
            groupCnt = 0;
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine(), " ");
                hashMap.put(st.nextToken(), st.nextToken());
            }
            for(String name : hashMap.keySet()){
                if(!visited.contains(name)){
                    oneCycle(name);
                    groupCnt++;
                }
            }
            sb.append(testCaseNum).append(" ").append(groupCnt).append("\n");
        }
        System.out.println(sb);
    }
    static void oneCycle(String name){
        while(!visited.contains(name)){
            visited.add(name);
            name = hashMap.get(name);
        }
    }
}
