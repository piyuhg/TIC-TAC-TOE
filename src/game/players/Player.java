package game.players;

public class Player {

	private String playerName;
	private char playerMove;
	private boolean hasTurn;
	private boolean hasWon;

	public Player(String playerName, char playerMove) {
		this.playerName = playerName;
		this.playerMove = playerMove;
		hasTurn = false;
		hasWon = false;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public char getPlayerMove() {
		return playerMove;
	}

	public void setPlayerMove(char playerMove) {
		this.playerMove = playerMove;
	}

	public boolean isHasTurn() {
		return hasTurn;
	}

	public void setHasTurn(boolean hasTurn) {
		this.hasTurn = hasTurn;
	}

	public boolean isHasWon() {
		return hasWon;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", playerMove=" + playerMove + ", hasTurn=" + hasTurn + ", hasWon="
				+ hasWon + "]";
	}

}