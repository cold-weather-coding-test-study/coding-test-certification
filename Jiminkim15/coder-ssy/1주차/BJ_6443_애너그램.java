import java.util.*;

public class BJ_6443_애너그램 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    sc.nextLine();

    List<String> words = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      words.add(sc.nextLine());
    }
    sc.close();

    for (String word : words) {
      generateAnagrams(word);
    }
  }

  private static void generateAnagrams(String word) {
    TreeSet<String> result = new TreeSet<>();
    boolean[] visited = new boolean[word.length()];
    backtrack(word.toCharArray(), "", visited, result);

    for (String anagram : result) {
      System.out.println(anagram);
    }
  }

  private static void backtrack(char[] chars, String current, boolean[] visited, Set<String> result) {
    if (current.length() == chars.length) {
      result.add(current);
      return;
    }

    for (int i = 0; i < chars.length; i++) {
      if (visited[i]) continue;

      visited[i] = true;
      backtrack(chars, current + chars[i], visited, result);
      visited[i] = false;
    }
  }
}
