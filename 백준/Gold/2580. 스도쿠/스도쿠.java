import java.io.*;

public class Main {
    static int[][] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        arr = new int[10][10];
        for(int i=0; i<9; i++){
            String[] in = br.readLine().split(" ");
            for(int j=0; j<9; j++){
                arr[i][j] = Integer.parseInt(in[j]);
            }
        }
        sudoku(0, 0);
    }

    //스도쿠 실행하기
    public static void sudoku(int row, int col) throws IOException {
        //행이 다 찼으면 다음 행으로
        if(col == 9){
            sudoku(row+1, 0);
        }
        //row가 9이면 스도쿠가 다 채워졌다는 것이므로 스도쿠 출력
        if(row == 9){
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    bw.write(arr[i][j] + " ");
                }
                bw.newLine();
                bw.flush();
            }
            bw.close();
            //시스템 종료
            System.exit(0);
        }

        if(arr[row][col] == 0){
            //1부터 9까지로 설정
            for(int i=1; i<=9; i++){
                if(possibility(row, col, i)){
                    arr[row][col] = i;
                    sudoku(row, col+1);
                }
            }
            //possibility가 다 거짓이라면
            arr[row][col] = 0;
            return;
        }

        //위 두 if문에 걸리지 않으면(행이 끝나거나 열이 끝나지 않은 경우)
        //열을 증가시키면서 스도쿠 진행
        sudoku(row, col+1);

    }
    //가능한 지 검사
    public static boolean possibility(int row, int col, int number){
        //가로 검사
        //해당 줄에 1부터 9까지의 수가 이미 존재하면 false 반환
        for(int i=0; i<9; i++){
            if(arr[row][i] == number){
                return false;
            }
        }
        //세로 검사
        for(int i=0; i<9; i++){
            if(arr[i][col] == number){
                return false;
            }
        }
        //3*3 검사
        //[0][0], [0][3], [0][6], [3][0], [3][3], [3][6], [6][0], [6][3], [6][6]
        int newRow = (row/3) * 3;
        int newCol = (col/3) * 3;

        for(int i=newRow; i<newRow+3; i++){
            for(int j=newCol; j<newCol+3; j++){
                if(arr[i][j] == number){
                    return false;
                }
            }
        }
        
        //가능한 경우 true 반환
        return true;
    }
}