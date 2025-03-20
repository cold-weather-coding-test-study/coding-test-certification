package DongHeonKim.week2;

import java.io.*;
import java.util.*;


public class BJ_1495_기타리스트{
    
    static boolean[][] dp;
    static int N; //곡 개수
    static int S; // 시작 볼륨
    static int M; // 최대 볼륨
    static int[] V; //볼륨 변화량량

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        V = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            V[i] = Integer.parseInt(st.nextToken());
        }

        maxVolume(N);

    }

    private static void maxVolume(int N) {
        dp = new boolean[N+1][M+1];
        dp[0][S] = true;    
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j <= M; j++) {
                if(dp[i][j]){
                    if(j + V[i] <= M)
                        dp[i+1][j+V[i]] = true;
                    if(j - V[i] >= 0)
                        dp[i+1][j-V[i]] = true;
                }
            }
        }

        int maxVolume = -1;
        for(int j =0; j <= M; j++) {
            if(dp[N][j])
                maxVolume = j;
        }

        System.out.println(maxVolume);
    }
}