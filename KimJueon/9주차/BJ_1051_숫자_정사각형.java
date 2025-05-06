import java.util.Scanner;

public class BJ_1051_숫자_정사각형 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 행
        int m = sc.nextInt(); // 열
        sc.nextLine(); // 버퍼 클리어

        char[][] board = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            board[i] = line.toCharArray();
        }

        int maxLen = 1; // 최소 정사각형 크기는 1x1

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 최대 가능한 정사각형 크기 구하기
                for (int l = 1; i + l < n && j + l < m; l++) {
                    if (board[i][j] == board[i][j + l] &&
                        board[i][j] == board[i + l][j] &&
                        board[i][j] == board[i + l][j + l]) {
                        maxLen = Math.max(maxLen, l + 1);
                    }
                }
            }
        }

        System.out.println(maxLen * maxLen); // 넓이 출력
    }
}
