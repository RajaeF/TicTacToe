import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

	public static void main(String[] args) {
		//stimulates a game of TicTacToe
		play();
	}

	// this array will represent the board that is going to be modified after each
	// round
	public static char[][] createBoard(int n) {
		char[][] board = new char[n][n];
		// filling every cell of the array with a space character
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
		return board;
	}

	//prints a series of "+-"
	public static void printLine(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("+-");
		}
		System.out.println("+");
	}

	//  display the content of the board
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

	//writes a character on the board
	// it takes as input the character that is to be added and the coordinates of
	// the desired placement
	public static void writeOnBoard(char[][] arr, char c, int x, int y) {
		// if those coordinates are equal or larger than the length of the array, they
		// are out of bounds
		if (x >= arr.length || y >= arr[0].length) {
			throw new IllegalArgumentException("The coordinates received represent a cell outside of the board.");
		} else {
			for (int i = 0; i <= x; i++) {
				for (int j = 0; j <= y; j++) {
					if (i == x && j == y) {
						//only empty cells are allowed to be filled
						if (arr[i][j] != ' ') {
							throw new IllegalArgumentException(
									"The cell contains a character other than a space character.");
						} else {
							arr[i][j] = c;
						}
					}
				}
			}
		}
	}

	//add the user's move to the board
	public static void getUserMove(char[][] arr) {
		Scanner read = new Scanner(System.in);
		int xCoordinate = 0;
		int yCoordinate = 0;
		boolean valid = false;
		while (!valid) {
			try {
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
		writeOnBoard(arr, 'x', xCoordinate, yCoordinate);
	}

	// determines if someone is winning on a row
	public static boolean rowWinner(char[][] arr, char c) {
		boolean result = true;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != c) {
					result = false;
					// once it detects one character off, it breaks and goes on to another row
					break;
				} else
					result = true;
			}
			if (result) {
				return result;
			}
		}
		return result;
	}

	// determines if someone is winning on a column
	public static boolean colWinner(char[][] arr, char c) {
		boolean result = true;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[j][i] != c) {
					result = false;
					// once it detects one character off, it breaks and goes on to another column
					break;
				} else
					result = true;
			}
			if (result) {
				return result;
			}
		}
		return result;
	}

	// determines if someone is winning on the diagonal
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
		return result;
	}

	// determines if someone is winning on the second diagonal
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
		return result;
	}


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

	public static int howManyChars(char[] arr, char c) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == c) {
				count++;
			}
		}
		return count;
	}


	public static int indexOfChar(char[] arr) {
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (howManyChars(arr, ' ') == 1 && arr[i] == ' ') {
				index = i;
			}
		}
		return index;
	}


	public static boolean rowMaybeWin(char[][] arr, char c) {
		for (int i = 0; i < arr.length; i++) {
			if (howManyChars(row(arr, i), ' ') == 1 && howManyChars(row(arr, i), c) == (arr.length - 1)) {
				return true;
			}
		}
		return false;
	}


	public static boolean colMaybeWin(char[][] arr, char c) {
		for (int i = 0; i < arr.length; i++) {
			if (howManyChars(column(arr, i), ' ') == 1 && howManyChars(column(arr, i), c) == (arr.length - 1)) {
				return true;
			}
		}
		return false;
	}

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

	// stimulates the move of the AI
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


	public static char checkForWinner(char[][] arr) {
		if (rowWinner(arr, 'x') || colWinner(arr, 'x') || diagWinner(arr, 'x') || otherDiagWinner(arr, 'x')) {
			return 'x';
		} else if (rowWinner(arr, 'o') || colWinner(arr, 'o') || diagWinner(arr, 'o') || otherDiagWinner(arr, 'o')) {
			return 'o';
		} else {
			return ' ';
		}
	}

	//stimulate the TicTacToe game
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