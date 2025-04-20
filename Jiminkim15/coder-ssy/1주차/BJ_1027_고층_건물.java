import java.util.Scanner;

public class BJ_1027_고층_건물 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 입력 처리
    int n = sc.nextInt(); // 빌딩 수
    int[] heights = new int[n]; // 빌딩 높이 배열
    for (int i = 0; i < n; i++) {
      heights[i] = sc.nextInt();
    }
    sc.close();

    int maxVisible = 0; // 가장 많이 보이는 빌딩 수

    // 각 빌딩에 대해 가시성 검사
    for (int i = 0; i < n; i++) {
      int visibleCount = 0;

      // 왼쪽 빌딩 검사
      double maxSlope = Double.NEGATIVE_INFINITY;
      for (int j = i - 1; j >= 0; j--) {
        double slope = (double) (heights[i] - heights[j]) / (i - j);
        if (slope > maxSlope) { // ">"를 사용
          maxSlope = slope;
          visibleCount++;
        }
      }

      // 오른쪽 빌딩 검사
      maxSlope = Double.NEGATIVE_INFINITY;
      for (int j = i + 1; j < n; j++) {
        double slope = (double) (heights[j] - heights[i]) / (j - i);
        if (slope > maxSlope) { // ">"를 사용
          maxSlope = slope;
          visibleCount++;
        }
      }

      // 최대 가시성 갱신
      maxVisible = Math.max(maxVisible, visibleCount);
    }

    // 결과 출력
    System.out.println(maxVisible);
  }
}
