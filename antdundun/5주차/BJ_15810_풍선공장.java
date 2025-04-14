import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_15810_풍선공장 {

    static int N, M;

    static class Node implements Comparable<Node> {
        long time;
        long tot;

        public Node(long time, long tot) {
            this.time = time;
            this.tot = tot;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.tot, o.tot);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int staff = Integer.parseInt(st.nextToken());
            pq.add(new Node(staff, staff));
        }

        while (true) {
            Node cur = pq.poll();
            cnt++;

            if (cnt == M) {
                System.out.println(cur.tot);
                return;
            }

            pq.add(new Node(cur.time, cur.tot + cur.time));

        }

    }

}
