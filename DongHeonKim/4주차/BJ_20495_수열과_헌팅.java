import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    // lowerBound: 배열에서 key보다 작지 않은 최초의 인덱스를 반환 (즉, 첫번째 >= key)
    static int lowerBound(long[] arr, long key) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < key) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
    
    // upperBound: 배열에서 key보다 큰 최초의 인덱스를 반환 (즉, 첫번째 > key)
    static int upperBound(long[] arr, long key) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= key) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        long[] L = new long[N];
        long[] R = new long[N];
        
        for (int i = 0; i < N; i++) {
            String[] parts = br.readLine().split(" ");
            long a = Long.parseLong(parts[0]);
            long b = Long.parseLong(parts[1]);
            L[i] = a - b;
            R[i] = a + b;
        }
        
        long[] sortedL = L.clone();
        long[] sortedR = R.clone();
        Arrays.sort(sortedL);
        Arrays.sort(sortedR);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int minRank = lowerBound(sortedR, L[i]) + 1;
            int maxRank = upperBound(sortedL, R[i]);
            
            sb.append(minRank).append(" ").append(maxRank).append("\n");
        }
        
        System.out.print(sb);
    }
}
