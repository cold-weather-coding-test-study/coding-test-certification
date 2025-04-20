import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n + 1][n + 1];
        int[][] prefix = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                grid[i][j] = sc.nextInt();

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                prefix[i][j] = grid[i][j] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];

        int maxProfit = Integer.MIN_VALUE;
        for (int k = 1; k <= n; k++) {
            for (int i = k; i <= n; i++) {
                for (int j = k; j <= n; j++) {
                    int total = prefix[i][j] - prefix[i - k][j] - prefix[i][j - k] + prefix[i - k][j - k];
                    maxProfit = Math.max(maxProfit, total);
                }
            }
        }

        System.out.println(maxProfit);
    }
}
