package com.test.agil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sudoku {
	List<Integer> POSSIBLE_NUMBERS = new ArrayList<Integer>(Arrays.asList(1, 2,
	    3, 4, 5, 6, 7, 8, 9));
	private int[][] sudoku = new int[9][9];

	/*
	 * ===== public methods======
	 */
	public void build(String mode) {
		solveSudoku(0, 0);
		filterByGameMode(mode);
	}

	public int get(int i, int j) {
		if (checkvalidposition(i, j)) {
			return sudoku[i][j];
		} else {
			return -1;
		}
	}

	public String set(int i, int j, int value) {
		if (checkvalidposition(i, j)) {
			if (setpossibleNumber(i, j, value)) {
				return "success";
			} else {
				return "not valid";
			}
		}
		return "Invalid location";
	}

	// Since sudoku can never be set to invalid state using the exposed functions,
	// it is is enough to check whether all fields are set.
	public boolean isGameOver() {
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[0].length; j++) {
				if (sudoku[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public String printSudoku() {
		return Arrays.deepToString(sudoku);
	}

	/*
	 * ===== private functions ======
	 */
	private boolean isValid(int i, int j, int value) {
		if ((value < 1) || (value > 9)) {
			return false;
		}
		return (sudoku[i][j] == 0) && isrowpossible(i, value)
		    && iscolpossible(j, value) && isblockpossible(i, j, value);
	}

	private boolean iscolpossible(int j, int value) {
		for (int i = 0; i < sudoku.length; i++) {
			if (sudoku[i][j] == value) {
				return false;
			}
		}
		return true;
	}

	private boolean isrowpossible(int i, int value) {
		for (int j = 0; j < sudoku[0].length; j++) {
			if (sudoku[i][j] == value) {
				return false;
			}
		}
		return true;
	}

	private boolean isblockpossible(int i, int j, int value) {
		int istart = i - i % 3;
		int jstart = j - j % 3;
		for (int k = istart; k <= istart + 2; k++) {
			for (int l = jstart; l <= jstart + 2; l++) {
				if (!(k == i && l == j)) {
					if (sudoku[k][l] == value) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private boolean checkvalidposition(int i, int j) {
		if ((i >= 0 && i < 9) && (j >= 0 && j < 9)) {
			return true;
		}
		return false;
	}

	private boolean setpossibleNumber(int i, int j, int value) {
		if (isValid(i, j, value)) {
			sudoku[i][j] = value;
			return true;
		}
		return false;
	}

	private boolean solveSudoku(int i, int j) {
		Collections.shuffle(POSSIBLE_NUMBERS);
		if (sudoku[i][j] != 0) {
			int nexti = 0;
			int nextj = 0;
			if (j == 8) {
				if (i == 8) { // game completed
					return true;
				} else {
					nexti = i + 1;
					nextj = 0;
				}
			} else {
				nexti = i;
				nextj = j + 1;
			}
			return solveSudoku(nexti, nextj);

		} else {
			for (int number : POSSIBLE_NUMBERS) {

				if (isValid(i, j, number)) {
					sudoku[i][j] = number;
					int nexti = 0;
					int nextj = 0;
					if (j == 8) {
						if (i == 8) { // game completed
							return true;
						} else {
							nexti = i + 1;
							nextj = 0;
						}
					} else {
						nexti = i;
						nextj = j + 1;
					}
					if (solveSudoku(nexti, nextj)) {
						return true;
					}// else try next number
					sudoku[i][j] = 0;
				}
			}
			return false;
		}
	}

	private void filterByGameMode(String mode) {
		int num = 0;
		int l = 0;
		if (mode.equalsIgnoreCase("easy")) {
			num = 20;
		} else if (mode.equalsIgnoreCase("medium")) {
			num = 25;
		} else if (mode.equalsIgnoreCase("hard")) {
			num = 35;
		} else if (mode.equalsIgnoreCase("expert")) {
			num = 40;
		}
		Random random = new Random();
		while (l < num) {
			int k = random.nextInt(81);
			int i = k % 9;
			int j = k / 9;
			if (sudoku[i][j] != 0) {
				sudoku[i][j] = 0;
				l++;
			}
		}
	}
}
