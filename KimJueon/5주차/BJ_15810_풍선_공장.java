import java.io.*;
import java.util.*;

public class BJ_15810_풍선_공장 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long l=arr[0], h;
        h = (long)arr[0] * M; // 최악의 경우 == 가장 빨리 만드는 사람이 요구되는 모든 풍선을 다 만드는 경우

        long mid;
        while(l <= h){
            mid = (l + h) >> 1;

            // mid(시간 값)으로 풍선을 만들었을 때 갯수가 모자라다면,
            if(check(mid) < M){
                // 기준 시간 값을 늘려서 생산량을 늘려야 한다.
                l = mid + 1;
            }
            else{
                h = mid - 1;
            }
        }

        System.out.println(l);
    }

    static long check(long mid){
        long count = 0;

        long balloon;
        for(int i=0; i<N; ++i){
            // 주어진 시간 / 풍선 1개당 만드는 데 필요한 시간
            balloon = mid/arr[i];
            count += balloon;
        }

        return count;
    }
}