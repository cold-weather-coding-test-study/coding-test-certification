import java.util.*;
public class BJ_10844_Step {
    static long[][] dp;
    static long mod = 1000000000;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        dp = new long[N+1][10];

        /*
         * N=> N개짜리 계단
         * if N=1
         * 계단 : 1/2/3/4/5/6/7/8/9	=> 9개
         * if N=2
         * 계단 : 01/10,12/21,23/32,34/43,45/54,56/65,67/76,78/87,89/98	=>17개
         */


        //N=1 경우의 수가 하나
        for(int i=1;i<10;i++) {
            dp[1][i]=1;
        }

        //N>1
        for(int i=2;i<=N;i++) {
            //0~9까지 탐색
            for(int j=0; j<10; j++) {
                if(j==0) {
                    dp[i][j]=dp[i-1][1]%mod;
                }else if(j==9) {
                    dp[i][j]=dp[i-1][8]%mod;
                }else {
                    dp[i][j]=(dp[i-1][j-1]+dp[i-1][j+1])%mod;
                }
            }
        }

        System.out.println(Arrays.stream(dp[N]).sum()%mod);
        sc.close();
    }


}
