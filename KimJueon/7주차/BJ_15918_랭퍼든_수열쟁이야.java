import java.util.*;

public class BJ_15918_랭퍼든_수열쟁이야 {
    static int n, x, y;
    static int count = 0;
    static int[] sequence;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        x = sc.nextInt() - 1; // 0-based index로 변경
        y = sc.nextInt() - 1;

        sequence = new int[2 * n];
        Arrays.fill(sequence, 0);

        backtrack(1);

        System.out.println(count);
    }

    static void backtrack(int num) {
        if (num > n) {
            if (sequence[x] == sequence[y] && sequence[x] != 0) {
                count++;
            }
            return;
        }

        for (int i = 0; i + num + 1 < sequence.length; i++) {
            int j = i + num + 1;

            if (sequence[i] == 0 && sequence[j] == 0) {
                sequence[i] = sequence[j] = num;
                backtrack(num + 1);
                sequence[i] = sequence[j] = 0;
            }
        }
    }
}
