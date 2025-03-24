import java.util.Scanner;

public class BJ_1699_제곱수의_합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 1^2로만 표현하는 최악의 경우
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}
