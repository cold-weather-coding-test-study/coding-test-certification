import java.util.*;

public class Main {
    static int n;
    static char[][] sign;
    static int[] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        String s = sc.next();
        sign = new char[n][n];
        result = new int[n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sign[i][j] = s.charAt(idx++);
            }
        }

        solve(0);
    }

    static void solve(int index) {
        if (index == n) {
            for (int i = 0; i < n; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            System.exit(0);
        }

        for (int num = -10; num <= 10; num++) {
            result[index] = num;
            if (isValid(index)) {
                solve(index + 1);
            }
        }
    }

    // index까지의 부분합이 부호 조건을 만족하는지 체크
    static boolean isValid(int index) {
        int sum = 0;
        for (int i = index; i >= 0; i--) {
            sum += result[i];
            char signChar = sign[i][index];
            if (signChar == '+' && sum <= 0) return false;
            if (signChar == '-' && sum >= 0) return false;
            if (signChar == '0' && sum != 0) return false;
        }
        return true;
    }
}
