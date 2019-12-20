import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
	public static void main( String args[]) {
		Scanner scanner = new Scanner (System.in);
		System.out.println ("Enter the number of Rows : ");
		int rows = scanner.nextInt();
		System.out.println ("Enter the number of Columns : ");
		int columns = scanner.nextInt();
		
		Cell[][] cell = new Cell [rows][columns];
		int value = 0;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {				
				if (i == 0 && j == 0) {
					cell[i][j] = new Cell (value, false, true);
				}
				else if (i == rows - 1 && j == columns - 1) {
					cell[i][j] = new Cell (value, true, false);
				}
				else {
					cell[i][j] = new Cell (value, true, true);
				}		
				value++;					
			}
		}
	
		if ( rows == 1 && columns == 1) {
			cell[0][0] = new Cell (value, false, false);
		}
		int numberOfSets = rows * columns; 
		DisjointSets sets = new DisjointSets(numberOfSets);
		
		List<AdjacentCells> adjLst = new ArrayList<AdjacentCells>();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				AdjacentCells adjCell = new AdjacentCells();
				if (i <= rows - 2 && j <= columns - 2) {
					adjCell.setPresentCell(cell[i][j].value);
					adjCell.setCellOnRight(cell[i][j + 1].value);
					adjCell.setCellOnBottom(cell[i + 1][j].value);
					adjLst.add(adjCell);
				}
				else if (i == rows - 1 && j <= columns - 2) {
					adjCell.setPresentCell(cell[i][j].value);
					adjCell.setCellOnRight(cell[i][j + 1].value);
					adjCell.setCellOnBottom(-1);
					adjLst.add(adjCell);					
				}
				else if (i <= rows - 2 && j == columns - 1) {
					adjCell.setPresentCell(cell[i][j].value);
					adjCell.setCellOnBottom(cell[i + 1][j].value);
					adjCell.setCellOnRight(-1);
					adjLst.add(adjCell);
				}
				else if (i == rows - 1 && j == columns - 1) {
					adjCell.setPresentCell(cell[i][j].value);
					adjCell.setCellOnBottom(-1);
					adjCell.setCellOnRight(-1);
					adjLst.add(adjCell);
				}
			}
		}
		
		Random random = new Random();
		while (isNotSameSet(sets, numberOfSets)) {
			int currCell = -1;
			int rCell = -1;
			int bCell = -1;
			int randomCell = random.nextInt(adjLst.size());
			currCell = adjLst.get(randomCell).getPresentCell();
			rCell = adjLst.get(randomCell).getCellOnRight();
			bCell = adjLst.get(randomCell).getCellOnBottom();
			int[] arr = new int[2];
			arr[0] = rCell;
			arr[1] = bCell;
			int rnd = random.nextInt(arr.length);
		    int set1, set2;
		    set1 = currCell;
		    int wall;		    
		    if (arr[rnd] == -1) {
		    	if(arr[rnd] == rCell) {
		    		arr[rnd] = bCell;
		    		set2 = bCell;		    		
		    	}
		    	else {
		    		arr[rnd] = rCell;		    	
		    		set2 = rCell;		    		
		    	}
		    }
		    else 
		    	set2 = arr[rnd];
		    if (set2 == rCell) 
		    	wall = 1;
		    else
		    	wall = 2;
			if (rCell == -1 && bCell == -1) {
				set2 = (rows * columns) - 1;
			}
		    if(sets.find(set1) != sets.find(set2)) {
				sets.union(sets.find(set1), sets.find(set2));
				adjLst.remove(randomCell);
				int r, c;
				if(wall == 1) {	
					r = (currCell + 1) /columns;
					c = (currCell + 1) - r * columns;
					cell[r][c].setWallLeft(false);
				}
				if(wall == 2) {
					r = (currCell) /columns;
					c = (currCell) - r * columns;
					cell[r][c].setWallBottom(false);
				}
			}
			
		}
		for(int k = 0; k < columns; k++) {
			System.out.print(" _");
		}
		System.out.println();
		for (int i =0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (cell[i][j].wallLeft) 
					System.out.print("|");
				else 
					System.out.print(" ");				
				if (cell[i][j].wallBottom) 
					System.out.print("_");
				else 
					System.out.print(" ");
			
			}
			System.out.print("|");
			System.out.println();
		}
		scanner.close();
	}
	
	private static boolean isNotSameSet (DisjointSets sets, int numSets) {
		for(int i = 1; i < numSets; i++){
			if(sets.find(i) != sets.find(0)){
				return true;
			}
		}
		return false;
	}
}