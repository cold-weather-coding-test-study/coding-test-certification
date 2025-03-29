import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();  

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

   
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

    
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n + 1];
        int[] parent = new int[n + 1];

        queue.add(1);
        visit[1] = true;

        while (!queue.isEmpty()) {
            int pop = queue.poll();
            for (int j : list.get(pop)) {
                if (visit[j]) {
                    continue;  
                }
                visit[j] = true;
                parent[j] = pop;  
                queue.add(j);  
            }
        }

       
        for (int result = 2; result <= n; result++) {
            System.out.println(parent[result]);
        }
    }
}
