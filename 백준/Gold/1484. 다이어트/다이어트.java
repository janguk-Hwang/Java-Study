import java.io.*;
import java.util.*;

// G는 자연수이다
// x^2 - y^2 = G, x는 이후의 몸무게 y는 이전의 몸무게
// (x-y) x (x+y) = G
// (x-y)와 (x+y)는 G의 약수이다.
// x와 y에 대해 정리하면 x = (a+b)/2, y = (b-a)/2
// 자연수인 경우만 취급해서 정답을 내면 된다. 문제에서 자연수가 아닌 경우는 제외하라고 명시되어 있음.
// 그러므로 x는 자연수인 경우만 생각한다. x가 자연수가 되려면 a+b는 짝수
// y는 1이상이면 된다. 몸무게가 0일 수는 없기 때문
public class Main {
    static int g;
    static List<Integer> result = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        g = Integer.parseInt(br.readLine());
        for(int a=1; a*a<=g; a++){
            // a가 g의 약수가 아니라면 건너뜀
            if(g % a != 0) continue;
            int b = g / a;
            // a+b가 짝수가 아니라면 건너뜀
            if((a+b) % 2 != 0) continue;
            int x = (a + b) / 2;
            int y = (b - a) / 2;
            // y가 1보다 작으면 건너뜀
            if(y < 1) continue;
            result.add(x);
        }
        if(result.isEmpty()) System.out.print(-1);
        else{
            result.sort(null);
            result.forEach(System.out::println);
        }
    }
}