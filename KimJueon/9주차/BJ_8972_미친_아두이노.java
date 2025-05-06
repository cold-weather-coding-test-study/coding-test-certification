import java.util.*;

public class BJ_8972_미친_아두이노 {
    static int R, C;
    static char[][] board;
    static List<int[]> crazy = new ArrayList<>();
    static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1}; // 1~9 방향 (5는 제자리)
    static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1}; // 1~9 방향

    static int jr, jc; // 종수 위치 (변경됨)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner 변수 유지
        R = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'I') {
                    jr = i;
                    jc = j;
                } else if (board[i][j] == 'R') {
                    crazy.add(new int[]{i, j});
                }
            }
        }

        char[] moves = sc.nextLine().toCharArray();

        for (int t = 0; t < moves.length; t++) {
            int dir = moves[t] - '0';

            // 1. 종수 이동
            jr += dr[dir];
            jc += dc[dir];

            if (board[jr][jc] == 'R') {
                System.out.println("kraj " + (t + 1));
                return;
            }

            board = new char[R][C]; // 보드 초기화
            board[jr][jc] = 'I';

            // 2. 미친 아두이노 이동
            Map<String, Integer> countMap = new HashMap<>();
            List<int[]> newCrazy = new ArrayList<>();

            for (int[] c : crazy) {
                int minDist = Integer.MAX_VALUE;
                int bestDir = 0;

                for (int d = 1; d <= 9; d++) {
                    if (d == 5) continue; // 제자리 X

                    int nr = c[0] + dr[d];
                    int nc = c[1] + dc[d];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                    int dist = Math.abs(nr - jr) + Math.abs(nc - jc);
                    if (dist < minDist) {
                        minDist = dist;
                        bestDir = d;
                    }
                }

                int nr = c[0] + dr[bestDir];
                int nc = c[1] + dc[bestDir];

                // 종수랑 같은 위치
                if (nr == jr && nc == jc) {
                    System.out.println("kraj " + (t + 1));
                    return;
                }

                String key = nr + "," + nc;
                countMap.put(key, countMap.getOrDefault(key, 0) + 1);
                newCrazy.add(new int[]{nr, nc});
            }

            crazy.clear();

            for (int[] c : newCrazy) {
                String key = c[0] + "," + c[1];
                if (countMap.get(key) == 1) {
                    crazy.add(c);
                    board[c[0]][c[1]] = 'R';
                }
            }
        }

        // 최종 보드 출력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 0) System.out.print('.');
                else System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
