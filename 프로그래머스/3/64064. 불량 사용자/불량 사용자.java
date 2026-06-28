import java.util.*;

class Solution {
    static HashSet<HashSet<String>> result = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        dfs(new HashSet<>(), 0, user_id, banned_id);
        return result.size();
    }
    
    // fr*d* -> frodo, fradi 중 어떤 사람인지 모르므로 모두 고려하기 위해 dfs
    public void dfs(HashSet<String> set, int depth, String[] user_id, String[] banned_id){
        // 종료 조건
        if(depth == banned_id.length){
            result.add(set);
            return;
        }
        for(int i=0; i<user_id.length; i++){
            if(set.contains(user_id[i])) continue;
            // 제재 명단에 있으면 set에 추가
            if(check(user_id[i], banned_id[depth])){
                set.add(user_id[i]);
                dfs(new HashSet<>(set), depth + 1, user_id, banned_id);
                set.remove(user_id[i]);
            }
        }
    }
    
    // 제재 명단에 포함되어 있는지 검사
    public boolean check(String userId, String bannedId){
        // 길이를 비교하여 최적화
        if(userId.length() != bannedId.length()){
            return false;
        }
        for(int i=0; i<userId.length(); i++){
            // *이 아닌 인덱스에 대해 같은지 확인
            if(bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)){
                return false;
            }
        }
        return true;
    }
}