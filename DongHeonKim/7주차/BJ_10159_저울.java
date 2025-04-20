public class BJ_10159_저울 {
    
}
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        boolean[][] heavier = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            heavier[from][to] = true;
        }

        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    if (heavier[i][k] && heavier[k][j])
                        heavier[i][j] = true;

        for (int i = 1; i <= N; i++) {
            int known = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (heavier[i][j] || heavier[j][i]) known++;
            }
            System.out.println(N - 1 - known);
        }
    }
}
