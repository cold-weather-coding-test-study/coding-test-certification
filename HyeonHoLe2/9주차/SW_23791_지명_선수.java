import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(sc.nextLine());
            StringTokenizer stA = new StringTokenizer(sc.nextLine());
            StringTokenizer stB = new StringTokenizer(sc.nextLine());

            Queue<Integer> aQueue = new LinkedList<>();
            Queue<Integer> bQueue = new LinkedList<>();
            char[] result = new char[N];

            boolean[] selected = new boolean[N + 1]; 

            while (stA.hasMoreTokens()) {
                aQueue.add(Integer.parseInt(stA.nextToken()));
            }

            while (stB.hasMoreTokens()) {
                bQueue.add(Integer.parseInt(stB.nextToken()));
            }

            boolean isATurn = true;

            for (int pick = 0; pick < N; pick++) {
                if (isATurn) {
                    while (selected[aQueue.peek()]) aQueue.poll();
                    int selectedPlayer = aQueue.poll();
                    selected[selectedPlayer] = true;
                    result[selectedPlayer - 1] = 'A';
                } else {
                    while (selected[bQueue.peek()]) bQueue.poll();
                    int selectedPlayer = bQueue.poll();
                    selected[selectedPlayer] = true;
                    result[selectedPlayer - 1] = 'B';
                }
                isATurn = !isATurn;
            }

            System.out.println(new String(result));
        }
    }
}
