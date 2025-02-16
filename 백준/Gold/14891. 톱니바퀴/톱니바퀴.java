import java.util.*;

public class Main {
    static List<Deque<Integer>> gears = new ArrayList<>(); // 톱니바퀴 리스트 (덱 사용)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 4개의 톱니바퀴 입력받기
        for (int i = 0; i < 4; i++) {
            Deque<Integer> gear = new ArrayDeque<>();
            String line = sc.next();
            for (char c : line.toCharArray()) {
                gear.add(c - '0'); // 문자 '0' 또는 '1'을 숫자로 변환
            }
            gears.add(gear);
        }

        int K = sc.nextInt(); // 회전 횟수 입력

        while (K-- > 0) {
            int gearNum = sc.nextInt() - 1; // 회전할 톱니바퀴 번호
            int direction = sc.nextInt(); // 회전 방향 (1: 시계, -1: 반시계)

            rotateGears(gearNum, direction);
        }

        // 점수 계산
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (gears.get(i).peekFirst() == 1) { // 12시 방향이 S극이면 점수 추가
                score += (1 << i); // 2^i 점수 계산
            }
        }

        System.out.println(score);
    }

    // 톱니바퀴 회전 처리 함수
    public static void rotateGears(int gearNum, int direction) {
        int[] directions = new int[4]; // 각 톱니바퀴가 어떻게 회전하는지 저장하는 배열
        directions[gearNum] = direction;

        // 왼쪽 방향 확인
        for (int i = gearNum; i > 0; i--) {
            if (getRightPole(i - 1) != getLeftPole(i)) {
                directions[i - 1] = -directions[i]; // 반대 방향 회전
            } else {
                break; // 더 이상 영향을 받지 않으면 중단
            }
        }

        // 오른쪽 방향 확인
        for (int i = gearNum; i < 3; i++) {
            if (getRightPole(i) != getLeftPole(i + 1)) {
                directions[i + 1] = -directions[i]; // 반대 방향 회전
            } else {
                break; // 더 이상 영향을 받지 않으면 중단
            }
        }

        // 실제 회전 적용
        for (int i = 0; i < 4; i++) {
            if (directions[i] != 0) {
                rotate(gears.get(i), directions[i]);
            }
        }
    }

    // 오른쪽 극(2번째 인덱스) 가져오기
    public static int getRightPole(int gearIndex) {
        Iterator<Integer> it = gears.get(gearIndex).iterator();
        it.next(); it.next(); // 두 번째 요소 가져오기
        return it.next();
    }

    // 왼쪽 극(6번째 인덱스) 가져오기
    public static int getLeftPole(int gearIndex) {
        Iterator<Integer> it = gears.get(gearIndex).iterator();
        for (int i = 0; i < 6; i++) {
            it.next();
        }
        return it.next();
    }

    // 덱을 이용한 톱니바퀴 회전 함수
    public static void rotate(Deque<Integer> gear, int direction) {
        if (direction == 1) { // 시계 방향 회전
            gear.addFirst(gear.removeLast());
        } else { // 반시계 방향 회전
            gear.addLast(gear.removeFirst());
        }
    }
}