import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4963_섬의개수 {

    static int W, H, cnt;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while (true) {
        	st = new StringTokenizer(br.readLine());
        	
        	W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            
            if (W==0 && H==0) break;
            
            map = new int[H][W];
            visit = new boolean[H][W];
            cnt = 0;
            
            for (int r = 0; r < H; r++) {
            	st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
            
            for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (map[r][c]==1 && !visit[r][c]) {
						link(r, c);
					}
				}
			}
            
            System.out.println(cnt);
        }
    }

	private static void link(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {r,c});
		visit[r][c] = true;
		
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int cr = cur[0];
			int cc = cur[1];
						
			for (int d = 0; d < 8; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				if (!check(nr, nc)) continue;
				if (map[nr][nc]==0 || visit[nr][nc]) continue;
				
				que.add(new int[] {nr, nc});
				visit[nr][nc] = true;
			}
		}
		
		cnt++;
	}

	private static boolean check(int r, int c) {
		return 0<=r && r<H && 0<=c && c<W;
	}
    
}
