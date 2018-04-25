package algorithm.backtracking;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class KnightTour {

    int N = 8;
    int[][] board = new int[N][N];
    List<Point> list = new ArrayList<>();

    public boolean knightTour(int[][] board, int row, int col) {
        Point p = null;
        if(allCellsToured())
            return true;
        else if (!(0 <= row && row < 8) || !(0 <= col && col < 8) || board[row][col] == 1) {
            return false;
        } else {
            board[row][col] = 1;
            p = new Point(row, col);
            list.add(p);
            if (knightTour(board, row + 2, col + 1))
                return true;
            if (knightTour(board, row + 2, col - 1))
                return true;
            if (knightTour(board, row - 2, col + 1))
                return true;
            if (knightTour(board, row - 2, col - 1))
                return true;
            if (knightTour(board, row - 1, col + 2))
                return true;
            if (knightTour(board, row - 1, col - 2))
                return true;
            if (knightTour(board, row + 1, col + 2))
                return true;
            if (knightTour(board, row + 1, col - 2))
                return true;
            
            list.remove(p);
            board[row][col] = 0;
            return false;
        }
        
    }
    
    private boolean allCellsToured()
    {
        for(int row=0;row<board.length;row++)
        {
            for(int col=0;col<board[row].length;col++)
            {
                if(board[row][col]==0)
                    return false;
            }
        }
        
        return true;
    }
    
    public static void main(String [] a)
    {
        KnightTour kt=new KnightTour();
        if(kt.knightTour(kt.board,0,0))
           System.out.println(kt.list);
        else
            System.out.println("No solution");
    }
}
