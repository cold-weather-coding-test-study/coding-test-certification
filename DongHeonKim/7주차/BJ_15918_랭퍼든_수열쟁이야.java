import java.util.Scanner;

public class Main {
    static int n, x, y, count = 0;
    static int[] arr;
    static boolean[] used;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1;

        arr = new int[2 * n];
        used = new boolean[n + 1];

        backtrack(0);
        System.out.println(count);
    }

    static void backtrack(int depth) {
        if (depth == 2 * n) {
            if (arr[x] == arr[y]) count++;
            return;
        }

        if (arr[depth] != 0) {
            backtrack(depth + 1);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (used[i]) continue;

            int second = depth + i + 1;
            if (second < 2 * n && arr[depth] == 0 && arr[second] == 0) {
                arr[depth] = i;
                arr[second] = i;
                used[i] = true;
                backtrack(depth + 1);
                arr[depth] = 0;
                arr[second] = 0;
                used[i] = false;
            }
        }
    }
}
