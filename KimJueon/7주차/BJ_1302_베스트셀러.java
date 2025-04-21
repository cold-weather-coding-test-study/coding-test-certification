import java.io.*;
import java.util.*;

public class BJ_1302_베스트셀러 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String title = br.readLine();
            map.put(title, map.getOrDefault(title, 0) + 1);
        }

        int max = 0;
        List<String> bestSellers = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            if (count > max) {
                max = count;
                bestSellers.clear();
                bestSellers.add(entry.getKey());
            } else if (count == max) {
                bestSellers.add(entry.getKey());
            }
        }

        Collections.sort(bestSellers); // 사전순 정렬
        System.out.println(bestSellers.get(0)); // 가장 앞선 제목 출력
    }
}
