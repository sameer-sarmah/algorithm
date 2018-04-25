package backtracking;

import java.util.Arrays;

public class NQueenRowWise {
    int N = 4;
    int[][] board = new int[N][N];

    public static void main(String [] a)
    {
        NQueenRowWise nq=new NQueenRowWise();
        if(nq.placeNQueen(0)==true)
            nq.display();
        
    }
    
    
    private boolean isSafe(int row, int col) {
        if (row >= 0 && row < N && col >= 0 && col < N)
            return true;
        else
            return false;

    }

    private boolean canPlaceQueen(int row, int col) {

        for (int i = 0; i < N; i++) {
            if (board[row][i] == 1)
                return false;
        }

        for (int i = 0; i <= row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        int i = row;
        int j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 1)
                return false;
            --i;
            --j;
        }
        i = row;
        j = col;
        while (i >= 0 && j < N) {
            if (board[i][j] == 1)
                return false;
            --i;
            ++j;
        }

        return true;

    }

    public void display() {
        for (int i = 0; i < board.length; i++)
            System.out.println(Arrays.toString(board[i]));
    }

    public boolean placeNQueen(int row) {

        if (row == N)
            return true;
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col) && canPlaceQueen(row, col)) {
                board[row][col] = 1;
                if (placeNQueen(row + 1) == true)
                    return true;
                board[row][col] = 0;

            }

        }

        return false;
    }

}
