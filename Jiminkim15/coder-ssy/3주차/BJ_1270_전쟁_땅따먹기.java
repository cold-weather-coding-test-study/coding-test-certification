import java.util.*;

public class BJ_1270_전쟁_땅따먹기 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); // 땅의 개수

    for (int i = 0; i < n; i++) {
      int t = sc.nextInt(); // 병사 수
      Map<Integer, Integer> countMap = new HashMap<>();

      for (int j = 0; j < t; j++) {
        int army = sc.nextInt();
        countMap.put(army, countMap.getOrDefault(army, 0) + 1);
      }

      int maxArmy = 0;
      int maxCount = 0;
      for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
        if (entry.getValue() > maxCount) {
          maxArmy = entry.getKey();
          maxCount = entry.getValue();
        }
      }

      if (maxCount > t / 2) {
        System.out.println(maxArmy);
      } else {
        System.out.println("SYJKGW");
      }
    }
  }
}
