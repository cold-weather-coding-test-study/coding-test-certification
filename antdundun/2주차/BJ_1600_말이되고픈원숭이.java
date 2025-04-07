import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1600_말이되고픈원숭이 {
	
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visit;
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] hr = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] hc = {-2, -1, 1, 2, 2, 1, -1, -2};
    
    static class Node {
    	int r;
    	int c;
    	int cnt;
    	int horse;
    	public Node(int r, int c, int cnt, int horse) {
    		this.r = r;
    		this.c = c;
    		this.cnt = cnt;
    		this.horse = horse;
    	}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        K = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        map = new int[H][W];
        visit = new boolean[H][W][K+1];
        
        for (int r = 0; r < H; r++) {
        	st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
        
        bfs();
        
    }

	private static void bfs() {
		Queue<Node> que = new LinkedList<>();
		que.add(new Node(0, 0, 0, 0));
		visit[0][0][0] = true;
		
		while (!que.isEmpty()) {
			Node cur = que.poll();
			
			if (cur.r==H-1 && cur.c==W-1) {
				System.out.println(cur.cnt);
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				
				if (!check(nr, nc)) continue;
				if (visit[nr][nc][cur.horse] || map[nr][nc]==1) continue;
				
				que.add(new Node(nr, nc, cur.cnt+1, cur.horse));
				visit[nr][nc][cur.horse] = true;
			}
			
			if (cur.horse < K) {
				for (int d = 0; d < 8; d++) {
					int nr = cur.r+hr[d];
					int nc = cur.c+hc[d];
					
					if (!check(nr, nc)) continue;
					if (visit[nr][nc][cur.horse+1] || map[nr][nc]==1) continue;
					
					que.add(new Node(nr, nc, cur.cnt+1, cur.horse+1));
					visit[nr][nc][cur.horse+1] = true;
				}
			}
		}
		
		System.out.println(-1);
	}

	private static boolean check(int r, int c) {
		return 0<=r && r<H && 0<=c && c<W;
	}
    
}
