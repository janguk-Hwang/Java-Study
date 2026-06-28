// 공항 문제
// k가 10^12이므로 배열로 풀면 메모리 초과
// 배열 대신 맵으로 부모 노드 저장
import java.util.*;

class Solution {
    static Map<Long, Long> map = new HashMap<>();
    static long[] answer;
    public long[] solution(long k, long[] room_number){
        answer = new long[room_number.length];
        for(int i=0; i<room_number.length; i++) answer[i] = func(room_number[i]);
        return answer;
    }
    
    public long func(long room){
        // 방이 비어있지 않으면
        if(map.containsKey(room)){
            // find
            long nextRoom = func(map.get(room));
            map.put(room, nextRoom);
            return nextRoom;
        }
        // union(원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방을 배정)
        map.put(room, room + 1);
        return room;
    }
}