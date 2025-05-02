package Design;
class TicTacToe {
    int[] rows;
    int[] cols;
    int diag;
    int antidiag;
    int n;

    public TicTacToe(int n) {
        this.rows = new int[n];
        this.cols = new int[n];
        this.diag = 0;
        this.antidiag = 0;
        this.n = n;
    }
    
    public int move(int row, int col, int player) {
        int sign = (player == 1) ? 1 : -1;
        rows[row] += sign;
        cols[col] += sign;
        if(row == col) diag += sign;
        if(row + col == n - 1) antidiag += sign;

        if(Math.abs(rows[row]) == 3 || 
        Math.abs(cols[col]) == 3 || 
        Math.abs(diag) == 3 || 
        Math.abs(antidiag) == 3) return player;

        return 0;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(3);
        System.out.println(ticTacToe.move(0, 0, 1)); 
        System.out.println(ticTacToe.move(0, 2, 2)); 
        System.out.println(ticTacToe.move(2, 2, 1)); 
        System.out.println(ticTacToe.move(1, 1, 2)); 
        System.out.println(ticTacToe.move(2, 0, 1)); 
        System.out.println(ticTacToe.move(2, 1, 1)); 
    }
}