package player;

public class Player 
{
	protected int maxHP;
	protected int currHP;
	protected String name;
	protected int heroClass; //need a glossary for which class this hero is

	private Player(String nameOfHero, int classOfHero)
	{
		this.heroClass = classOfHero;
		this.name = nameOfHero;
	}

	public void takeDmg(int dmg) {
		currHP -= dmg;
	}

	public int getHealth() {
		return currHP;
	}
}