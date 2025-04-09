import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] time = new int[N];
        for (int i = 0; i < N; i++) {
            time[i] = sc.nextInt();
        }

        long left = 1;
        long right = 1_000_000L * M;

        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long total = 0;

            for (int i = 0; i < N; i++) {
                total += mid / time[i];
                if (total >= M) break;
            }

            if (total >= M) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
