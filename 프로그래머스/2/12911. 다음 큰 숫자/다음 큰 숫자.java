public int solution(int n) {
    int countOnes(int num) {
        int count = 0;
        while (num > 0) {
            if (num % 2 == 1) { // 끝 비트가 1이면
                count++;
            }
            num /= 2; // 오른쪽으로 한 비트 이동 (나누기 2)
        }
        return count;
    }

    int targetCount = countOnes(n);
    int nextNum = n + 1;

    while (countOnes(nextNum) != targetCount) {
        nextNum++;
    }

    return nextNum;
}