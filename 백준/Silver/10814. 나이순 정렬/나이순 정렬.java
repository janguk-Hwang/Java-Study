import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Member> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            pq.offer(new Member(age, name, i));
        }
        while(!pq.isEmpty()){
            Member m = pq.poll();
            sb.append(m.age).append(" ").append(m.name).append("\n");
        }
        System.out.print(sb);
    }

    public static class Member implements Comparable<Member>{
        int age; String name; int order;
        public Member(int age, String name, int order){
            this.age = age; this.name = name; this.order = order;
        }
        @Override
        public int compareTo(Member o){
            if(this.age == o.age) return Integer.compare(this.order, o.order);
            return Integer.compare(this.age, o.age);
        }
    }
}