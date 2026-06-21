// ShiftRow, Rotate
// ShiftRow 연산의 횟수, Rotate 연산의 횟수를 모듈러 연산으로 간소화 불가능
// 규칙
// Rotate 연산에서 내부의 값은 필요가 없음 -> 테두리를 각각 나눠서 리스트(Deque)로 만들어서 양 끝에서 삽입, 삭제하여 연산 횟수 감소
// ShiftRow 연산에서 Rotate에서 사용한 Deque에 추가로 내부 값들도 Deque로 저장하여 순환(이동)
// 양 옆의 세로 Deque, 내부 가로 Deque<Deque>

import java.util.*;

class Solution {
    static Deque<Integer> left;
    static Deque<Integer> right;
    static Deque<Deque<Integer>> inside;
    static Deque<Integer> slice;
    public int[][] solution(int[][] rc, String[] operations) {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
        inside = new ArrayDeque<>();
        for(int i=0; i<rc.length; i++){
            left.addLast(rc[i][0]);
            slice = new ArrayDeque<>();
            for(int j=1; j<rc[0].length-1; j++) slice.addLast(rc[i][j]);
            inside.addLast(slice);
            right.addLast(rc[i][rc[0].length - 1]);
        }
        for(String s : operations){
            // ShiftRow
            if(s.equals("ShiftRow")){
                left.addFirst(left.pollLast());
                right.addFirst(right.pollLast());
                inside.addFirst(inside.pollLast());
            }
            // Rotate
            else{
                inside.peekFirst().addFirst(left.pollFirst());
                right.addFirst(inside.peekFirst().pollLast());
                inside.peekLast().addLast(right.pollLast());
                left.addLast(inside.peekLast().pollFirst());
            }
        }
        int[][] rst = new int[rc.length][rc[0].length];
        for(int i=0; i<rc.length; i++){
            rst[i][0] = left.pollFirst();
            Deque<Integer> temp = inside.pollFirst();
            for(int j=1; j<rc[0].length-1; j++){
                rst[i][j] = temp.pollFirst();
            }
            rst[i][rc[0].length-1] = right.pollFirst();
        }
        return rst;
    }
}














