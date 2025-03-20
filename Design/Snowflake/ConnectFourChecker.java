package Snowflake;

public class ConnectFourChecker {
    public static final int WIN_COUNT = 4;
    char[][] board;
    Character winner;

    public ConnectFourChecker(char[][] board) {
        this.board = board;
        this.winner = null;
    }

    public String checkMove(int row, int col, char player) {
        if(winner != null) return "Game Over: " + winner + " already won!";
        if(board[row][col] != '.') return "Position is occupied";
        board[row][col] = player;
        if(isWiner(board, row, col, player)) {
            winner = player;
            return player + "wins!";
        }
        return "no result";
    }

    public boolean isWiner(char[][] board, int row, int col, char player) {
        return checkDirection(board, row, col, player, 0, 1) ||
                checkDirection(board, row, col, player, 1,0) ||
                checkDirection(board, row, col, player, 1, 1) ||
                checkDirection(board, row, col, player, 1, -1);
    }

    public boolean checkDirection(char[][] board, int row, int col, char player, int dr, int dc) {
        int count = 1;
        count += countConsecutive(board, row, col, player, dr, dc);
        count += countConsecutive(board, row, col, player, -dr, -dc);
        return count >= WIN_COUNT;
    }

    public int countConsecutive(char[][] board, int row, int col, char player, int dr, int dc) {
        int r = row + dr, c = col + dc;
        int count = 0;
        while(isValid(board, r, c) && board[r][c] == player) {
            count++;
            r += dr;
            c += dc;
        }
        return count;
    }

    public boolean isValid(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'a', '.', '.', '.'},
                {'.', '.', 'a', 'b', '.', '.', '.'},
                {'.', 'a', '.', 'b', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
        };
        ConnectFourChecker cc = new ConnectFourChecker(board);
        System.out.println(cc.checkMove(0, 0, 'b'));
        System.out.println(cc.checkMove(0, 0, 'a'));
        System.out.println(cc.checkMove(1, 0, 'b'));
        System.out.println(cc.checkMove(2, 0, 'b'));
        System.out.println(cc.checkMove(3, 0, 'b'));
    }
}
