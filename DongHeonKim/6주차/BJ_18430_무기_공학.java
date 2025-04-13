import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] wood;
    static boolean[][] used;
    static int maxStrength = 0;

    static int[][][] boomerangDir = {
        {{1,0}, {0,1}},  // 아래 + 오른쪽
        {{1,0}, {0,-1}}, // 아래 + 왼쪽
        {{-1,0}, {0,1}}, // 위 + 오른쪽
        {{-1,0}, {0,-1}} // 위 + 왼쪽
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        wood = new int[N][M];
        used = new boolean[N][M];
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                wood[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0, 0, 0);
        
        System.out.println(maxStrength);
    }
    
    static void dfs(int r, int c, int sum){
        if(c == M) {
            dfs(r + 1, 0, sum);
            return;
        }
        
        if(r == N) {
            maxStrength = Math.max(maxStrength, sum);
            return;
        }
        
        if(used[r][c]) {
            dfs(r, c + 1, sum);
        } else {
            // 부메랑을 만들지 않고 넘어감
            dfs(r, c + 1, sum);
            
            // (r, c)를 중심으로 만들 수 있는 부메랑 4가지 시도
            for(int d = 0; d < 4; d++){
                int nr1 = r + boomerangDir[d][0][0];
                int nc1 = c + boomerangDir[d][0][1];
                int nr2 = r + boomerangDir[d][1][0];
                int nc2 = c + boomerangDir[d][1][1];
                
                if(isInRange(nr1, nc1) && !used[nr1][nc1] &&
                   isInRange(nr2, nc2) && !used[nr2][nc2]) {
                    
                    used[r][c] = true;
                    used[nr1][nc1] = true;
                    used[nr2][nc2] = true;
                    
                    int strength = wood[r][c] * 2 
                                 + wood[nr1][nc1]
                                 + wood[nr2][nc2];
                    
                    dfs(r, c + 1, sum + strength);
                    
                    used[r][c] = false;
                    used[nr1][nc1] = false;
                    used[nr2][nc2] = false;
                }
            }
        }
    }
    
    static boolean isInRange(int r, int c){
        return (r >= 0 && r < N && c >= 0 && c < M);
    }
}