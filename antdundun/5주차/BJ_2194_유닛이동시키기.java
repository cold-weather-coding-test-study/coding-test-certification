import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2194_유닛이동시키 {
	
	static int N, M, A, B, K;
	static int[][] map;
	static boolean[][] visit;
	static int sr, sc, er, ec;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken())-1;
        B = Integer.parseInt(st.nextToken())-1;
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][M+1];
        visit = new boolean[N+1][M+1];
        
        for (int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	map[r][c] = 2; // 장애물 체크
		}
        
        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        er = Integer.parseInt(st.nextToken());
        ec = Integer.parseInt(st.nextToken());
        
        bfs(sr, sc);
    }

	private static void bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<>();
		
		que.add(new int[] {sr, sc, 0});
		visit[sr][sc] = true;
		
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
						
			if (r==er && c==ec) {
				System.out.println(cnt);
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if (nr < 1 || nr+A > N || nc < 1 || nc+B > M) continue;
				if (visit[nr][nc]) continue;
				
				if (unit(nr, nc)) continue;
				
				que.add(new int[] {nr, nc, cnt+1});
				visit[nr][nc] = true;
			}
		}
		
		System.out.println(-1);
	}

	private static boolean unit(int r, int c) {		
		for (int i = r; i <= r+A; i++) {
			for (int j = c; j <= c+B; j++) {
				if (map[i][j]==2) return true;
			}
		}
		return false;
	}
    
}
