import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2411_아이템먹기 {

    static int N, M, A, B;
    static int[][] map, dp;
    static Node[] items; 
    
    static class Node {
    	int r;
    	int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];        
        dp = new int[N][M];
        items = new Node[A+1];
        
        // 아이템
        for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			
			items[i] = new Node(r,c);
		}
        
        items[A] = new Node(N-1, M-1);
        
        Arrays.sort(items, (o1, o2) -> {
        	if (o1.r == o2.r) {
        		return o1.c-o2.c;
        	}
        	return o1.r-o2.r;
        });
        
        for (int i = 1; i < A; i++) {
			if (items[i].r < items[i-1].r || items[i].c < items[i-1].c) {
				System.out.println(0);
				return;
			}
		}
        
        // 장애물
        for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			
			map[r][c] = -1;
		}
        
        dp[0][0] = 1;
        
        int idx = 0;
        int sr = 0;
        int sc = 0;
        
        while (idx <= A) {
        	int er = items[idx].r;
            int ec = items[idx].c;
            
        	for (int r = sr; r <= er; r++) {
				for (int c = sc; c <= ec; c++) {
					if (map[r][c]==-1) continue;
					if (0 < r && 0 < c) {
						dp[r][c] = dp[r-1][c] + dp[r][c-1];
					} else if (0==r && 0 < c) {
						dp[r][c] = dp[r][c-1];
					} else if (0 < r && 0==c){
						dp[r][c] = dp[r-1][c];
					}
				}
			}
        	
        	idx++;
        	sr = er;
        	sc = ec;
        }
        
        System.out.println(dp[N-1][M-1]);
    }

}
