package player;


import java.util.Scanner;

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

	private static Board instance;

	static Board initialize(String name)
	{
		if (instance == null)
		{
			Player us = new Player(name, 0, 0);
			Player them = new Player("CPU", 0, 1);
			instance = new Board(us, them);
			return instance;
		}
		else
        {
            return instance;
        }
	}

	public static void main(String[] args) 
	{
		Board initialBoard = new Board();
		initialBoard.play();
		//player1 = new Player(name, 0, 0);
		//player2 = new Player("Opponent", 2, 1);
		//Board currBoard = new Board(player1, player2);
		
	}

	public void play() 
	{
		Scanner user_input = new Scanner( System.in );
		System.out.println("Welcome to HearthBot!\n");
		System.out.println("What is your name?");
		String name = user_input.next();
		System.out.println("\nWell met, " + name + "!");
		System.out.println("What class do you wish to play?");
		System.out.println("Druid: 0, Hunter: 1, Mage: 2, Paladin: 3, Priest: 4, Rogue: 5, Shaman: 6, Warlock: 7, Warrior: 8");
		int playerClass = Integer.parseInt(user_input.next());
		player1 = new Player(name, playerClass, 0);
		player2 = new Player("Opponent", 2, 1);
		System.out.println("\n"+player1.ToString());
		System.out.println(player2.ToString());
		
		Board testBoard = new Board(player1, player2);
		Minion goldshireFootman = new Minion("Goldshire Footman");
		Minion chillwindYeti = new Minion("Chillwind Yeti");
		testBoard.addMinion(goldshireFootman, 0, 0);
		testBoard.addMinion(chillwindYeti, 3, 1);
		testBoard.printBoard();
		Action test = new Action(chillwindYeti, goldshireFootman);
		testBoard.printBoard();
	}

	public Board() {
		playerBoard = new Minion[7];
		opponentBoard = new Minion[7];
		playerSD = 0;
		opponentSD = 0;
	}

	public Board(Player us, Player them) {
		playerBoard = new Minion[7];
		opponentBoard = new Minion[7];
		player1 = us;
		player2 = them;
		playerSD = 0;
		opponentSD = 0;
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

	public void addMinion(Minion toAdd, int place, int player) {
		if (player == 0) {
			if (playerBoard[place] == null) {
				playerBoard[place] = toAdd;
			}
			else {
				//TODO: throw minionPlaceConflictException
			}
		}
		else {
			if (opponentBoard[place] == null) {
				opponentBoard[place] = toAdd;
			}
			else {
				//TODO: throw minionPlaceConflictException
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

	public void printBoard() {
		System.out.println("\nYour board: ");
		for (int i = 0; i < 7; i ++) {
			if (playerBoard[i] == null) {
				System.out.println("Position " + i + ": Null");
			}
			else {
				System.out.println("Position " + i + ":");
				System.out.println(this.playerBoard[i].ToString());
			}
			
		}
		System.out.println("\n");
		System.out.println("Opponent's board: ");
		for (int i = 0; i < 7; i ++) {
			if (opponentBoard[i] == null) {
				System.out.println("Position  " + i + ": Null");
			}
			else {
				System.out.println("Position " + i + ":");
				System.out.println(this.opponentBoard[i].ToString());
			}
		}
	}
}