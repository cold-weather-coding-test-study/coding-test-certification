import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1926_그림 {

    static int N, M, max, cnt;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

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

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1 && !visit[r][c]) {
                    max = Math.max(max, bfs(r, c));
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(max);
    }

    private static int bfs(int r, int c) {
        Queue<int[]> que = new LinkedList<>();

        que.add(new int[]{r, c});
        visit[r][c] = true;
        int pic = 1;

        while (!que.isEmpty()) {
            int[] cur = que.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (!check(nr, nc)) continue;
                if (map[nr][nc] == 0 || visit[nr][nc]) continue;

                que.add(new int[]{nr, nc});
                visit[nr][nc] = true;
                pic++;
            }
        }

        return pic;
    }

    private static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

}
