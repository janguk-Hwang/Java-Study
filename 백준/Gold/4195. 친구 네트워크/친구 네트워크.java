import java.io.*;
import java.util.*;

// 이름으로 입력이 들어오기 때문에 해시맵으로 이름과 부모 노드 번호를 저장
public class Main {
    static int t, f;
    static int[] parent, size;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        t = Integer.parseInt(br.readLine());
        for(int k=0; k<t; k++){
            HashMap<String, Integer> hsMap = new HashMap<>();
            f = Integer.parseInt(br.readLine());
            parent = new int[2*f];
            size = new int[2*f];
            int index = 0;
            for(int i=0; i<f; i++){
                st = new StringTokenizer(br.readLine(), " ");
                String name1 = st.nextToken();
                String name2 = st.nextToken();
                if(!hsMap.containsKey(name1)){
                    hsMap.put(name1, index);
                    parent[index] = index;
                    size[index] = 1;
                    index++;
                }
                if(!hsMap.containsKey(name2)){
                    hsMap.put(name2, index);
                    parent[index] = index;
                    size[index] = 1;
                    index++;
                }
                if(find(hsMap.get(name1)) != find(hsMap.get(name2))){
                    union(hsMap.get(name1), hsMap.get(name2));
                }
                sb.append(size[find(hsMap.get(name1))]).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[b] = a;
        size[a] += size[b];
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}