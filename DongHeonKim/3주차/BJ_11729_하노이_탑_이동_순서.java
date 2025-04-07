import java.util.Scanner;

public class BJ_11729_하노이_탑_이동_순서 {
    static StringBuilder sb = new StringBuilder();
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        hanoi(N, 1, 3, 2); // N개를 1번 → 3번으로, 2번은 보조 기둥
        System.out.println(count);
        System.out.print(sb);
    }

    // 하노이 재귀 함수
    static void hanoi(int n, int from, int to, int via) {
        if (n == 1) {
            sb.append(from).append(" ").append(to).append("\n");
            count++;
            return;
        }

        hanoi(n - 1, from, via, to);   // 1단계: n-1개를 보조(via)로 이동
        sb.append(from).append(" ").append(to).append("\n"); // 2단계: 가장 큰 원판 이동
        count++;
        hanoi(n - 1, via, to, from);   // 3단계: n-1개를 최종 위치로 이동
    }
}
