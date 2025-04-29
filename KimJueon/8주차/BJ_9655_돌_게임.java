import java.util.Scanner;

public class BJ_9655_돌_게임 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        boolean[] dp = new boolean[N + 4]; // 0부터 시작하고, N+3까지 커버

        // 기본 조건
        dp[0] = false; // 돌 0개면, 지난 사람이 이긴 거임 (현재 사람이 진 거임)
        dp[1] = true;  // 돌 1개면, 상근이가 가져가서 이김
        dp[2] = false; // 2개면 상근이가 1개 가져가면 창영이 1개 가져가서 이김
        dp[3] = true;  // 3개면 상근이가 3개 가져가서 이김

        for (int i = 4; i <= N; i++) {
            dp[i] = !dp[i-1] || !dp[i-3];
        }

        System.out.println(dp[N] ? "SK" : "CY");
    }
}
