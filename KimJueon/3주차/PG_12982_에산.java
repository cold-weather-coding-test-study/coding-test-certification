import java.util.*;

public class PG_12982_에산 {
  public int solution(int[] d, int budget) {
    List<Integer> arr = new ArrayList<>();
    for (int num : d) {
      arr.add(num);
    }
    Collections.sort(arr);

    int answer = 0;
    for (int num : arr) {
      budget -= num;
      if (budget >= 0) answer++;
      else break;
    }

    return answer;
  }
}
