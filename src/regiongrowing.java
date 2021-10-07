/*
 Name: Daniel Peters
 Class: Csci 433, Yixin Chen
 Honor Code: "I PLEDGE MYSELF TO UPHOLD THE HIGHEST STANDARDS OF HONESTY IN MY UNIVERSITY LIFE AND I WILL NOT TOLERATE DISHONESTY ON THE PART OF OTHERS."
 Date: February 9, 2021
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class regiongrowing {
	
	
	// These 3 global variables are used in the recursive implementation of the algorithm. I commented these out,
	// along with the recursive algorithm, in order to provide a better visual representation of my thought process
	// for solving this assignment.
	
/*	
	private ArrayList<Integer> sizes = new ArrayList<Integer>();
	private static int currRegion = 0;
	private static int depth = 0;
*/	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Please enter the PGM file you want to use the Region Growing algorithm on: ");
		Scanner keyboard = new Scanner(System.in);
		
		String fileName = keyboard.nextLine();
		
		File file = new File(fileName);
		
		Scanner scnr;
		
//****************** SECTION #1 ****************** This section creates the matrix from the specified PGM file and also takes user input for the specified gray-value	
		
		try {
			scnr = new Scanner(file);
		}
		catch(FileNotFoundException e){
			throw new FileNotFoundException("Error! This file was not found!");
		}
		
		
		String[] format = scnr.nextLine().split(" ");
		
		int width = Integer.parseInt(format[1]);
		int height = Integer.parseInt(format[2]);
		
		ArrayList<Integer> data = new ArrayList<Integer>();
		
		while(scnr.hasNextLine()) {
			String[] currLine = scnr.nextLine().split(" ");
			for(int i = 0; i < currLine.length; i++) {
				data.add(Integer.parseInt(currLine[i]));
			}
		}
		
		scnr.close();
		
		int[][] matrix = new int[height][width];
		
		// Matrix is in height x width form
		
		int dataCounter = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				matrix[i][j] = data.get(dataCounter);
				dataCounter++;
			}
		}
			
		System.out.print("Please enter the gray-scale value of the region of interest (either 0 or 255): ");
		int gs = keyboard.nextInt();
		System.out.println();
		
		while(gs != 0 && gs != 255) {
			System.out.println("Invalid input. Please enter the gray-scale value of the region of interest (either 0 or 255): ");
			gs = keyboard.nextInt();
			System.out.println();
		}
		
//****************** END OF SECTION #1 ******************			
		

		
		
		
		
//****************** SECTION #2 ****************** This section uses for-loops, a while-loop, and if-statements to mimic a recursive algorithm in order to overcome stack overflow
		// Please see submitted report for detailed explanation of this algorithm.
		// Summary: Keeps track of seed pixel(s) through stacks. Adds pixels to region until no more matching neighbor pixels.
		
		ArrayList<Integer> regionSizes = new ArrayList<Integer>();
		
		Stack<Integer> rows = new Stack<Integer>();
		Stack<Integer> cols = new Stack<Integer>();
		
		int itRegions = 0;
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(matrix[i][j] == gs) {
					rows.push(i);
					cols.push(j);
					itRegions++;
					
					while(!rows.isEmpty()) {
						int popR = rows.pop();
						int popC = cols.pop();
						matrix[i][j] = -1;
						
						int[] rNbrs = {popR + 1, popR + 1, popR, popR - 1, popR - 1, popR - 1, popR, popR + 1};
						int[] cNbrs = {popC, popC + 1, popC + 1, popC + 1, popC, popC - 1, popC - 1, popC - 1};
						
						for(int k = 0; k < 8; k++) {
							if(rNbrs[k] < 0 || rNbrs[k] > height - 1 || cNbrs[k] < 0 || cNbrs[k] > width - 1) {
								continue;
							}
							else if(matrix[rNbrs[k]][cNbrs[k]] == gs) {
								matrix[rNbrs[k]][cNbrs[k]] = -1;
								rows.push(rNbrs[k]);
								cols.push(cNbrs[k]);
								itRegions++;
							}
						
							
							
						}
						
						
					
					}
					regionSizes.add(itRegions);
					itRegions = 0;
				}
				
			}
		}
		
//****************** END OF SECTION #2 ******************		
		
		
		
		
		
		
//****************** SECTION #3 ****************** This section uses a simple sort to place the region sizes in ascending order. It also prints the student's name, file name,
		// the number of regions, and the size of each region.
		
		Collections.sort(regionSizes);
		
		System.out.println();
		System.out.println("Daniel Peters");
		System.out.println(fileName);
		System.out.print(regionSizes.size() + ": ");
		for(int i = 0; i < regionSizes.size(); i++) {
			System.out.print(regionSizes.get(i) + ", ");
		}
	}
		
//****************** END OF SECTION #3 ******************		
		

	
	
	
	
	
	
	
	
	
	
	
//****************** SECTION #4 ****************** This section contains the recursive algorithm that was first used to solve this assignment. It provided a starting point
	// for the student to make the iterative solution easier to code. I am commenting this section of code out, rather than removing it entirely, so that my thought process
	// can be better understood. It functions very similarly to section 3, with a few slight differences.
	
/*	
 		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(matrix[i][j] == gs) {
					
					matrix = check(matrix, height, width, i, j, gs);
					regionSizes.add(currRegion);
					currRegion = 0;
				}
				else if(matrix[i][j] == -1) {
					continue;
				}
			}
		}
				
*/
		
		
	
	
	
	
/*
	
	public static int[][] check(int[][] matrix, int height, int width, int row, int col, int gs) {
		
		matrix[row][col] = -1;
		currRegion++;
		
		int[] rowNH = {row + 1, row + 1, row, row - 1, row - 1, row - 1, row, row + 1};
		int[] colNH = {col, col + 1, col + 1, col + 1, col, col - 1, col -1, col -1};
		
		for(int i = 0; i < 8; i++) {
			if(rowNH[i] < 0 || rowNH[i] > height - 1 || colNH[i] < 0 || colNH[i] > width - 1) {
				continue;
			}
			else {
				if(i == 7 && matrix[rowNH[i]][colNH[i]] != gs) {
					return matrix;
				}
				
				else if(matrix[rowNH[i]][colNH[i]] == gs) {
					matrix = check(matrix, height, width, rowNH[i], colNH[i], gs);
				}
			}
		}
		
		return matrix;
	}
	
*/

//****************** END OF SECTION #4 ******************
	
	
}
