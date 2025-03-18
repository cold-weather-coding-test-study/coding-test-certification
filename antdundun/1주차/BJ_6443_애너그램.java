import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BJ_6443_애너그램 {

    static int N;
    static char[] str;
    static int[] visit;
    static Stack<Character> stack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            str = br.readLine().toCharArray();

            stack = new Stack<>();
            Arrays.sort(str);
            visit = new int[26];

            for (char c : str) {
                visit[c - 'a']++;
            }

            perm(0);
        }

    }

    private static void perm(int cnt) {
        if (cnt == str.length) {
            StringBuilder sb = new StringBuilder();
            for (char c : stack) {
                sb.append(c);
            }

            System.out.println(sb.toString());
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (visit[i] == 0) continue;
            stack.push((char) (i + 'a'));
            visit[i]--;

            perm(cnt + 1);
            visit[i]++;
            stack.pop();
        }
    }

}
