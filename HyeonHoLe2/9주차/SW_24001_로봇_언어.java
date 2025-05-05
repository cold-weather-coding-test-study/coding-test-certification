import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 0; test_case < T; test_case++) {
            String str = sc.nextLine();
            Set<Integer> currentPositions = new HashSet<>();
            currentPositions.add(0);

            int maxDistance = 0;

            for (char ch : str.toCharArray()) {
                Set<Integer> nextPositions = new HashSet<>();

                for (int pos : currentPositions) {
                    if (ch == 'L') {
                        nextPositions.add(pos - 1);
                    } else if (ch == 'R') {
                        nextPositions.add(pos + 1);
                    } else if (ch == '?') {
                        nextPositions.add(pos - 1);
                        nextPositions.add(pos + 1);
                    }
                }

                currentPositions = nextPositions;

          
                for (int pos : currentPositions) {
                    maxDistance = Math.max(maxDistance, Math.abs(pos));
                }
            }

            System.out.println(maxDistance);
        }
    }
}
