import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ_14725_개미굴 {
    static int N;
    
    static class Node {
        HashMap<String, Node> child;
        
        public Node() {
            this.child = new HashMap<>();
        }
    }
    
    static class Trie {
        Node root;
        
        public Trie() {
            this.root = new Node();
        }
        
        void insert(String[] words) {
            Node node = this.root;
            
            for (String word : words) {
                node.child.putIfAbsent(word, new Node());
                node = node.child.get(word);
            }
        }
        
        void print() {
            printNode(this.root, "");
        }
        
        void printNode(Node node, String prefix) {
            TreeMap<String, Node> sortedChildren = new TreeMap<>(node.child);
            
            for (Map.Entry<String, Node> entry : sortedChildren.entrySet()) {
                System.out.println(prefix + entry.getKey());
                printNode(entry.getValue(), prefix + "--");
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        Trie trie = new Trie();
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String[] words = new String[K];
            
            for (int j = 0; j < K; j++) {
                words[j] = st.nextToken();
            }
            
            trie.insert(words);
        }
        
        trie.print();
    }
}
