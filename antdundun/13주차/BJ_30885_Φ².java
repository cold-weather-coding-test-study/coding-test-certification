import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_30885_Φ² {
    static class Worm {
        long size;
        int location;
        Worm next;
        Worm prev;

        Worm(long size, int location) {
            this.size = size;
            this.location = location;
        }
    }

    static void appendWorm(Worm[] headRef, Worm newWorm) {
        if (headRef[0] == null) {
            headRef[0] = newWorm;
            newWorm.next = newWorm;
            newWorm.prev = newWorm;
        } else {
            Worm tail = headRef[0].prev;
            newWorm.prev = tail;
            newWorm.next = headRef[0];
            tail.next = newWorm;
            headRef[0].prev = newWorm;
        }
    }

    static void deleteWorm(Worm[] headRef, Worm target) {
        if (target.next == target) {
            headRef[0] = null;
        } else {
            target.prev.next = target.next;
            target.next.prev = target.prev;
            if (headRef[0] == target) {
                headRef[0] = target.next;
            }
        }
        target.next = null;
        target.prev = null;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Worm[] head = new Worm[1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            long size = Long.parseLong(st.nextToken());
            appendWorm(head, new Worm(size, i + 1));
        }

        int deleteCount = 0;
        Worm current = head[0];

        while (deleteCount < n - 1) {
            long addSize = 0;

            // 오른쪽 먼저 체크
            if (current.next != head[0] && current.size >= current.next.size) {
                addSize += current.next.size;
                Worm temp = current.next;
                deleteWorm(head, temp);
                deleteCount++;
            }

            // 왼쪽 체크
            if (current != head[0] && current.size >= current.prev.size) {
                addSize += current.prev.size;
                Worm temp = current.prev;
                deleteWorm(head, temp);
                deleteCount++;
            }

            current.size += addSize;
            current = current.next;
        }

        System.out.println(head[0].size);
        System.out.println(head[0].location);
    }
}
