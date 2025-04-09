import java.util.*;

public class Main {
    static int N, M, A, B, K;
    static boolean[][] obstacle;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y, dist;
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    // 유닛이 해당 위치에 존재할 수 있는지 체크
    static boolean canPlace(int x, int y) {
        // 유닛 범위를 벗어나면 안됨
        if (x + A > N || y + B > M) return false;

        for (int i = x; i < x + A; i++) {
            for (int j = y; j < y + B; j++) {
                if (obstacle[i][j]) return false;
            }
        }
        return true;
    }

    static int bfs(int sx, int sy, int ex, int ey) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == ex && p.y == ey) return p.dist;

            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visited[nx][ny] && canPlace(nx, ny)) {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny, p.dist + 1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        A = sc.nextInt();
        B = sc.nextInt();
        K = sc.nextInt();

        obstacle = new boolean[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            obstacle[r][c] = true;
        }

        int sr = sc.nextInt() - 1;
        int sc_ = sc.nextInt() - 1;
        int er = sc.nextInt() - 1;
        int ec = sc.nextInt() - 1;

        System.out.println(bfs(sr, sc_, er, ec));
    }
}
