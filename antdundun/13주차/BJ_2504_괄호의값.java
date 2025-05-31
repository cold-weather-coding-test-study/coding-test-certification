import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_2504_괄호의값 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>();

        String str = br.readLine();
        int len = str.length();

        int ans = 0;
        int tmp = 1;

        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);

            if (ch=='(' || ch=='[') {

                tmp *= (ch=='(') ? 2 : 3;
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }

                if (ch==')') {
                    if (stack.peek()!='(') {
                        System.out.println(0);
                        return;
                    }

                    if (str.charAt(i-1)=='(') {
                        ans += tmp;
                    }

                    stack.pop();
                    tmp /= 2;
                } else {
                    if (stack.peek()!='[') {
                        System.out.println(0);
                        return;
                    }

                    if (str.charAt(i-1)=='[') {
                        ans += tmp;
                    }

                    stack.pop();
                    tmp /= 3;
                }
            }
        }

        System.out.println(!stack.isEmpty() ? 0 : ans);

    }
}
