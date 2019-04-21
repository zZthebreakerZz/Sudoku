import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class SudokuGenerator {
    
	public static void writeFile(String output_name, int[][][] dataFile) {
		try{
			Writer fileOut = new FileWriter(output_name);
			for(int[][] data: dataFile ) {
				for(int[] d: data) {
					for(int n: d) {
						fileOut.write(String.valueOf(n) + ' ');
					}
					fileOut.write('\n');
				}
			}
			fileOut.close();
		}catch(IOException e){    
			System.out.println(e); 
		}
	}
	
	public static int[][][] createPuzzle(int[][][] sol, int holes) {
		int[][][] puz = sol.clone();
		while (true) {
			for(int[][] data: sol ) {
				for(int row = 0; row < data.length; row++) {
					int rand = (int) (Math.random() * 9);
					if (data[row][rand] == 0) {
						continue;
					} else {
						data[row][rand] = 0;
						holes--;
					}
					if (holes == 0) {
						return puz;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String puzzleFile = args[0];
		String solutionFile = args[1];
		int nHoles = Integer.parseInt(args[2]);
		
		if (nHoles > (9*9 - 9)) {
			System.out.println("There is too much hole. Try again");
			return;
		}
		SudokuSolver solution = new SudokuSolver();
		solution.createSolution();
		writeFile(solutionFile, solution.getGrid());
		writeFile(puzzleFile, createPuzzle(solution.getGrid(), nHoles));
	}

}
