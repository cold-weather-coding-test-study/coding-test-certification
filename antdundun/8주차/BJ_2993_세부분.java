import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2993_세부분 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        int len = word.length();
        String min = "z";

        for (int i = 1; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                String tmp = reverse(word.substring(0, i)) + reverse(word.substring(i, j)) + reverse(word.substring(j));
                if (min.compareTo(tmp) > 0) {
                    min = tmp;
                }
            }
        }

        System.out.println(min);

    }

    private static String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

}
