import java.util.Scanner;

public class BJ_14888_연산자_끼워넣기 {
    static int N;
    static int[] numbers;
    static int[] operators = new int[4]; // +, -, *, /
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            operators[i] = sc.nextInt();
        }

        dfs(1, numbers[0]);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int idx, int result) {
        if (idx == N) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;

                int next = result;

                if (i == 0) {
                    next += numbers[idx];
                } else if (i == 1) {
                    next -= numbers[idx];
                } else if (i == 2) {
                    next *= numbers[idx];
                } else if (i == 3) {
                    if (result < 0) {
                        next = -(-result / numbers[idx]);
                    } else {
                        next /= numbers[idx];
                    }
                }

                dfs(idx + 1, next);
                operators[i]++; // 백트래킹
            }
        }
    }
}
