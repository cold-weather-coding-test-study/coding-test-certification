import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_18430_무기공학 {
	
	static int N, M, max;
	static int[][] map;
	static boolean[][] visit;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visit = new boolean[N][M];
        
        for (int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
        
        max = 0;
        dfs(0, 0);
        
        System.out.println(max);
        
    }

    private static void dfs(int idx, int sum) {
        if (idx == N*M) {
            max = Math.max(max, sum);
            return;
        }

        int r = idx/M;
        int c = idx%M;

        if (!visit[r][c]) {
            if (r+1<N && c-1>=0 && !visit[r+1][c] && !visit[r][c-1]) {
                visit[r][c] = true;
                visit[r+1][c] = true;
                visit[r][c-1] = true;
                dfs(idx+1, sum+map[r][c]*2+map[r+1][c]+map[r][c-1]);
                visit[r][c] = false;
                visit[r+1][c] = false;
                visit[r][c-1] = false;
            }

            if (r+1<N && c+1<M && !visit[r+1][c] && !visit[r][c+1]) {
                visit[r][c] = true;
                visit[r+1][c] = true;
                visit[r][c+1] = true;
                dfs(idx+1, sum+map[r][c]*2+map[r+1][c]+map[r][c+1]);
                visit[r][c] = false;
                visit[r+1][c] = false;
                visit[r][c+1] = false;
            }

            if (r-1>=0 && c-1>=0 && !visit[r-1][c] && !visit[r][c-1]) {
                visit[r][c] = true;
                visit[r-1][c] = true;
                visit[r][c-1] = true;
                dfs(idx+1, sum + map[r][c]*2+map[r-1][c]+map[r][c-1]);
                visit[r][c] = false;
                visit[r-1][c] = false;
                visit[r][c-1] = false;
            }

            if (r-1>=0 && c+1<M && !visit[r-1][c] && !visit[r][c+1]) {
                visit[r][c] = true;
                visit[r-1][c] = true;
                visit[r][c+1] = true;
                dfs(idx+1, sum+map[r][c]*2+map[r-1][c]+map[r][c+1]);
                visit[r][c] = false;
                visit[r-1][c] = false;
                visit[r][c+1] = false;
            }
        }

        dfs(idx+1, sum);
    }


}
