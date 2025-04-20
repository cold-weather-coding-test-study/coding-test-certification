import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] grid;
    static BigInteger ans = BigInteger.valueOf(-1);
    static Map<BigInteger, Boolean> cache = new HashMap<>();
    static final BigInteger TWO = BigInteger.valueOf(2);
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 모든 가능한 등차 (di, dj)
                // 범위는 -N+1 ~ N-1, -M+1 ~ M-1 (밖으로 바로 벗어나면 한 칸만 선택하는 경우와 동일)
                for (int di = -N + 1; di < N; di++) {
                    for (int dj = -M + 1; dj < M; dj++) {
                        if (di == 0 && dj == 0) {
                            BigInteger candidate = BigInteger.valueOf(grid[i][j]);
                            if (isSquare(candidate) && candidate.compareTo(ans) > 0) {
                                ans = candidate;
                            }
                            continue;
                        }
                        
                        BigInteger candidate = BigInteger.ZERO;
                        int r = i, c = j;
                        // while문으로 경로상에 있는 모든 칸에 대해 후보 수를 이어붙임
                        while (r >= 0 && r < N && c >= 0 && c < M) {
                            candidate = candidate.multiply(BigInteger.TEN)
                                                 .add(BigInteger.valueOf(grid[r][c]));
                            if (isSquare(candidate) && candidate.compareTo(ans) > 0) {
                                ans = candidate;
                            }
                            r += di;
                            c += dj;
                        }
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
    
    static boolean isSquare(BigInteger num) {
        if (cache.containsKey(num)) {
            return cache.get(num);
        }
        if (num.compareTo(BigInteger.ZERO) < 0) {
            cache.put(num, false);
            return false;
        }
        BigInteger sqrtVal = sqrt(num);
        boolean res = sqrtVal.multiply(sqrtVal).equals(num);
        cache.put(num, res);
        return res;
    }
    
    static BigInteger sqrt(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new ArithmeticException("Square root of negative number");
        }
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
            return n;
        }
        BigInteger x = n;
        BigInteger y = x.add(n.divide(x)).divide(TWO);
        while (y.compareTo(x) < 0) {
            x = y;
            y = x.add(n.divide(x)).divide(TWO);
        }
        return x;
    }
}
