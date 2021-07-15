import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

	public static void main(String[] args) {
		// calling the method that stimulates a game of TicTacToe
		play();
	}

	// this method takes an integer n and creates/returns a square two dimensional
	// array
	// of characters based on that integer
	// this array will represent the board that is going to be modified after each
	// round
	// initially it would have a space character in each cell
	public static char[][] createBoard(int n) {
		// declaring the array and giving it a length
		char[][] board = new char[n][n];
		// filling every cell of the array with a space character
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
		return board;
	}

	// this method prints a series of "+-" depending on the size of the square that
	// is the board (int n)
	public static void printLine(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("+-");
		}
		// it also adds on a final "+" after the series of "+-" have been printed
		System.out.println("+");
	}

	// this method is going to display the content of the board that is a char two
	// dimensional array
	// on top, between, and below every array it will print a series of "+"'s and
	// "-"'s
	// this will depend on the size of the array, and is done using the printLine
	// method
	// it will also print a "|" before, after and between every cell of each sub
	// array
	public static void displayBoard(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			printLine(arr[i].length);
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print('|');
				System.out.print(arr[i][j]);
			}

			System.out.println('|');

		}
		printLine(arr[0].length);
	}

	// this method writes a character on a two dimensional array of characters, the
	// board
	// it takes as input the character that is to be added and the coordinates of
	// the desired placement
	// in this case, x indicates the row number, and y the column number on the
	// board
	public static void writeOnBoard(char[][] arr, char c, int x, int y) {
		// if those coordinates are equal or larger than the length of the array, they
		// are out of bounds
		// thus, an exception is thrown
		if (x >= arr.length || y >= arr[0].length) {
			throw new IllegalArgumentException("The coordinates received represent a cell outside of the board.");
		} else {
			for (int i = 0; i <= x; i++) {
				for (int j = 0; j <= y; j++) {
					if (i == x && j == y) {
						// since this a game of TicTacToe, only empty cells are allowed to be filled
						// thus, if the cell is not a space character, we cannot place the char c in it
						// hence, the exception usage
						if (arr[i][j] != ' ') {
							throw new IllegalArgumentException(
									"The cell contains a character other than a space character.");
						} else {
							// if the cell has a space character, then we can replace it by the desired
							// character
							arr[i][j] = c;
						}
					}
				}
			}
		}
	}

	// this method will add on the user's move to the board(the two dimensional
	// array of characters), which is taken as input
	public static void getUserMove(char[][] arr) {
		// it uses the scanner class to to get a move from the user
		Scanner read = new Scanner(System.in);
		// again, it relies on x and y coordinates, the first int is going to be the row
		// number
		// and the second one, the column number
		int xCoordinate = 0;
		int yCoordinate = 0;
		boolean valid = false;
		while (!valid) {
			try {
				// if the user's inputs are valid, then it becomes equal to the x and y
				// coordinates
				xCoordinate = read.nextInt();
				yCoordinate = read.nextInt();
				valid = true;
				// if those coordinates are larger than the array's length
				// or if the cell corresponding to them is not a space character, or if the
				// input is a negative number, the user will
				// be asked to input new coordinates
				if (arr[xCoordinate][yCoordinate] != ' ') {
					System.out.println("Guess again!");
					valid = false;
				}
			} catch (Exception e) {
				System.out.println("Guess again!");
				valid = false;
			}
		}

		// once those coordinates are valid, an 'x' character is added to the cell that
		// corresponds to them
		// this is done using the writeonBoard method
		writeOnBoard(arr, 'x', xCoordinate, yCoordinate);
	}

	// this method take as input a two dimensional array of characters and a
	// character c as input
	// it determines if someone is winning on a row
	public static boolean rowWinner(char[][] arr, char c) {
		boolean result = true;
		// it will go over all the rows of the array
		// in case there is a row that has the character c in each of its cell
		// the user with that character c is winning on that row
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != c) {
					result = false;
					// once it detects one character off, it breaks and goes on to another row
					break;
				} else
					result = true;
			}
			// if any of the ith rows has the character c in one of its cells, the method
			// returns true immediately
			// instead of checking the other rows too
			if (result) {
				return result;
			}
		}
		return result;
	}

	// this method take as input a two dimensional array of characters and a
	// character c as input
	// it determines if someone is winning on a column
	public static boolean colWinner(char[][] arr, char c) {
		// it will go over all the columns of the array
		// in case there is a row that has the character c in each of its cell
		// the user with that character c is winning on that column
		boolean result = true;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[j][i] != c) {
					result = false;
					// once it detects one character off, it breaks and goes on to another row
					break;
				} else
					result = true;
			}
			// if any of the ith columns has the character c in one of its cells, the method
			// returns true immediately
			// instead of checking the other columns too
			if (result) {
				return result;
			}
		}
		return result;
	}

	// this method take as input a two dimensional array of characters and a
	// character c as input
	// it determines if someone is winning on the diagonal
	public static boolean diagWinner(char[][] arr, char c) {
		boolean result = true;
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][j] != c) {
				result = false;
				// once it detects one character off, it breaks, thus returning false
				break;
			}
			j++;
		}
		// if all the cells of the diagonal contain the character c, it returns true
		// the user with that character c is winning on the diagonal
		return result;
	}

	// this method take as input a two dimensional array of characters and a
	// character c as input
	// it determines if someone is winning on the second diagonal
	public static boolean otherDiagWinner(char[][] arr, char c) {
		boolean result = true;
		int j = arr[0].length - 1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][j] != c) {
				result = false;
				// once it detects one character off, it breaks, thus returning false
				break;
			}
			j--;
		}
		// if all the cells of the diagonal contain the character c, it returns true
		// the user with that character c is winning on the second diagonal
		return result;
	}

	// this method takes a two dimensional array of characters and an integer x as
	// input
	// it returns an array of characters, that corresponds to the xth column of the
	// 2d array
	public static char[] column(char[][] arr, int x) {
		// declaring the array and giving it a length
		char[] temp = new char[arr[0].length];
		for (int i = 0; i < arr[0].length; i++) {
			for (int j = 0; j < arr.length; j++) {
				// copying the cells of that column into the new array
				if (i == x) {
					temp[j] = arr[j][i];
				}
			}
		}
		return temp;
	}

	// this method takes a two dimensional array of characters and an integer x
	// it returns a array of characters, that corresponds to the xth row of the 2d
	// array
	public static char[] row(char[][] arr, int x) {
		// declaring the array and giving it a length
		char[] temp = new char[arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				// copying the cells of that row into the new array
				if (i == x) {
					temp[j] = arr[i][j];
				}
			}
		}
		return temp;
	}

	// this method takes a two dimensional array of characters as input
	// it returns a array of characters, that corresponds to the diagonal of the 2d
	// array
	public static char[] diagonal(char[][] arr) {
		// declaring the array and giving it a length
		char[] temp = new char[arr[0].length];
		int j = 0;
		for (int i = 0; i < arr[0].length; i++) {
			// copying the cells of the diagonal into the new array
			temp[i] = arr[i][j];
			j++;
		}
		return temp;
	}

	// this method takes a two dimensional array of characters as input
	// it returns a array of characters, that corresponds to the second diagonal of
	// the 2d
	// array
	public static char[] otherDiagonal(char[][] arr) {
		// declaring the array and giving it a length
		char[] temp = new char[arr[0].length];
		int j = arr[0].length - 1;
		for (int i = 0; i < arr[0].length; i++) {
			// copying the cells of the second diagonal into the new array
			temp[i] = arr[i][j];
			j--;
		}
		return temp;
	}

	// this method takes an array of characters and a character as iput
	// it returns an integer - count that corresponds to the number of the character
	// c
	// that is present in the array
	public static int howManyChars(char[] arr, char c) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == c) {
				count++;
			}
		}
		return count;
	}

	// this method takes an array of characters as input
	public static int indexOfChar(char[] arr) {
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			// if the array of characters has only one cell with a space character
			// and if that the loop is iterating at that cell with the space character
			// the method will return the index which corresponds to the position in the
			// array of the cell with the space character
			if (howManyChars(arr, ' ') == 1 && arr[i] == ' ') {
				index = i;
			}
		}
		return index;
	}

	// this method takes a two dimensional array of characters and a character as
	// input
	public static boolean rowMaybeWin(char[][] arr, char c) {
		for (int i = 0; i < arr.length; i++) {
			// if the row at which the loop is iterating has only one cell with a space
			// character
			// and if all the other cells are filled with the character c but the cell with
			// the space character
			// then either the user or the AI has a possibility of winning depending on the
			// nature of the character c
			// so, it will return true if the former applies, and false otherwise
			if (howManyChars(row(arr, i), ' ') == 1 && howManyChars(row(arr, i), c) == (arr.length - 1)) {
				return true;
			}
		}
		return false;
	}

	// this method takes a two dimensional array of characters and a character as
	// input
	public static boolean colMaybeWin(char[][] arr, char c) {
		// if the column at which the loop is iterating has only one cell with a space
		// character
		// and if all the other cells are filled with the character c but the cell with
		// the space character
		// then either the user or the AI has a possibility of winning depending on the
		// nature of the character c
		// so, it will return true if the former applies, and false otherwise
		for (int i = 0; i < arr.length; i++) {
			if (howManyChars(column(arr, i), ' ') == 1 && howManyChars(column(arr, i), c) == (arr.length - 1)) {
				return true;
			}
		}
		return false;
	}

	// this method takes as input a two dimensional array of characters and
	// determines if there is an obvious move for the AI to make
	public static boolean checkForObviousMove(char[][] arr) {
		// the if statements are written in order of priority
		// the AI should always prioritize winning the game than blocking the user from
		// winning it
		// whenever one of these evaluate to true, the method terminates
		// if the diagonal has only one space character and if all of its other cells
		// are
		// filled with an 'o', then the AI is to add an 'o' to the cell with the space
		// character
		if (howManyChars(diagonal(arr), ' ') == 1 && howManyChars(diagonal(arr), 'o') == (arr.length - 1)) {
			int index = indexOfChar(diagonal(arr));
			writeOnBoard(arr, 'o', index, index);
			return true;
		}
		// if the second diagonal has only one space character and if all of its other
		// cells are
		// filled with an 'o', then the AI is to add an 'o' to the cell with the space
		// character
		if (howManyChars(otherDiagonal(arr), ' ') == 1 && howManyChars(otherDiagonal(arr), 'o') == (arr.length - 1)) {
			int index = indexOfChar(otherDiagonal(arr));
			int x = (arr.length - 1 - index);
			writeOnBoard(arr, 'o', index, x);
			return true;
		}
		// if any of the rows has only one space character and if all of its other cells
		// are
		// filled with an 'o', then the AI is to add an 'o' to the cell with the space
		// character
		if (rowMaybeWin(arr, 'o')) {
			for (int i = 0; i < arr.length; i++) {
				if (howManyChars(row(arr, i), 'o') == (arr.length - 1)) {
					int index = indexOfChar(row(arr, i));
					writeOnBoard(arr, 'o', i, index);
					return true;
				}
			}
		}
		// if any of the columns has only one space character and if all of its other
		// cells are
		// filled with an 'o', then the AI is to add an 'o' to the cell with the space
		// character
		if (colMaybeWin(arr, 'o')) {
			for (int i = 0; i < arr.length; i++) {
				if (howManyChars(column(arr, i), 'o') == (arr.length - 1)) {
					int index = indexOfChar(column(arr, i));
					writeOnBoard(arr, 'o', index, i);
					return true;
				}
			}
		}
		// if the diagonal has only one space character and if all of its other cells
		// are
		// filled with an 'x', then the AI is to add an 'o' to the cell with the space
		// character
		if (howManyChars(diagonal(arr), ' ') == 1 && howManyChars(diagonal(arr), 'x') == (arr.length - 1)) {
			int index = indexOfChar(diagonal(arr));
			writeOnBoard(arr, 'o', index, index);
			return true;
		}
		// if the second diagonal has only one space character and if all of its other
		// cells are
		// filled with an 'x', then the AI is to add an 'o' to the cell with the space
		// character
		if (howManyChars(otherDiagonal(arr), ' ') == 1 && howManyChars(otherDiagonal(arr), 'x') == (arr.length - 1)) {
			int index = indexOfChar(otherDiagonal(arr));
			int x = (arr.length - 1 - index);
			writeOnBoard(arr, 'o', index, x);
			return true;
		}
		// if any of the rows has only one space character and if all of its other cells
		// are
		// filled with an 'x', then the AI is to add an 'o' to the cell with the space
		// character
		if (rowMaybeWin(arr, 'x')) {
			for (int i = 0; i < arr.length; i++) {
				if (howManyChars(row(arr, i), 'x') == (arr.length - 1)) {
					int index = indexOfChar(row(arr, i));
					writeOnBoard(arr, 'o', i, index);
					return true;
				}
			}
		}
		// if any of the columns has only one space character and if all of its other
		// cells are
		// filled with an 'x', then the AI is to add an 'o' to the cell with the space
		// character
		if (colMaybeWin(arr, 'x')) {
			for (int i = 0; i < arr.length; i++) {
				if (howManyChars(column(arr, i), 'x') == (arr.length - 1)) {
					int index = indexOfChar(column(arr, i));
					writeOnBoard(arr, 'o', index, i);
					return true;
				}
			}
		}
		// if none of the above conditions applies, then the method returns false as
		// there is no obvious move
		return false;
	}

	// this method takes as input a two dimensional array of characters
	// it stimulates the move of the AI
	public static void getAIMove(char[][] arr) {
		// if there is an obvious move, then the AI is to make it
		if (checkForObviousMove(arr)) {

		}
		// otherwise, random numbers are generated to correspond to the x and y
		// coordinates
		else {
			Random generator = new Random();
			int x = generator.nextInt(arr.length);
			int y = generator.nextInt(arr.length);
			// if those numbers generates do no correspond to an empty cell - a cell with a
			// space character, new numbers are generated
			while (arr[x][y] != ' ') {
				x = generator.nextInt(arr.length);
				y = generator.nextInt(arr.length);
			}
			// once the coordinates are valid, the AI adds an 'o' to the cell corresponding
			// to them
			writeOnBoard(arr, 'o', x, y);
		}
	}

	// this method takes as input a two dimensional array of characters
	public static char checkForWinner(char[][] arr) {
		// it loops through the whole array and checks if any of the columns, rows,
		// diagonals have x's or o's in their respective, which is considered as a win
		// if it is the case it returns a 'x' or 'o' depending on the nature of the
		// winner
		// if not, it will return ' '
		if (rowWinner(arr, 'x') || colWinner(arr, 'x') || diagWinner(arr, 'x') || otherDiagWinner(arr, 'x')) {
			return 'x';
		} else if (rowWinner(arr, 'o') || colWinner(arr, 'o') || diagWinner(arr, 'o') || otherDiagWinner(arr, 'o')) {
			return 'o';
		} else {
			return ' ';
		}
	}

	// this method stimulates the TicTacToe game
	public static void play() {
		// it uses the scanner class to to get inputs from the user
		Scanner read = new Scanner(System.in);
		// it first asks for the name of the user, and reads their input
		System.out.println("Please enter your name:");
		String name = read.nextLine();
		// then it asks for the dimension of the desired board i.e: the two dimensional
		// array of characters
		System.out.println("Welcome, " + name + "! Are you ready to play?");
		System.out.println("Please chose the dimension of your board:");
		// declaring/initializing the dimension of the board
		int dimension = 0;
		boolean valid = false;
		while (!valid) {
			try {
				// if the user's input is valid, then it becomes equal to the dimension
				dimension = read.nextInt();
				valid = true;
				// in case the input of the user is a negative int or zero, then they are asked
				// to try another input
				if (dimension <= 0) {
					System.out.println("Wrong input. Try again,.");
					valid = false;
				}
			}
			// in case the input of the user is not an integer, they are asked to try
			// another input
			catch (Exception e) {
				System.out.println("Wrong input! Try again.");
				valid = false;
				read.next();
			}
		}
		// creating a board that has for dimension the input of the user
		char[][] board = createBoard(dimension);
		// generating 2 numbers randomly/tossing a coin to determines who starts the
		// game first
		Random toss = new Random();
		int coin = toss.nextInt(2);
		System.out.println("The result of the coin toss is: " + coin);
		// if the result of that coin tossed is 0 then the user starts
		// if it is 1 the AI starts
		if (coin == 0) {
			System.out.println("You have the first move.");
		} else {
			System.out.println("The AI has the first move.");
		}
		// this loop iterates until the maximum number of moves is reached
		// this maximum numbers corresponds to the dimension squared
		// because once reached, there are no longer any cells to add x's and o's to
		for (int i = 1; i <= dimension * dimension; i++) {
			if (coin == 0) {
				// if the user starts first, then he can play at every odd of the loop
				if (i % 2 != 0) {
					System.out.println("Please enter your move:");
					// the user is asked to input coordinates, a 'x' is then added to cell that
					// corresponds to them
					getUserMove(board);
					// the board is then displayed, it will contain the move of the user and any
					// other previous moves
					displayBoard(board);
					// every time the user plays, it checks if he won or not
					// if it is the case, it terminates the loop, and announces the winner
					if (checkForWinner(board) == 'x') {
						System.out.println("GAME OVER");
						System.out.println("You won!");
						break;
					}
				}
				// and the AI plays at every even iteration of the loop
				else {
					System.out.println("The AI has made its move:");
					// stimulates the AI move
					getAIMove(board);
					// the board is then displayed, it will contain the move of the AI and any other
					// previous moves
					displayBoard(board);
					// every time the AI plays, it checks if he won or not
					// if it is the case, it terminates the loop, and announces the winner
					if (checkForWinner(board) == 'o') {
						System.out.println("GAME OVER");
						System.out.println("You lost!");
						break;
					}
				}
			} else if (coin == 1) {
				// if the AI starts first, then he can play at every odd of the loop
				if (i % 2 != 0) {
					System.out.println("The AI has made its move:");
					// stimulates the AI move
					getAIMove(board);
					// the board is then displayed, it will contain the move of the AI and any other
					// previous moves
					displayBoard(board);
					// every time the AI plays, it checks if he won or not
					// if it is the case, it terminates the loop, and announces the winner
					if (checkForWinner(board) == 'o') {
						System.out.println("GAME OVER");
						System.out.println("You lost!");
						break;
					}
				}
				// and the user plays at every even iteration of the loop
				else {
					System.out.println("Please enter your move:");
					// the user is asked to input coordinates, a 'x' is then added to cell that
					// corresponds to them
					getUserMove(board);
					// the board is then displayed, it will contain the move of the user and any
					// other previous moves
					displayBoard(board);
					// every time the user plays, it checks if he won or not
					// if it is the case, it terminates the loop, and announces the winner
					if (checkForWinner(board) == 'x') {
						System.out.println("GAME OVER");
						System.out.println("You won!");
						break;
					}
				}
			}
		}
		// if the maximal number of moves is reached, the game terminates, and no one
		// wins
		if (checkForWinner(board) == ' ') {
			System.out.println("GAME OVER");
			System.out.println("No more available moves! It is a tie! ");
		}
	}
}