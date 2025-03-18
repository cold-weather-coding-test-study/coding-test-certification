import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_16564_히오스프로게이머 {

    static int N;
    static long K;
    static long[] level;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        level = new long[N];

        for (int i = 0; i < N; i++) {
            level[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(level);

        while (true) {
            int idx = 0;

            for (int i = 0; i < N; i++) {
                if (level[0] == level[i]) {
                    idx = i;
                }
            }

            if (K < idx + 1) {
                System.out.println(level[0]);
                return;
            }

            if (idx + 1 == N) {
                long ans = level[0] + K / (idx + 1);
                System.out.println(ans);
                return;
            }

            long k = K / (idx + 1);
            long diff = level[idx + 1] - level[0];

            for (int i = 0; i < idx + 1; i++) {
                if (k > diff) {
                    level[i] += diff;
                    K -= diff;
                } else {
                    level[i] += k;
                    K -= k;
                }
            }

        }
    }

}
