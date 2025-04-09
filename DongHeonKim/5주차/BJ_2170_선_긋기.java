import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] lines = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lines, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int start = lines[0][0];
        int end = lines[0][1];
        long totalLength = 0;

        for (int i = 1; i < N; i++) {
            int curStart = lines[i][0];
            int curEnd = lines[i][1];

            if (curStart <= end) {
                end = Math.max(end, curEnd);
            } else {
                totalLength += end - start;
                start = curStart;
                end = curEnd;
            }
        }

        totalLength += end - start;

        System.out.println(totalLength);
    }
}
