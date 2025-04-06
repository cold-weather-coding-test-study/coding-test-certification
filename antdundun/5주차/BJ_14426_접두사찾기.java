import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_14426_접두사찾기 {
	
	static int N, M;
	
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
		
		// 트라이에 단어 추가
		void insert(char[] word) {
			Node node = this.root;
			
			for (int i = 0; i < word.length; i++) {
				char c = word[i];
				node.child.putIfAbsent(c, new Node());
				node = node.child.get(c);
			}
			
			node.isEnd = true;
		}
		
		// 트라이에서 특정 단어 찾기
		boolean search(char[] word) {
			Node node = this.root;
			
			for (int i = 0; i < word.length; i++) {
				char c = word[i];
				if (!node.child.containsKey(c)) return false;
				node = node.child.get(c);
			}
			
			return true;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        Trie root = new Trie();
        
        for (int i = 0; i < N; i++) {
			char[] word = br.readLine().toCharArray();
			root.insert(word);
		}
        
        int cnt = 0;
        
        for (int i = 0; i < M; i++) {
			char[] word = br.readLine().toCharArray();
			
			if (root.search(word)) cnt++;
		}
        
        System.out.println(cnt);
        
    }

}
