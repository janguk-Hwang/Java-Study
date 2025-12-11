import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer> menPreferTall = new ArrayList<>();
    static List<Integer> menPreferSmall = new ArrayList<>();
    static List<Integer> womenPreferTall = new ArrayList<>();
    static List<Integer> womenPreferSmall = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        Person[] men = new Person[n];
        Person[] women = new Person[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) men[i] = new Person(Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) women[i] = new Person(Integer.parseInt(st.nextToken()));
        for(Person man : men){
            if(man.isPreferTallThanMe) menPreferTall.add(man.height);
            else menPreferSmall.add(man.height);
        }
        for(Person woman : women){
            if(woman.isPreferTallThanMe) womenPreferTall.add(woman.height);
            else womenPreferSmall.add(woman.height);
        }
        Collections.sort(menPreferTall);
        Collections.sort(menPreferSmall);
        Collections.sort(womenPreferTall);
        Collections.sort(womenPreferSmall);
        int rst = 0;
        rst += match(menPreferSmall, womenPreferTall);
        rst += match(womenPreferSmall, menPreferTall);
        System.out.println(rst);
    }

    static int match(List<Integer> groupA, List<Integer> groupB){
        int i=0, j=0, cnt=0;
        while(i < groupA.size() && j < groupB.size()){
            if(groupA.get(i) > groupB.get(j)){
                cnt++;
                i++;
                j++;
            }
            else i++;
        }
        return cnt;
    }

    static class Person{
        int height;
        boolean isPreferTallThanMe;
        Person(int h){
            if(h > 0){
                this.height = h;
                this.isPreferTallThanMe = true;
            }
            else{
                this.height = -h;
                this.isPreferTallThanMe = false;
            }
        }
    }
}