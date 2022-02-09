package Backtracking;

public class Project3 {
    private int T_board;
    private int rowQueen = 0;
    private int columnQueen = 0;
    boolean[][] solution;
    boolean result = false;

    public Project3(int dimension, int row, int column) {
        this.T_board = dimension;
        this.rowQueen = row;
        this.columnQueen = column;
    }

    public boolean[][] findSolution() {
        Board board = new Board(T_board);
        board.setSquare(this.rowQueen, this.columnQueen);
        if (!results(board, 0)) {
            System.out.println("No solution found! ");
        }
        return solution;
    }

    private boolean results(Board board, int column) {
        if (columnQueen == 0 && column == columnQueen) {
            column++;
        }

        for (int i = 0; i < T_board && !result; i++) {
            if (isSafe(board, i, column)) {
                board.setSquare(i, column);
                if (column < T_board - 1) {
                    if ((column + 1) == columnQueen && columnQueen < (T_board - 1)) {
                        result = results(board, column + 2);
                    } else if ((column + 1) == columnQueen && columnQueen == (T_board - 1)) {
                        result = true;
                        solution = board.getMatrix();
                    } else {
                        result = results(board, column + 1);
                    }
                    if (!result) {
                        board.clearSquare(i, column);
                    }
                } else {
                    result = true;
                    solution = board.getMatrix();
                }
            }
        }
        return result;
    }

    private boolean isSafe(Board board, int row, int column) {
        int i, j;
        //Check to the left of the row
        for (i = 0; i < column; i++) {
            if (board.getSquare(row, i)) {
                return false;
            }
        }
        //Check to the right of the row
        for (i = column; i < T_board; i++) {
            if (board.getSquare(row, i)) {
                return false;
            }
        }

        //Check the square
        for (i = 0; i < row; i++) {
            if (board.getSquare(i, column)) {
                return false;
            }
        }
        //Check below the square
        for (i = row; i < T_board; i++) {
            if (board.getSquare(i, column)) {
                return false;
            }
        }

        //Check the upper left diagonal
        for (i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (board.getSquare(i, j)) {
                return false;
            }
        }
        //Check the lower left diagonal
        for (i = row, j = column; j >= 0 && i < T_board; i++, j--) {
            if (board.getSquare(i, j)) {
                return false;
            }
        }

        //Check the upper right diagonal
        for (i = row, j = column; i >= 0 && j < T_board; i--, j++) {
            if (board.getSquare(i, j)) {
                return false;
            }
        }
        //Check the lower right diagonal
        for (i = row, j = column; j < T_board && i < T_board; i++, j++) {
            if (board.getSquare(i, j)) {
                return false;
            }
        }
        return true;
    }
}
