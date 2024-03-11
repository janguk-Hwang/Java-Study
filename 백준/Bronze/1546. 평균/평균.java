import java.util.*;
import java.lang.Math;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double[] input = new double[N];
        double maximum = Double.MIN_VALUE;
        double sum = 0;
        double avg = 0;
        for(int i=0; i<N; i++){
            input[i] = sc.nextDouble();
            maximum = Math.max(maximum, input[i]);
        }
        for(int j=0; j<N; j++){
            input[j] = input[j]/maximum*100;
            sum += input[j];
        }
        avg = sum/N;
        System.out.println(avg);
    }
}