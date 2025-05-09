import java.util.*;

public class BJ_2194_유닛_이동시키기 {
	public static int n, m;
	public static int[][]arr;
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	n = scan.nextInt();
    	m = scan.nextInt();

    	arr = new int[n+1][m+1];
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= m; j++) {
    			arr[i][j] = scan.nextInt();
    		}
    	}
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= m; j++) {
    			arr[i][j] += Math.max(arr[i-1][j], Math.max(arr[i][j-1],arr[i-1][j-1]));
    		}
    	}
    	System.out.println(arr[n][m]);
    }
}