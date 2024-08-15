import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        //N보다 작은 소수를 모두 구하고 소수끼리의 합이 N이 되면(이중 for문) cnt++

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            List<Boolean> primeList = new ArrayList<>(N+1);
            primeList.add(false);      //0
            primeList.add(false);      //1

            for(int j=2; j<N+1; j++){
                primeList.add(j, true);
            }

            for(int k=2; k<=Math.sqrt(N); k++){
                if(primeList.get(k)){               //primeList가 true(2 이상)이면
                    for(int p=k*k; p<=N; p+=k){
                        primeList.set(p, false);    //소수가 아닌 수들은 false로
                    }
                }
            }

            List<Integer> temp = new ArrayList<>();
            for(int j=0; j<primeList.size(); j++){
                if(primeList.get(j)){
                    temp.add(j);                    //리스트에 소수들을 넣음
                }
            }

            int[] primeArray = new int[temp.size()];
            for(int j=0; j<temp.size(); j++){
                primeArray[j] = temp.get(j);        //리스트 -> 배열, primeArray에는 N보다 작은 소수들이 저장되어 있음
            }

            Set<Integer> set = new HashSet<>();
            int count = 0;

            for(int k=2; k<=N/2;k++){
                if(primeList.get(k)&&primeList.get(N-k)){
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}