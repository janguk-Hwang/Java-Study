import java.io.*;
import java.util.*;

// x, y, z, k가 서로 같아도 된다.
// 가능한 두 수의 합을 모두 list에 저장
// arr에서 큰 값부터 해당 값에서 list에서의 값을 뺀 값이 arr이 존재하는지 확인(이분 탐색으로 빠르게 탐색)
// 없으면 다음 arr 배열 요소에 대해서 진행
// Collections.binarySearch는 찾는 값이 있으면 해당 값이 들어갈 인덱스를 반환하고 없으면 해당 값이 삽입될 자리의 인덱스를
// -(idx) - 1 형태로 반환하기 때문에 음수가 반환되면 찾는 값이 없다는 것을 의미한다. 있으면 양수가 반환된다.
public class Main {
    static int n;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                list.add(arr[i] + arr[j]);
            }
        }
        Arrays.sort(arr);   // 정렬해야 최대값부터 검사됨
        Collections.sort(list);
        for(int i=n-1; i>=0; i--){
            for(int j=0; j<i; j++){
                int target = arr[i] - arr[j];   // 찾아야 하는 두 수의 합
                if(Collections.binarySearch(list, target) >= 0){
                    System.out.print(arr[i]);
                    return;
                }
            }
        }
    }
}