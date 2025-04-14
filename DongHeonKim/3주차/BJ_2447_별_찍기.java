import java.util.Scanner;

public class BJ_2447_별_찍기 {
    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        map = new char[N][N];
        drawStar(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    // 재귀로 별 찍기
    static void drawStar(int x, int y, int size, boolean blank) {
        if (blank) {
            // 공백 채우기
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
        }

        if (size == 1) {
            map[x][y] = '*';
            return;
        }

        int newSize = size / 3;
        int count = 0;

        for (int i = x; i < x + size; i += newSize) {
            for (int j = y; j < y + size; j += newSize) {
                count++;
                // 가운데 칸은 공백으로 넘김
                if (count == 5) {
                    drawStar(i, j, newSize, true);
                } else {
                    drawStar(i, j, newSize, false);
                }
            }
        }
    }
}
