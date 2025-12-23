import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String str;
    static int n;
    static Set<String> set = new HashSet<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        str = br.readLine();
        n = str.length();
        for(int i=0; i<n; i++){
            List<String> path = new ArrayList<>();
            // 인덱스가 i인 문자만 잘라서 path에 저장(1글자 문자열)
            path.add(str.substring(i, i+1));
            // 예제에서 볼 수 있듯이 str의 어떤 문자든지 시작문자열로 잡고 앞, 뒤로 문자를 붙여나가면서 str 자체를 만들 수 있다.
            dfs(i, i, path);
        }
        System.out.println(set.size());
    }

    // 같은 방법인지 구별하기 위해 각 경로마다 지나온 경로를 List<String> path에 담는다.
    static void dfs(int l, int r, List<String> path){
        if(l==0 && r==n-1){
            // 숫자를 적는 과정에서 나온 수가 순서대로 모두 같다면 같은 방법이다.
            set.add(String.join(" ", path));
            return;
        }

        // 현재 문자열에서 왼쪽 한 칸을 추가로 str에서 추출해서 path에 저장
        // l-1 ~ r까지 
        if(l > 0){
            path.add(str.substring(l-1, r+1));
            dfs(l-1, r, path);
            path.remove(path.size()-1);
        }

        // `` 오른쪽 ``
        // l부터 r+1까지
        if(r < n-1){
            path.add(str.substring(l, r+2));
            dfs(l, r+1, path);
            path.remove(path.size()-1);
        }
    }
}