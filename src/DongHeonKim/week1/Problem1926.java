package DongHeonKim.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem1926 {

    public static int arr[][];
    public static boolean visited[][];
    public static int n, m, cnt;
    public static boolean zero=true;
    public static int dx[] = {0, 0, -1, 1};
    public static int dy[] = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j);
                    list.add(cnt);
                    cnt = 0;
                    zero=false;
                }
            }
        }

        if(zero) {
            System.out.println("0");
            System.out.println("0");
            return;
        }

        Collections.sort(list);
        System.out.println(list.size());
        System.out.println(list.get(list.size() - 1));
    }


    public static void dfs(int x, int y) {
        visited[x][y] = true;
        cnt++;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < n && cy < m) {
                if (!visited[cx][cy] && arr[cx][cy] == 1) {
                    dfs(cx, cy);
                }
            }
        }
    }
    
}
