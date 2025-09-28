import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long atk;
    static int[] enemyOrBuff;
    static long[] a, h;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        atk = Long.parseLong(st.nextToken());
        enemyOrBuff = new int[n];
        a = new long[n]; h = new long[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            enemyOrBuff[i] = Integer.parseInt(st.nextToken());
            a[i] = Long.parseLong(st.nextToken());
            h[i] = Long.parseLong(st.nextToken());
        }
        long minNeedHp = 0;
        long sumDamage = 0;
        long tempAtk = atk;
        for(int i=0; i<n; i++){
            // 몬스터 있는 경우
            if(enemyOrBuff[i] == 1){
                long monsterAtk = a[i];
                long monsterHp = h[i];
                long atkTime = monsterHp / tempAtk;
                if(monsterHp % tempAtk != 0) atkTime++;
                long damage = (atkTime - 1) * monsterAtk;
                sumDamage += damage;
                minNeedHp = Math.max(minNeedHp, sumDamage + 1);
                continue;
            }
            // 회복약 방
            tempAtk += a[i];
            sumDamage -= h[i];
            if(sumDamage < 0) sumDamage = 0;
        }
        System.out.print(minNeedHp);
    }
}