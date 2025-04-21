import java.util.Scanner;

public class Main {
    static int[] dp = new int[10001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        dp[0] = 1;

        for (int num = 1; num <= 3; num++) {
            for (int i = num; i <= 10000; i++) {
                dp[i] += dp[i - num];
            }
        }

        while (T-- > 0) {
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}
