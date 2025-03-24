import java.util.*;

public class BJ_1991_트리_순회 {
  static int[][] tree = new int[26][2]; // 왼쪽, 오른쪽 자식 저장용
  static StringBuilder preorder = new StringBuilder();
  static StringBuilder inorder = new StringBuilder();
  static StringBuilder postorder = new StringBuilder();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    for (int i = 0; i < n; i++) {
      char parent = sc.next().charAt(0);
      char left = sc.next().charAt(0);
      char right = sc.next().charAt(0);

      int idx = parent - 'A';
      tree[idx][0] = (left == '.' ? -1 : left - 'A');
      tree[idx][1] = (right == '.' ? -1 : right - 'A');
    }

    dfsPre(0);   // 루트는 항상 A (0)
    dfsIn(0);
    dfsPost(0);

    System.out.println(preorder);
    System.out.println(inorder);
    System.out.println(postorder);
  }

  // 전위 순회: 루트 → 왼쪽 → 오른쪽
  static void dfsPre(int node) {
    if (node == -1) return;
    preorder.append((char)(node + 'A'));
    dfsPre(tree[node][0]);
    dfsPre(tree[node][1]);
  }

  // 중위 순회: 왼쪽 → 루트 → 오른쪽
  static void dfsIn(int node) {
    if (node == -1) return;
    dfsIn(tree[node][0]);
    inorder.append((char)(node + 'A'));
    dfsIn(tree[node][1]);
  }

  // 후위 순회: 왼쪽 → 오른쪽 → 루트
  static void dfsPost(int node) {
    if (node == -1) return;
    dfsPost(tree[node][0]);
    dfsPost(tree[node][1]);
    postorder.append((char)(node + 'A'));
  }
}
