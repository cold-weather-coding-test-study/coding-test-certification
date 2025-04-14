import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static final int SIZE = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String board = br.readLine();
            if(board.equals("end")) break;

            if(isValidFinalState(board)) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }

    static boolean isValidFinalState(String board) {
        // X, O, '.' 개수 세기
        int xCount = 0, oCount = 0;
        for(char c : board.toCharArray()) {
            if(c == 'X') xCount++;
            else if(c == 'O') oCount++;
        }

        if(!(xCount == oCount || xCount == oCount + 1)) return false;

        boolean xWin = checkWin(board, 'X');
        boolean oWin = checkWin(board, 'O');

        // 동시에 이긴 경우 -> 불가능
        if(xWin && oWin) return false;

        // X가 이긴 경우 -> xCount == oCount + 1
        if(xWin && !oWin) {
            return (xCount == oCount + 1);
        }

        // O가 이긴 경우 -> xCount == oCount
        if(!xWin && oWin) {
            return (xCount == oCount);
        }

        return (xCount + oCount == 9);
    }

    static boolean checkWin(String board, char player) {    
        // 가로 검사
        for(int r = 0; r < SIZE; r++) {
            if(board.charAt(r*3) == player &&
               board.charAt(r*3 + 1) == player &&
               board.charAt(r*3 + 2) == player) {
                return true;
            }
        }

        // 세로 검사
        for(int c = 0; c < SIZE; c++) {
            if(board.charAt(c) == player &&
               board.charAt(c + 3) == player &&
               board.charAt(c + 6) == player) {
                return true;
            }
        }

        if(board.charAt(0) == player &&
           board.charAt(4) == player &&
           board.charAt(8) == player) {
            return true;
        }

        if(board.charAt(2) == player &&
           board.charAt(4) == player &&
           board.charAt(6) == player) {
            return true;
        }

        return false;
    }
}
