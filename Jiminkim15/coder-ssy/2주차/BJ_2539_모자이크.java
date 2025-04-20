import java.util.*;

public class BJ_2539_모자이크 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 도화지 위쪽 변의 개수
        int M = sc.nextInt(); // 오른쪽 변의 개수

        int colorPaperCount = sc.nextInt(); // 색종이 개수
        int wrongLineCount = sc.nextInt(); // 잘못 칠해진 칸의 개수

        int[] wrongRows = new int[wrongLineCount];
        int[] wrongCols = new int[wrongLineCount];

        // 잘못 칠해진 위치를 각각의 배열에 저장
        for (int i = 0; i < wrongLineCount; i++) {
            wrongRows[i] = sc.nextInt();
            wrongCols[i] = sc.nextInt();
        }

        // 색종이 최소 크기를 찾는 과정
        int minSize = 1;
        int maxSize = Math.max(N, M);
        int answer = maxSize;

        while (minSize <= maxSize) {
            int midSize = (minSize + maxSize) / 2;
            if (canCoverAll(midSize, colorPaperCount, wrongRows, wrongCols)) {
                answer = midSize;
                maxSize = midSize - 1;
            } else {
                minSize = midSize + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean canCoverAll(int size, int count, int[] rows, int[] cols) {
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();

        for (int r : rows) rowSet.add((r - 1) / size);
        for (int c : cols) colSet.add((c - 1) / size);

        return rowSet.size() * colSet.size() <= count;
    }
}
