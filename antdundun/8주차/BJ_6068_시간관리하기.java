import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_6068_시간관리하기 {

    static int N;

    static class Node implements Comparable<Node> {
        int T;
        int S;
        int M; // S-T

        public Node(int T, int S, int M) {
            this.T = T;
            this.S = S;
            this.M = M;
        }

        @Override
        public int compareTo(Node o) {
            if (this.S == o.S) return o.M - this.M;
            return o.S - this.S;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            pq.add(new Node(T, S, S - T));
        }

        Node last = pq.poll();
        int time = last.M;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.S < time) {
                time = cur.S;
            }

            time -= cur.T;

        }

        System.out.println(time < 0 ? -1 : time);

    }

}
