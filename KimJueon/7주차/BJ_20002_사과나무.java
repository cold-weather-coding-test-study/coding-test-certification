import java.util.*;

public class BJ_20002_사과나무 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N][N];
        int[][] prefix = new int[N + 1][N + 1];

        // 입력
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                arr[i][j] = sc.nextInt();

        // 누적합 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                prefix[i][j] = arr[i - 1][j - 1]
                             + prefix[i - 1][j]
                             + prefix[i][j - 1]
                             - prefix[i - 1][j - 1];
            }
        }

        int maxProfit = Integer.MIN_VALUE;

        // 모든 정사각형 크기 K에 대해 검사
        for (int k = 1; k <= N; k++) {
            for (int i = k; i <= N; i++) {
                for (int j = k; j <= N; j++) {
                    int total = prefix[i][j]
                              - prefix[i - k][j]
                              - prefix[i][j - k]
                              + prefix[i - k][j - k];
                    maxProfit = Math.max(maxProfit, total);
                }
            }
        }

        System.out.println(maxProfit);
    }
}
