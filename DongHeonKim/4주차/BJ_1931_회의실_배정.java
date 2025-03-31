import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Meeting implements Comparable<Meeting> {
        long start, end;
        Meeting(long start, long end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Meeting other) {
            if (this.end == other.end) {
                return Long.compare(this.start, other.start);
            }
            return Long.compare(this.end, other.end);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            meetings[i] = new Meeting(start, end);
        }
        
        // 종료 시간이 빠른 순으로 정렬 (종료 시간이 같으면 시작 시간이 빠른 순)
        Arrays.sort(meetings);
        
        int count = 0;
        long currentEnd = 0;
        for (int i = 0; i < N; i++) {
            if (meetings[i].start >= currentEnd) {
                count++;
                currentEnd = meetings[i].end;
            }
        }
        
        System.out.println(count);
    }
}
