class PG_산모양타일링 {
    public int solution(int n, int[] tops) {
        int[] a = new int[n+1]; // 채워진 타일이 마름모로 끝나는 경우
        int[] b = new int[n+1]; // 채워진 타일이 마름모로 끝나지 않는 경우
        
        a[1] = 1;
        b[1] = tops[0]==1 ? 3 : 2;
        
        for (int i=2; i<=n; i++) {
            if (tops[i-1]==1) {
                a[i] = (a[i-1] + b[i-1])%10007;
                b[i] = (a[i-1]*2 + b[i-1]*3)%10007;
            } else {
                a[i] = (a[i-1] + b[i-1])%10007;
                b[i] = (a[i-1] + b[i-1]*2)%10007;
            }
        }
        
        return (a[n]+b[n])%10007;
    }
}
