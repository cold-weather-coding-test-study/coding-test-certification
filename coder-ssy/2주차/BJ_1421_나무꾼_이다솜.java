import java.util.*;

public class BJ_1421_나무꾼_이다솜 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 입력 처리
    int N = scanner.nextInt(); // 나무 개수
    int C = scanner.nextInt(); // 자를 때 드는 비용
    int W = scanner.nextInt(); // 단위당 가격
    int[] trees = new int[N];

    int maxTreeLength = 0;
    for (int i = 0; i < N; i++) {
      trees[i] = scanner.nextInt();
      maxTreeLength = Math.max(maxTreeLength, trees[i]); // 가장 긴 나무 길이 저장
    }
    scanner.close();

    long maxProfit = 0;

    // 가능한 모든 L(나무 길이)를 시도
    for (int L = 1; L <= maxTreeLength; L++) {
      long totalProfit = 0;

      for (int tree : trees) {
        if (tree < L) continue; // 현재 L로 자를 수 없는 경우 패스

        int cutCount = (tree % L == 0) ? (tree / L) - 1 : (tree / L); // 필요한 자르기 횟수
        int pieceCount = tree / L; // 잘라서 나오는 조각 수

        long revenue = (long) pieceCount * L * W; // 수익
        long cost = (long) cutCount * C; // 비용
        long profit = revenue - cost; // 최종 이익

        if (profit > 0) {
          totalProfit += profit;
        }
      }

      // 최대 이익 갱신
      maxProfit = Math.max(maxProfit, totalProfit);
    }

    System.out.println(maxProfit);
  }
}
