import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_16401_과자나눠주기 {
	
    static int N, M;
    static long[] snack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        snack = new long[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
			snack[i] = Integer.parseInt(st.nextToken());
		}
        
        Arrays.sort(snack);
        
        long ans = 0;
        long lo = 1;
        long hi = snack[N-1];
        
        while (lo <= hi) {
        	long mid = (lo+hi)/2;
        	
        	int cnt = 0;
        	for (int i = 0; i < N; i++) {
				cnt += snack[i]/mid;
			}
        	
        	if (cnt < M) {
        		hi = mid-1;
        	} else {
        		ans = mid;
        		lo = mid+1;
        	}
        }
        
        System.out.println(ans);
    }
}
