import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16724_피리부는사나이 {

    static int N, M, cnt;
    static char[][] map;
    static boolean[][] visit, cycle;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M];
        cycle = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }

        cnt = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!visit[r][c]) {
                	dfs(r,c);
                }
            }
        }

        System.out.println(cnt);
    }

	private static void dfs(int r, int c) {
		visit[r][c] = true;
		
		int d = 0;
		if (map[r][c]=='D') {
			d = 1;
		} else if (map[r][c]=='L') {
			d = 2;
		} else if (map[r][c]=='R') {
			d = 3;
		}
		
		int nr = r+dr[d];
		int nc = c+dc[d];
		
		if (!visit[nr][nc]) {
			dfs(nr, nc);
		} else {
			if (!cycle[nr][nc]) {
				cnt++;
			}
		}
		
		cycle[r][c] = true;
	}

}
