package Backtracking;

public class Board {

    public int size_board;
    public boolean[][] board;

    public Board(int size_board) {
        this.size_board = size_board;
        board = new boolean[this.size_board][this.size_board];
    }

    public void setSquare(int i, int j) {
        board[i][j] = true;
    }

    public void clearSquare(int i, int j) {
        board[i][j] = false;
    }

    public boolean getSquare(int i, int j) {
        return board[i][j];
    }

    public boolean[][] getMatrix() {
        return copyMatrix(board);
    }

    private boolean[][] copyMatrix(boolean[][] origin) {
        boolean[][] assign = new boolean[origin.length][origin[1].length];
        for (int i = 0; i < origin.length; i++) {
            for (int k = 0; k < origin[1].length; k++) {
                assign[i][k] = origin[i][k];
            }
        }
        return assign;
    }
}
