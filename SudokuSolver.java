public class SudokuSolver {
	private int[][][] grid;
	
	public SudokuSolver() {
		this.grid  = new int[3][3][3];
	}

	public int[][][] getGrid() {
		return grid;
	}

	public void setGrid(int[][][] grid) {
		this.grid = grid;
	}

	public void createSolution() {
		int[][] form = LatinSquare.generateLatinSquare(3).getSquare();
		for(int i = 0; i < 3; i++) {
			LatinSquare ls1 = LatinSquare.generateLatinSquare(3);
			ls1.setNumberSudoku(form[i][0]);
			int[][] block1 = ls1.getSquare();
			LatinSquare ls2 = LatinSquare.generateLatinSquare(3);
			ls2.setNumberSudoku(form[i][1]);
			int[][] block2 = ls2.getSquare();
			LatinSquare ls3 = LatinSquare.generateLatinSquare(3);
			ls3.setNumberSudoku(form[i][2]);
			int[][] block3 = ls3.getSquare();
			this.grid[i] = append(block1, block2, block3);
		}
	}
	
	public static int[][] append(int[][]... matrices) {
		int size = matrices.length;
		int rows = matrices[0].length;
		int cols = matrices[0][0].length;
		int[][] result = new int[rows][cols * size];
		for(int i = 0; i < rows; ++i) {
			for(int j = 0, k = 0; j < size; j++, k += cols) {
				System.arraycopy(matrices[j][i], 0, result[i], k, cols);
			}
		}
		return result;
	}
}
