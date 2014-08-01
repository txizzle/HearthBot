package player;

public class Player 
{
	protected int maxHP;
	protected int currHP;
	protected String name;
	protected int heroClass; //need a glossary for which class this hero is
	protected int playerNumber;
	protected Board board;

	private Player(String nameOfHero, int classOfHero, Board thisBoard, int playerNumb)
	{
		this.heroClass = classOfHero;
		this.name = nameOfHero;
		this.board = thisBoard;
		this.playerNumber = playerNumb;
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
}