import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3184_양 {
	
	static int R, C;
	static char[][] map;
	static boolean[][] visit;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int liveSheep, liveWolf;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        visit = new boolean[R][C];
        liveSheep = 0;
        liveWolf = 0;
        
        for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
        
        for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!visit[r][c] && map[r][c]!='#') {
					bfs(r, c);
				}
			}
		}
        
        System.out.println(liveSheep+" "+liveWolf);
    }

	private static void bfs(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {r,c});
		visit[r][c] = true;
		
		int sheep = 0;
		int wolf = 0;
		
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			
			if (map[cur[0]][cur[1]]=='o') sheep++;
			if (map[cur[0]][cur[1]]=='v') wolf++;
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				if (!check(nr, nc)) continue;
				if (visit[nr][nc] || map[nr][nc]=='#') continue;
								
				que.add(new int[] {nr, nc});
				visit[nr][nc] = true;
			}
		}
		
		if (sheep > wolf) {
			liveSheep += sheep;
		} else {
			liveWolf += wolf;
		}
	}

	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}

}
