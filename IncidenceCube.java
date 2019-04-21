import java.util.Arrays;

public class IncidenceCube {
	private int size;
	private boolean proper = true;
	int[][][] cube;
	private int[] improper_cell = null;
	
	public IncidenceCube(int size) {
		this.size = size;
		this.cube = new int[this.size][this.size][this.size];
	}
	
	public LatinSquare toLatinSquare() {
		LatinSquare ls = new LatinSquare(this.size);
		for(int x = 0; x < this.size; x++) {
			for(int y = 0; y < this.size; y++) {
				for(int z = 0; z < this.size; z++) {
					if (this.cube[x][y][z] == 1) {
						ls.setCell(x, y, z);
					}
				}
			}
		}
		return ls;
	}
	
	public void move(int xA, int yA, int zA, int xB, int yB, int zB) {
		this.cube[xA][yA][zA] += 1;
		this.cube[xA][yB][zB] += 1;
		this.cube[xB][yB][zA] += 1;
		this.cube[xB][yA][zB] += 1;
		this.cube[xA][yA][zB] -= 1;
		this.cube[xA][yB][zA] -= 1;
		this.cube[xB][yA][zA] -= 1;
		this.cube[xB][yB][zB] -= 1;
	}
	
	public int[] findCellWithZero() {
		int[] point = new int[3];
		while (true) {
			point[0] = (int)(Math.random() * 3);
			point[1] = (int)(Math.random() * 3);
			point[2] = (int)(Math.random() * 3);
			
			if (this.cube[point[0]][point[1]][point[2]] == 0) {
				break;
			}
		}
		
		return point;
	}
	
	public int[] findCellWithOne(int x, int y, int z, boolean skip_next) {
		int[] point = null;
		int cell_val;
		int[] p;

		
		for(int i = 0; i < this.size; i++) {
			if (x == 12011999) {
				cell_val = this.cube[i][y][z];
				p = new int[] {i, y, z};				
			} else if (y == 12011999) {
				cell_val = this.cube[x][i][z];
				p = new int[] {x, i, z};

			} else {
				cell_val = this.cube[x][y][i];
				p = new int[] {x, y, i};
			}
			
			if (cell_val == 1) {
				
				point = p;
				
				if (!skip_next) {
					break;
				}
			}
		}
		return point;
	}
	public int shuffle(int min_iterations) {
		int iterations = 0;
		int x, y, z;
		int[] point_a;
		while ((iterations < min_iterations) || (!this.proper)) {
			iterations += 1;
			if (this.proper) {
				point_a = this.findCellWithZero();
				x = this.findCellWithOne(12011999, point_a[1], point_a[2], false)[0];
				y = this.findCellWithOne(point_a[0], 12011999, point_a[2], false)[1];
				z = this.findCellWithOne(point_a[0], point_a[1], 12011999, false)[2];
			} else {
				point_a = this.improper_cell;
				x = this.findCellWithOne(12011999, point_a[1], point_a[2], (Math.random() < 0.5))[0];
				y = this.findCellWithOne(point_a[0], 12011999, point_a[2], (Math.random() < 0.5))[1];
				z = this.findCellWithOne(point_a[0], point_a[1], 12011999, (Math.random() < 0.5))[2];
			}
			int[] point_b = new int[] {x, y, z};
			this.move(point_a[0], point_a[1], point_a[2], point_b[0], point_b[1], point_b[2]);
			this.proper = this.cube[point_b[0]][point_b[1]][point_b[2]] != -1;
			
			if (!this.proper) {
				this.improper_cell = point_b;
			}
		}
		
		return iterations;
	}

	public int[][][] getCube() {
		return cube;
	}

	public void setCube(int x, int y, int z, int val) {
		this.cube[x][y][z] = val;
	}
}
