package game.util;

import java.util.Scanner;

import game.players.Player;

public class TicTacToe {

	private final int ROWS = 3;
	private final int COLS = 3;
	private final char MATRIX[][] = new char[ROWS][COLS];
	private final Scanner s = new Scanner(System.in);
	private final char CROSS = 'X';
	private final char ZERO = '0';
	private Player firstPlayer;
	private Player secondPlayer;
	private Player currentPlayer;
	private int maxMoves = ROWS * COLS;
	private int completedMoves = 0;

	private void printMatrix() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++)
				System.out.print(" " + MATRIX[i][j] + " ");
			System.out.println();
		}
	}

	private void initializeMatrix() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++)
				MATRIX[i][j] = '_';
		}
	}

	private void play() {
		printMatrix();
		while (!isGameOver()) {
			takePlayerMove();
		}
	}

	private boolean isOccupied(int place) {
		int row = calcRow(place);
		int col = calcCol(place);
		if (MATRIX[row][col] == CROSS || MATRIX[row][col] == ZERO)
			return true;
		return false;
	}

	private int calcRow(int place) {
		if (place > 6) {
			return 2;
		} else if (place > 3) {
			return 1;
		}
		return 0;
	}

	private int calcCol(int place) {
		if (place % 3 == 0) {
			return 2;
		} else if ((place + 1) % 3 == 0) {
			return 1;
		}
		return 0;
	}

	private void takePlayerMove() {
		System.out.print(currentPlayer.getPlayerName() + "'s turn, Enter your place number: ");
		int place = -1;
		boolean valid = false;
		do {
			if (s.hasNextInt()) {
				place = s.nextInt();
				if (place >= 1 && place <= 9) {
					if (!isOccupied(place)) {
						valid = true;
						fillPlace(place);
					} else {
						System.out.println("Place is already Occupied!!");
					}
				} else {
					System.out.println("Invalid Place");
				}
			}
		} while (!valid);
	}

	private void fillPlace(int place) {
		MATRIX[calcRow(place)][calcCol(place)] = currentPlayer.getPlayerMove();
		completedMoves++;
		printMatrix();
		if (!isGameOver())
			changeCurrentPlayer();
		else
			showResults();
	}

	private boolean isGameOver() {
		if (checkWin()) {
			currentPlayer.setHasWon(true);
			return true;
		}
		if (completedMoves >= maxMoves)
			return true;
		return false;
	}

	private void showResults() {
		if (currentPlayer.isHasWon())
			System.out.println("Congratulations! " + currentPlayer.getPlayerName() + " has won!!");
		else
			System.out.println("It's a draw!!");
	}

	private boolean checkWin() {
		if (completedMoves < 5)
			return false;
		char currentPlayerMove = currentPlayer.getPlayerMove();
		if (MATRIX[1][1] == currentPlayerMove) {
			if ((MATRIX[0][0] == currentPlayerMove) && (MATRIX[2][2] == currentPlayerMove))
				return true;
			if ((MATRIX[0][1] == currentPlayerMove) && (MATRIX[2][1] == currentPlayerMove))
				return true;
			if ((MATRIX[0][2] == currentPlayerMove) && (MATRIX[2][0] == currentPlayerMove))
				return true;
			if ((MATRIX[1][0] == currentPlayerMove) && (MATRIX[1][2] == currentPlayerMove))
				return true;
		} else if (MATRIX[0][0] == currentPlayerMove) {
			if ((MATRIX[0][1] == currentPlayerMove) && (MATRIX[0][2] == currentPlayerMove))
				return true;
			if ((MATRIX[1][0] == currentPlayerMove) && (MATRIX[2][0] == currentPlayerMove))
				return true;
		} else if (MATRIX[2][2] == currentPlayerMove) {
			if ((MATRIX[1][2] == currentPlayerMove) && (MATRIX[0][2] == currentPlayerMove))
				return true;
			if ((MATRIX[2][1] == currentPlayerMove) && (MATRIX[2][0] == currentPlayerMove))
				return true;
		}
		return false;
	}

	private void changeCurrentPlayer() {
		if (currentPlayer == firstPlayer)
			currentPlayer = secondPlayer;
		else if (currentPlayer == secondPlayer)
			currentPlayer = firstPlayer;
	}

	private void setPlayers() {
		String playerName;
		System.out.print("Enter First Player Name: ");
		playerName = s.nextLine();
		firstPlayer = new Player(playerName, ZERO);
		System.out.print("Enter Second Player Name: ");
		playerName = s.nextLine();
		secondPlayer = new Player(playerName, CROSS);
		firstPlayer.setHasTurn(Boolean.TRUE);
		currentPlayer = firstPlayer;
	}

	public void startGame() {
		System.out.println("\n\n\t-----Welcome to TIC TAC TOE Game------\n\n");
		setPlayers();
		initializeMatrix();
		play();
	}

}
