import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_16938_캠프준비 {
	
	public static int N, L, R, X, ans;
	public static int[] level;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        level = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
			level[i] = Integer.parseInt(st.nextToken());
		}
        
        Arrays.sort(level);
        
        ans = 0;
        select(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        
        System.out.println(ans);
    }

	private static void select(int idx, int cnt, int min, int max, long sum) {
		if (cnt >= 2) {
			if (L<=sum && sum<=R && max-min >= X) {
				ans++;
			}
		}
		
		
		for (int i = idx; i < N; i++) {
			select(i+1, cnt+1, Math.min(min, level[i]), Math.max(max, level[i]), sum+level[i]);
		}
	}
    
}
