import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int sum = 0;
        int[] array = new int[N];
        for(int i=0; i<N; i++){
            array[i] = sc.nextInt();
        }
        int V = sc.nextInt();
        for(int j=0; j<N; j++){
            if(array[j] == V){
                sum++;
            }
        }
        System.out.println(sum);
    }
}