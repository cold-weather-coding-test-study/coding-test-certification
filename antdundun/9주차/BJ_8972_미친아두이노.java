import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_8972_미친아두이노 {

    static int R, C;
    static char[][] map;
    static int ir, ic;
    static List<Node> robot;
    static int[] dr = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    
    static class Node {
    	int r;
    	int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}    	
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        robot = new ArrayList<>();
        
        for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (map[r][c]=='I') {
					ir = r;
					ic = c;
					map[r][c] = '.';
				} else if (map[r][c]=='R') {
					robot.add(new Node(r,c));
					map[r][c] = '.';
				}
			}
		}
        
        List<Integer> rm;
        String move = br.readLine();
        int idx = 0;
        
        while (true) {
        	if (idx == move.length()) {
        		break;
        	}
        	
        	// 종수 이동
        	int m = move.charAt(idx)-'0'-1;
        	
        	ir += dr[m];
        	ic += dc[m];
        	        	
        	for (int i = 0; i < robot.size(); i++) {
				Node rb = robot.get(i);
				
				// 로봇을 만나면
				if (ir==rb.r && ic==rb.c) {
					System.out.println("kraj "+(idx+1));
					return;
				}
			}
        	
        	// 로봇 이동
        	for (int i = 0; i < robot.size(); i++) {
				Node rb = robot.get(i);
				int min = 200;
				int moveR = 0;
				int moveC = 0;
				
				// 가장 가까운 위치로 이동
				for (int d = 0; d < 9; d++) {
					int nr = rb.r+dr[d];
					int nc = rb.c+dc[d];
					
					if (!check(nr, nc)) continue;
					
					int diff = Math.abs(ir-nr)+Math.abs(ic-nc);
					if (min > diff) {
						moveR = dr[d];
						moveC = dc[d];
						min = diff;
					}
				}
				
				rb.r += moveR;
				rb.c += moveC;
				
				// 종수를 만나면
				if (ir==rb.r && ic==rb.c) {
					System.out.println("kraj "+(idx+1));
					return;
				}
			}
        	
        	// 로봇이 서로 만나면
        	rm = new ArrayList<>();
        	for (int i = 0; i < robot.size(); i++) {
        		Node rb = robot.get(i);
        		
				for (int j = i+1; j < robot.size(); j++) {
					Node rb2 = robot.get(j);
					if (rb.r==rb2.r && rb.c==rb2.c) {
						if (!rm.contains(i)) {
							rm.add(i);
						}
						
						if (!rm.contains(j)) {
							rm.add(j);
						}
					}
				}
			}
        	
        	Collections.sort(rm, Comparator.reverseOrder());
        	for (int index : rm) {
				robot.remove(index);
			}
        	
        	for (int i = 0; i < robot.size(); i++) {
        		Node rb = robot.get(i);
			}
        	
        	idx++;
        }
        
        map[ir][ic] = 'I';
        for (int i = 0; i < robot.size(); i++) {
			Node rb = robot.get(i);
			map[rb.r][rb.c] = 'R';
		}
        
        for (int r = 0; r < R; r++) {
			System.out.println(map[r]);
		}
        
    }

	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}
    
}
