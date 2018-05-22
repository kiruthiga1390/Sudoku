# Sudoku
Sudoku is a well known game played.In sudoku every row should consists of number 1 to 9 and every column should
contain numbers 1 to 9 and every block (3*3) should contain the numbers 1 to 9 without repetition.

## Algorithm
1.For checking if every row has numbers 1 to 9 without duplicate isrowvalid() method is used. 
2.For checking if every column has numbers 1 to 9 without duplicate iscolvalid() method is used. 
3.For checking every block (3*3) has numbers 1 to 9 without duplicate isblockvalid() method is used.
4.A recursive call is used which will check if possible number is found if not it will go to the previous call and change
with other possible number so whole (9*9) is solved.

## Files
1.A two dimensional array (9*9) is used for storing the values.
2.The program is initialised by building a sudoku of given mode and returns gameId.
3.A game object is created and maintained for every puzzle. Based on gameId and index(row,column) it can be edited.
4.Methods of API Exposed:
	1.createNewGame(String mode): creates sudoku game with the given mode(easy,medium,hard,expert) and returns gameId which can be used to interact further.
	2.getAllValues(String gameId): For the given gameId this method will return the sudoku board as string.
	3.setValue(String gameId, int i, int j, int value): For the given cell index(i,j), if  the value is valid it will be set else returns the reason for not setting.
	4.getValue(String gameId, int i, int j): For the given cell index it will return the value. (0= not set, -1= invalid index, 1-9 = set value).
	5.isGameCompleted(String gameId):If the game is completed returns true else returns false.

5.when user tries to set value to a cell it is validated using the following methods isrowvalid,iscolvalid,isblockvalid.
6.we can call create and solve functions to test if the API works.

## How To execute
Run the SudokuApi.java with three methods Creategame, Getvalue (of particular cell), SetValue(of particular cell), Solvegame. 