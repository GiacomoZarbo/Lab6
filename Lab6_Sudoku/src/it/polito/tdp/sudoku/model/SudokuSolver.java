package it.polito.tdp.sudoku.model;

public class SudokuSolver {

	public int[][] risolviSudoku(int[][] matrix) {
		return risolvi_ric(matrix, 0);
	}

	private int[][] risolvi_ric(int[][] matrix, int level) {
		if (level == 81) {
			return matrix;
		}
		int riga = level / 9;
		int colonna = level % 9;
		if (matrix[riga][colonna] == 0) {
			for (int k = 1; k <= 9; k++) {
				if (valida(matrix, riga, colonna, k)) {
					matrix[riga][colonna] = k;
					int[][] retMatrix = risolvi_ric(matrix, level + 1);
					if (retMatrix != null)
						return retMatrix;
				}
				matrix[riga][colonna] = 0;
			}
		} else {
			int[][] retMatrix = risolvi_ric(matrix, level + 1);
			if (retMatrix != null)
				return retMatrix;
		}
		return null;
	}

	private boolean valida(int[][] matrix, int x, int y, int k) {
		// check row
		for (int j = 0; j < 9; j++) {
			if (matrix[x][j] != 0 && matrix[x][j] == k)
				return false;
		}
		// check column
		for (int i = 0; i < 9; i++) {
			if (matrix[i][y] != 0 && matrix[i][y] == k)
				return false;
		}

		int r = x - x % 3;
		int c = y - y % 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matrix[i + r][j + c] != 0 && matrix[i + r][j + c] == k)
					return false;
			}
		}
		return true;
	}

}
