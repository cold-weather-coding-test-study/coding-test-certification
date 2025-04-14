import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	static int A,B,N,M;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		A = sc.nextInt();
		B = sc.nextInt();
		int K = sc.nextInt();
		int[][] board = new int[N][M];
		for (int k = 0; k < K; k++) {
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			board[a][b] = 1;
		}
		int[] start = new int[] {sc.nextInt()-1,sc.nextInt()-1};
		int[] end = new int[] {sc.nextInt()-1,sc.nextInt()-1};
		System.out.println(BFS(start,end,board));
	}
	public static int BFS(int[] start,int[] end,int[][] board) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(start);
		boolean[][] v = new boolean[N][M];
		v[start[0]][start[1]] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(--size>=0) {
				int[] now = q.poll();
				if(now[0] == end[0] && now[1] == end[1]) return cnt;
				for (int d = 0; d < 4; d++) {
					int nx = now[0]+dx[d];
					int ny = now[1]+dy[d];
					if(func(now[0],now[1],board,d) && !v[nx][ny]) {
						v[nx][ny] = true;
						q.add(new int[] {nx,ny});
					}
				}				
			}
			cnt++;
		}
		return -1;
	}
	
	public static boolean func(int x, int y, int[][] board, int d) {
		for (int i = x; i < x+A; i++) {
			for (int j = y; j < y+B; j++) {
				int nx = i+dx[d];
				int ny = j+dy[d];
				if(0 > nx || nx >= N || 0 > ny || ny >= M || board[nx][ny] == 1) {
					return false;
				}
			}
		}
		return true;
	}

}