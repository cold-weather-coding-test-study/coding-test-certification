import java.util.*;

public class BJ_10159_저울 {
    static final int INF = 1_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 물건 개수
        int M = sc.nextInt(); // 비교 수
        boolean[][] graph = new boolean[N + 1][N + 1];

        // 비교 입력 받기
        for (int i = 0; i < M; i++) {
            int heavy = sc.nextInt();
            int light = sc.nextInt();
            graph[heavy][light] = true; // heavy > light
        }

        // 플로이드-워셜: 간접 비교 관계 추론
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        // 각 물건에 대해 비교 불가능한 개수 계산
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (!graph[i][j] && !graph[j][i]) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
