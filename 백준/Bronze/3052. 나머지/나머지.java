import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] input = new int[10];
        for(int i=0; i<10; i++){
            input[i] = sc.nextInt()%42;
        }
        HashSet<Integer> result = new HashSet<>();
        for(int j=0; j<10; j++){
            result.add(input[j]);
        }
        System.out.println(result.size());
    }
}