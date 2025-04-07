import java.io.*;
import java.util.*;

public class BJ_20495_수열과_헌팅 {
  static class Interval {
    int index;
    long min;
    long max;

    public Interval(int index, long a, long b) {
      this.index = index;
      this.min = a - b;
      this.max = a + b;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    Interval[] intervals = new Interval[N];
    long[] minArr = new long[N];
    long[] maxArr = new long[N];

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      intervals[i] = new Interval(i, a, b);
      minArr[i] = a - b;
      maxArr[i] = a + b;
    }

    Arrays.sort(minArr);
    Arrays.sort(maxArr);

    int[] minPos = new int[N];
    int[] maxPos = new int[N];

    for (Interval interval : intervals) {
      // minPos: 내 max보다 작은 max들을 count
      int minIndex = lowerBound(maxArr, interval.min);
      // maxPos: 내 min보다 큰 min들을 제외한 수
      int maxIndex = upperBound(minArr, interval.max);

      minPos[interval.index] = minIndex + 1;      // 1-based
      maxPos[interval.index] = maxIndex;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      sb.append(minPos[i]).append(" ").append(maxPos[i]).append("\n");
    }
    System.out.print(sb);
  }

  // lowerBound: target보다 작은 수의 개수
  private static int lowerBound(long[] arr, long target) {
    int left = 0, right = arr.length;
    while (left < right) {
      int mid = (left + right) / 2;
      if (arr[mid] < target) left = mid + 1;
      else right = mid;
    }
    return left;
  }

  // upperBound: target보다 작거나 같은 수의 개수
  private static int upperBound(long[] arr, long target) {
    int left = 0, right = arr.length;
    while (left < right) {
      int mid = (left + right) / 2;
      if (arr[mid] <= target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}
