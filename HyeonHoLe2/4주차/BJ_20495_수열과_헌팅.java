import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] a = new int[N];
        int[] b = new int[N];

        int[] aMinus = new int[N];
        int[] aPlus = new int[N];

        int[] originalMinus = new int[N];
        int[] originalPlus = new int[N];

        StringTokenizer st;
        for(int i=0; i<N; ++i){
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            aMinus[i] = a[i] - b[i];
            aPlus[i] = a[i] + b[i];

            originalMinus[i] = aMinus[i];
            originalPlus[i] = aPlus[i];
        }
        Arrays.sort(aMinus);
        Arrays.sort(aPlus);
        int l, h, mid;
        StringBuilder sb = new StringBuilder();       
        for(int i=0; i<N; ++i){
        
            l = 0;
            h = N;
            while (l<h){
                mid = (l+h) >> 1;
                if(aPlus[mid] < originalMinus[i]){
                    l = mid + 1;
                }
                else{
                    h = mid;
                }
            }
            sb.append(h+1).append(" ");
            l = 0;
            h = N;
            while (l < h){
                mid = (l+h) >> 1;
                if(aMinus[mid] <= originalPlus[i]){
                    l = mid + 1;
                }
                else{
                    h = mid;
                }
            }
            sb.append(h).append("\n");
        }
        System.out.print(sb);
    }
}
