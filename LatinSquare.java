import java.util.Arrays;

public class LatinSquare {
	private int size;
	private int[][] square;
	
	public LatinSquare(int size) {
		this.size = size;
		this.square = new int[this.size][this.size];
	}
	
	public int getCell(int r, int c) {
		return this.square[r][c];
	}
	
	public void setCell(int r, int c, int val) {
		this.square[r][c] = val;
	}
	
	public IncidenceCube toIncidenceCube() {
		IncidenceCube ic = new IncidenceCube(this.size);
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size; j++) {
				ic.cube[i][j][this.square[i][j]] = 1;
			}
		}

		return ic;
	}
	
	public static LatinSquare generateDefaultLatinSquare(int size) {
		LatinSquare ls = new LatinSquare(size);
		for (int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				ls.setCell(x, y, (y + x) % size);
			}
		}
		return ls;
	}
	
	public static LatinSquare generateLatinSquare(int size) {
		LatinSquare ls = LatinSquare.generateDefaultLatinSquare(size);
		IncidenceCube ic = ls.toIncidenceCube();
		ic.shuffle(size*size*size);
		return ic.toLatinSquare();
	}

	public int[][] getSquare() {
		return square;
	}

	public void setSquare(int[][] square) {
		this.square = square;
	}
	
	public void setNumberSudoku(int n) {
		for(int row = 0; row < this.size; row++) {
			for(int col = 0; col < this.size; col++) {
				this.square[row][col] += (3 * n) + 1;
			}
		}
	}
}
