import java.util.*;

public class BJ_15810_풍선_공장 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt(); // 스태프 수
    int m = sc.nextInt(); // 풍선 수

    int[] time = new int[n];
    int maxTime = 0;

    for (int i = 0; i < n; i++) {
      time[i] = sc.nextInt();
      maxTime = Math.max(maxTime, time[i]);
    }

    long left = 1;
    long right = (long) maxTime * m;
    long result = right;

    while (left <= right) {
      long mid = (left + right) / 2;

      long balloons = 0;
      for (int i = 0; i < n; i++) {
        balloons += mid / time[i];
        if (balloons >= m) break; // 시간 초과 방지
      }

      if (balloons >= m) {
        result = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    System.out.println(result);
  }
}
