import java.util.*;

public class BJ_6068_시간_관리하기 {
    static class Work implements Comparable<Work> {
        int time; // 일 걸리는 시간
        int deadline; // 마감 시간

        Work(int time, int deadline) {
            this.time = time;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Work o) {
            return o.deadline - this.deadline; // 마감시간 내림차순
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Work> works = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            int s = sc.nextInt();
            works.add(new Work(t, s));
        }

        Collections.sort(works); // 마감 시간 기준 정렬

        int currentTime = works.get(0).deadline; // 시작 가능한 가장 늦은 시간

        for (Work work : works) {
            currentTime = Math.min(currentTime, work.deadline); // 마감 시간을 넘지 않게
            currentTime -= work.time; // 일을 하고 나면 시간 감소

            if (currentTime < 0) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(currentTime);
    }
}
