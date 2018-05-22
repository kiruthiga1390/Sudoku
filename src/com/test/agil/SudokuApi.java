package com.test.agil;

import java.util.HashMap;

public class SudokuApi {
	private HashMap<String, Sudoku> sudokuMap = new HashMap<String, Sudoku>();
	private String response = null;

	// Creates a new sudoku game base on given modes(easy/hard.. etc)
	// returns the gameID associated with the created sudoku board
	// any further interactions should be done by passing the gameID
	public String createNewGame(String mode) {
		String gameId = Long.toString(System.currentTimeMillis());
		Sudoku sudoku = new Sudoku();
		sudoku.build(mode);
		sudokuMap.put(gameId, sudoku);
		response = gameId.toString();
		return response;
	}

	// Returns a string representation of a 9x9 matrix
	// On error null
	public String getAllValues(String gameId) {
		Sudoku sudoku = sudokuMap.get(gameId);
		if (sudoku == null) {
			response = null;
			return response;
		}
		response = sudoku.printSudoku();
		return response;
	}

	// Set a value in the given cell for the game
	// cell is referred buy row and column number starting from 1 to 9
	// On successfully setting the value returns "success" else returns
	// invalidity reason
	public String setValue(String gameId, int i, int j, int value) {
		Sudoku sudoku = sudokuMap.get(gameId);
		if (sudoku == null) {
			response = "Game doesn't exists";
			return response;
		}
		response = sudoku.set(i - 1, j - 1, value);
		return response;
	}

	// return a value between -1 to 9
	// -1 => either game doesn't exists or index is not between 1 to 9
	// 0 => value not yet set
	// 1 - 9 =>the actual set value;
	public String getValue(String gameId, int i, int j) {
		Sudoku sudoku = sudokuMap.get(gameId);
		if (sudoku == null) {
			response = "-1";
			return response;
		}
		response = sudoku.get(i - 1, j - 1) + "";
		return response;
	}

	// returns true if all the values are set
	// else false
	public String isGameCompleted(String gameId) {
		Sudoku sudoku = sudokuMap.get(gameId);
		if (sudoku == null) {
			response = "false";
			return response;
		}
		response = sudoku.isGameOver() + "";
		return response;
	}

}
