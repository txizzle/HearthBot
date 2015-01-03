package player;

public class Player 
{
	protected static final int DRUID = 0;
	protected static final int HUNTER = 1;
	protected static final int MAGE =  2;
	protected static final int PALADIN = 3;
	protected static final int PRIEST = 4;
	protected static final int ROGUE =  5;
	protected static final int SHAMAN = 6;
	protected static final int WARLOCK = 7;
	protected static final int WARRIOR = 8;
	protected static final String[] classNames = {"Druid", "Hunter", "Mage", "Paladin", "Priest", "Rogue", "Shaman", "Warlock", "Warrior"};

	protected int maxHP;
	protected int currHP;
	protected String name;
	protected int heroClass; //need a glossary for which class this hero is
	protected int playerNumber;
	protected Board board;


	public Player(String nameOfHero, int classOfHero, int playerNumb)
	{
		heroClass = classOfHero;
		name = nameOfHero;
		playerNumber = playerNumb;
		maxHP = 30;
		currHP = 30;
	}

	private void setBoard(Board heroBoard) {
		this.board = heroBoard;
	}

	protected Player getOtherPlayer() {
		return board.getPlayer(1 - playerNumber);
	}
	public void takeDmg(int dmg) {
		currHP -= dmg;
	}

	public int getHealth() {
		return currHP;
	}

	protected Board getBoard() {
		return board;
	}
	public void die() {
		System.out.println(name + " has died!");
	}

	public void win() {
		System.out.println(name + " wins!");
	}

	public String ToString() {
		String toReturn = "Player " + (playerNumber+1) + " Statistics:\nName: " + name + "\nClass: " + classNames[heroClass] + "\nHealth: " + currHP + "\n";
		return toReturn;
	}
}