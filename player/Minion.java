package player;

public class Minion
{
	//Minion Types
	private static final int MINION = 0; //normal minion type
	private static final int BEAST = 1;
	private static final int DRAGON = 2;
	private static final int MURLOC = 3;
	private static final int PIRATE = 4;

	//Universal Minion Declarations
	protected String name;
	protected int cost; //not sure if we will ever use this
	protected int normalHP; //for when silence is played
	protected int normalAtt; //for when silence is played
	protected int maxHP; //initially this is the same as normalHP, special cases like injured blademaster though
	protected int currAtt; //initially same as normalAtt
	protected int currHP; 
	protected boolean isSilenced;
	protected int controller;
	protected Board thisBoard;

	//Special Minion Attributes
	protected boolean hasCharge;
	protected boolean hasTaunt;
	protected boolean hasBC; //battlecry
	protected boolean hasDR; //deathrattle
	protected boolean hasDS; //divine shield
	protected boolean hasER; //enrage
	protected boolean hasET; //has end turn action, ex. rag, mana tide totem
	protected boolean hasST; //has start turn action, ex. nat pagle
	protected boolean isImmune;
	protected int spellDmg;
	protected boolean frozen;
	
	//Creating a new Minion instance; default is vanilla (att, HP, type)

	private Minion(String name, int att, int health, int cost, int typeOfMinion, int sDmg, boolean taunt, 
		boolean battleCry, boolean deathRattle, boolean divineShield, boolean enrage , boolean endTurn, 
		boolean startTurn, boolean isImmune)
	{
		this.name = name;
		this.normalAtt = att;
		this.currAtt = normalAtt;
		this.maxHP = health;
	}

	protected int getPlayer() {
		return controller;
	}
	protected Board getBoard() {
		return thisBoard;
	}
	protected void takeDmg(int dmg) {
		if (hasDS) {
			hasDS = false;
		}
		else {
			currHP -= dmg;
		}
	}

	protected void die() {
		//TODO: implement when a minion dies
	}

	protected void addHealth(int hpToAdd) {
		maxHP += hpToAdd;
	}

	protected void heal(int hpToHeal) {
		if (currHP + hpToHeal >= maxHP) {
			currHP = maxHP;
		}
		else {
			currHP += hpToHeal;
		}
	}

	protected int getSD() {
		if (isSilenced == false) {
			return spellDmg;
		} 
		return 0;
	}
	protected boolean hasDR() {
		return hasDR;
	}

	protected boolean hasBC() {
		return hasBC;
	}

	protected boolean hasER() {
		return hasER;
	}

	protected boolean hasET() {
		return hasET;
	}

	protected boolean isImmune() {
		return isImmune;
	}

	protected int getAtt() {
		return currAtt;
	}

	protected int getHealth() {
		return currHP;
	}

	protected boolean isFrozen() {
		return frozen;
	}

	protected Card ToCard() {
		//TODO: add card conversion (pull from database? construct completely new card?)
		Card temp = new Card();
		return temp;
	}

}