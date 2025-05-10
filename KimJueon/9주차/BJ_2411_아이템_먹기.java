import java.util.*;

public class BJ_2411_아이템_먹기 {
    static int N, M;
    static boolean[][] obstacle;
    static List<int[]> items = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int A = sc.nextInt(); // 아이템 수
        int B = sc.nextInt(); // 장애물 수

        obstacle = new boolean[N + 1][M + 1];

        for (int i = 0; i < A; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            items.add(new int[]{x, y});
        }

        for (int i = 0; i < B; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            obstacle[x][y] = true;
        }

        // 출발점 (1,1) 추가
        items.add(new int[]{1, 1});
        // 도착점 (N,M) 추가
        items.add(new int[]{N, M});

        // 정렬: x 오름차순, y 오름차순
        items.sort(Comparator.<int[]>comparingInt(p -> p[0]).thenComparingInt(p -> p[1]));

        int result = 1;
        for (int i = 0; i < items.size() - 1; i++) {
            int[] from = items.get(i);
            int[] to = items.get(i + 1);
            // 이동 불가능한 경우(뒤로 가야 하는 경우)
            if (from[0] > to[0] || from[1] > to[1]) {
                result = 0;
                break;
            }
            result *= countPaths(from[0], from[1], to[0], to[1]);
        }

        System.out.println(result);
    }

    // DP로 (sx, sy)부터 (ex, ey)까지 오른쪽/위 방향으로 갈 수 있는 경로 수를 계산
    private static int countPaths(int sx, int sy, int ex, int ey) {
        int[][] dp = new int[ex - sx + 1][ey - sy + 1];

        for (int i = 0; i <= ex - sx; i++) {
            for (int j = 0; j <= ey - sy; j++) {
                int x = sx + i;
                int y = sy + j;

                if (obstacle[x][y]) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int fromLeft = (j > 0) ? dp[i][j - 1] : 0;
                    int fromDown = (i > 0) ? dp[i - 1][j] : 0;
                    dp[i][j] = fromLeft + fromDown;
                }
            }
        }
        return dp[ex - sx][ey - sy];
    }
}
