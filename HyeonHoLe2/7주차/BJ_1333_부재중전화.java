import java.util.*;

public class Main{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int D = sc.nextInt();

        int total = N*L + (N-1)*5;
        for(int i=0; ;i += D){

            if(i>=total){
                System.out.print(i);
                break;
            }
            boolean Playing =  false;
            for(int j=0;j<N;j++){
                if(i>=j*(L+5) && i<j*(L+5)+L){
                   Playing = true; 
                   break;
                }
            }
            
            if(!Playing){
                 System.out.print(i);
                 break;
            }
        
        

        }
}
}