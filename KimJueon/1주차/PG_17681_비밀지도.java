public class PG_17681_비밀지도 {
  public static String[] solution(int n, int[] arr1, int[] arr2) {
    String[] answer = new String[n];

    for (int i = 0; i < n; i++) {
      String bianry1 = String.format("%" + n + "s",
          Integer.toBinaryString(arr1[i])).replace(" ", "0");
      String bianry2 = String.format("%" + n + "s",
          Integer.toBinaryString(arr2[i])).replace(" ", "0");
      StringBuilder result = new StringBuilder();

      for (int j = 0; j < n; j++) {
        char a = bianry1.charAt(j);
        char b = bianry2.charAt(j);
        if (a == '1' || b == '1') {
          result.append("#");
        }
        else result.append(" ");
      }

      answer[i] = result.toString();
    }

    return answer;
  }
}