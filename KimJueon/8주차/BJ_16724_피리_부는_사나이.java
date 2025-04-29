import java.util.Scanner;

public class BJ_16724_피리_부는_사나이 {
    static int n, m;
    static char[][] map;
    static int[][] visited;
    static int count = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new char[n][m];
        visited = new int[n][m];

        sc.nextLine(); // 개행 제거
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        System.out.println(count);
    }

    static void dfs(int x, int y) {
        visited[x][y] = 1; // 탐색 중 표시

        int dir = direction(map[x][y]);
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (visited[nx][ny] == 0) {
            dfs(nx, ny);
        } else if (visited[nx][ny] == 1) {
            // 탐색 중인 노드를 다시 만났으면 싸이클 발생
            count++;
        }

        visited[x][y] = 2; // 탐색 완료 표시
    }

    static int direction(char c) {
        if (c == 'U') return 0;
        else if (c == 'D') return 1;
        else if (c == 'L') return 2;
        else return 3; // 'R'
    }
}
