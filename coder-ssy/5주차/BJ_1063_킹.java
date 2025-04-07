import java.util.*;

public class BJ_1063_킹 {

  static Map<String, int[]> dirMap = new HashMap<>();

  static {
    dirMap.put("R", new int[]{0, 1});
    dirMap.put("L", new int[]{0, -1});
    dirMap.put("B", new int[]{-1, 0});
    dirMap.put("T", new int[]{1, 0});
    dirMap.put("RT", new int[]{1, 1});
    dirMap.put("LT", new int[]{1, -1});
    dirMap.put("RB", new int[]{-1, 1});
    dirMap.put("LB", new int[]{-1, -1});
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String kingPos = sc.next();
    String stonePos = sc.next();
    int n = sc.nextInt();
    sc.nextLine();

    int[] king = parsePos(kingPos);
    int[] stone = parsePos(stonePos);

    for (int i = 0; i < n; i++) {
      String move = sc.nextLine();
      int[] d = dirMap.get(move);

      int ny = king[0] + d[0];
      int nx = king[1] + d[1];

      // 킹이 이동할 위치가 체스판 밖이면 무시
      if (!isInBounds(ny, nx)) continue;

      // 킹이 이동할 자리에 돌이 있으면 돌도 같은 방향으로 이동
      if (ny == stone[0] && nx == stone[1]) {
        int sy = stone[0] + d[0];
        int sx = stone[1] + d[1];

        // 돌도 범위 안에 있어야 이동
        if (!isInBounds(sy, sx)) continue;

        // 이동
        stone[0] = sy;
        stone[1] = sx;
      }

      king[0] = ny;
      king[1] = nx;
    }

    System.out.println(posToString(king));
    System.out.println(posToString(stone));
  }

  static int[] parsePos(String s) {
    return new int[]{s.charAt(1) - '0', s.charAt(0) - 'A' + 1}; // (행, 열)
  }

  static String posToString(int[] pos) {
    return (char) (pos[1] + 'A' - 1) + String.valueOf(pos[0]);
  }

  static boolean isInBounds(int y, int x) {
    return 1 <= y && y <= 8 && 1 <= x && x <= 8;
  }
}
