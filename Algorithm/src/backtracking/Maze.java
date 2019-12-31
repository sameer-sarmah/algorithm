package backtracking;

import java.awt.Point;
import java.util.Stack;

public class Maze {
	// find a path along from source to target traversing only through 1s
	int[][] maze = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1 } };
	Point start = new Point(0, 0);
	Point end = new Point(maze.length - 1, maze.length - 1);
	Stack<Point> stack = new Stack<Point>();

	public static void main(String[] args) {
		Maze m = new Maze();
		if (m.traverse(m.start.x, m.start.y) == true)
			System.out.println(m.stack);
		else
			System.out.println("No solution");
	}

	private boolean isValid(int row, int col) {
		if (row >= 0 && row < maze.length && col >= 0 && col < maze[row].length)
			return true;
		else
			return false;
	}

	public boolean traverse(int row, int col) {
		// traverse across a row,then traverse down a row ,then go diagonally
		stack.add(new Point(row, col));
		if (end.x == row && end.y == col)
			return true;
		if (isValid(row, col)) {
			if (maze[row][col + 1] == 1 && traverse(row, col + 1) == true)
				return true;
			if (maze[row + 1][col] == 1 && traverse(row + 1, col) == true)
				return true;
			if (maze[row + 1][col + 1] == 1 && traverse(row + 1, col + 1) == true)
				return true;
		}
		stack.pop();
		return false;
	}

}
