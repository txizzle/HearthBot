package player;

public class Board 
{
	protected static final int PLAYER = 0;
	protected static final int OPPONENT = 1;

	protected Player player1;
	protected Player player2;

	protected Minion[] playerBoard;
	protected Minion[] opponentBoard;

	protected int playerSD; //spelldamage total
	protected int opponentSD;

	public static void main(String[] args) {
		System.out.println("Welcome to HearthBot!");
	}


	protected Player getPlayer(int playerNumber) {
		if (playerNumber == 0) {
			return player1;
		}
		else {
			return player2;
		}
	}

	public void removeMinion(Minion toRemove) {
		if (toRemove.getPlayer() == 0) { //Minion is controlled by us
			for (int i = 0; i < playerBoard.length; i++) {
				if (toRemove == playerBoard[i]) {
					playerBoard[i] = null;
					//TODO: collapseBoard method to consolidate minion placement?
				}
			}
		}
		else { 
			for (int i = 0; i < opponentBoard.length; i++) {
				if (toRemove == opponentBoard[i]) {
					opponentBoard[i] = null;
					//TODO: collapseBoard method to consolidate minion placement?
				}
			}
		}
	}

	public int getSD(Player caster) {
		playerSD = 0;
		if (caster == player1) {
			for (Minion curr : playerBoard) {
				playerSD += curr.getSD();
			}
		}
		else {
			for (Minion curr : opponentBoard) {
				playerSD += curr.getSD();
			}
		}
		return playerSD;
	}
}