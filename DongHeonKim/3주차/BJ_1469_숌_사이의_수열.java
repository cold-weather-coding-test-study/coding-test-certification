import java.util.*;
import java.io.*;

public class BJ_1469_숌_사이의_수열 {

    public static int N;
    public static int[] elements;
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        backtracking();
    }

    public static void backtracking() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        elements = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            elements[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(elements); // 사전 순 빠른 수열을 위해 정렬

        // 초기 상태: 수열은 null로 채움, 사용 횟수는 0
        dfs_back(0, new String[N * 2], new int[N]);
        System.out.println(list.isEmpty() ? "-1" : list.get(0));
    }

    // 백트래킹 DFS
    public static void dfs_back(int depth, String[] answer, int[] count) {
        if (!list.isEmpty()) return; // 이미 답 찾았으면 끝냄

        if (depth == N * 2) {
            list.add(String.join(" ", answer));
            return;
        }

        if (answer[depth] != null) {
            dfs_back(depth + 1, answer, count);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (count[i] == 2) continue; // 이미 두 번 사용했으면 건너뜀

            int val = elements[i];
            int second = depth + val + 1;

            // 조건: 범위 초과 x, 두 위치 모두 비어 있어야 함
            if (second >= N * 2 || answer[second] != null) continue;

            // 숫자 두 개 배치
            answer[depth] = answer[second] = String.valueOf(val);
            count[i] = 2;

            dfs_back(depth + 1, answer, count);

            // 백트래킹 (되돌리기)
            answer[depth] = answer[second] = null;
            count[i] = 0;
        }
    }
}