import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2253_점프 {
	
	static int N, M;
	static List<Integer> stones;
	static int[][] dp;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        stones = new ArrayList<>();
        dp = new int[N+1][(int) Math.sqrt(2 * N) + 2];;
        
        for (int i = 0; i < M; i++) {
			stones.add(Integer.parseInt(br.readLine()));
		}
        
        for (int i = 0; i < N+1; i++) {
			Arrays.fill(dp[i], 10001);
		}
        
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {1, 0, 0}); // 현재 돌, 점프, 횟수
        dp[1][0] = 0;
        
        while (!que.isEmpty()) {
        	int[] cur = que.poll();
        	int stone = cur[0];
        	int jump = cur[1];
        	int cnt = cur[2];
        	
        	for (int k = jump-1; k <= jump+1; k++) {
				if (k > 0) {
					int next = stone+k;
					if (next <= N && !stones.contains(next) && dp[next][k] > cnt+1) {
						dp[next][k] = cnt+1;
						que.add(new int[] {next, k, cnt+1});
					}
				}
			}
        }
        
        int min = 10001;
        for (int i = 0; i < dp[N].length; i++) {
			min = Math.min(min, dp[N][i]);
		}
        
        if (min==10001) {
        	System.out.println(-1);
        } else {
        	System.out.println(min);
        }
    }
}
