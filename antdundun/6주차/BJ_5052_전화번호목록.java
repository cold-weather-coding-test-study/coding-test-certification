import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BJ_5052_전화번호목록 {
	
	static int T, N;
	static class Node {
		HashMap<Character, Node> child;
		boolean isEnd;
		
		public Node() {
			this.child = new HashMap<>();
			this.isEnd = false;			
		}
	}
	static class Trie {
		Node root;

		public Trie() {
			this.root = new Node();
		}
		
		void insert(char[] number) {
			Node node = this.root;
			
			for (int i = 0; i < number.length; i++) {
				char n = number[i];
				node.child.putIfAbsent(n, new Node());
				node = node.child.get(n);
			}
			
			node.isEnd = true;
		}
		
		boolean search(char[] number) {
			Node node = this.root;
			
			for (int i = 0; i < number.length; i++) {
				char n = number[i];
				if (node.child.containsKey(n)) {
					node = node.child.get(n);
					if (node.isEnd) {
						return true;
					}
				} else {
					return false;
				}
			}
			
			return true;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			Trie trie = new Trie();
			
			boolean isPossible = true;
			for (int i = 0; i < N; i++) {
				char[] number = br.readLine().toCharArray();
				
				if (trie.search(number)) {
					isPossible = false;
				} else {
					trie.insert(number);
				}
			}
			
			if (isPossible) {
				System.out.println("YES");				
			} else {
				System.out.println("NO");
			}
		}
		
	}
}
