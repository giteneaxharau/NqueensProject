package Backtracking;

import java.util.ArrayList;

public class Project4 {
    private int T_board;
    private ArrayList<boolean[][]> solutions = new ArrayList<>();

    public Project4(int dimension) {
        this.T_board = dimension;
    }

    public ArrayList<boolean[][]> findSolution() {
        Board board = new Board(T_board);
        results(board, 0);
        if (solutions.isEmpty()) {
            System.out.println("No solution found! ");
        }
        return solutions;
    }

    private void results(Board board, int column) {
        if (column == T_board) {
            solutions.add(board.getMatrix());
            return;
        }
        for (int i = 0; i < T_board; i++) {
            if (isSafe(board, i, column)) {
                board.setSquare(i, column);
                results(board, column + 1);
                board.clearSquare(i, column);
            }
        }
    }

    private boolean isSafe(Board board, int row, int column) {
        int i, j;
        for (i = 0; i < column; i++) {
            if (board.getSquare(row, i)) {
                return false;
            }
        }
        for (i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (board.getSquare(i, j)) {
                return false;
            }
        }

        for (i = row, j = column; j >= 0 && i < T_board; i++, j--) {
            if (board.getSquare(i, j)) {
                return false;
            }
        }
        return true;
    }
}

