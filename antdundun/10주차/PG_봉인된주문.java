import java.util.*;
import java.io.*;

class PG_봉인된주문 {
        
    public String solution(long n, String[] bans) {
        Arrays.sort(bans, (o1, o2) -> {
            if (o1.length()==o2.length()) return o1.compareTo(o2);
            return o1.length() - o2.length();
        });
        
        int len = bans.length;
        
        for (int i=0; i<len; i++) {
            String str = formmat(n);
            
            int strLen = str.length()-1;
            int bansLen = bans[i].length()-1;
            
            if (strLen > bansLen || (strLen==bansLen && bans[i].compareTo(str)<=0)) {
                n++;
            } else break;
        }
        
        return formmat(n);
    }
    
    private String formmat(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            long remain = n%26;
            n /= 26;
            if (remain==0) {
                remain = 26;
                n--;
            }
            sb.append((char)('a'+remain-1));
        }
        
        return sb.reverse().toString();
    }
}
