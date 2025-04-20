import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            int weight = sc.nextInt();
            int value = sc.nextInt();

            for (int j = K; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        System.out.println(dp[K]);
    }
}
