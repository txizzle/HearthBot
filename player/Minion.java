package player;

import java.util.*;

public class Minion
{
	//Minion Types
	private static final int MINION = 0; //normal minion type
	private static final int BEAST = 1;
	private static final int DRAGON = 2;
	private static final int MURLOC = 3;
	private static final int PIRATE = 4;
	private static final int DEMON = 5;
	private static final int MECH = 6;

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
	protected int type;
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
	
	//Minion Library
	protected static Hashtable<String, int[]> minionDict = makeDict();
	//Creating a new Minion instance; default is vanilla (att, HP, type)

	protected static Hashtable<String, int[]> makeDict() {
		Hashtable<String, int[]> minionLibrary = new Hashtable<String, int[]>();
		int[] goldshire = {1,2,1,0,0,1,0,0,0,0,0,0,0,0};
		int[] wisp =      {1,1,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] frostwolf = {2,2,2,0,0,1,0,0,0,0,0,0,0,0};
		int[] kobold =    {2,2,2,0,1,0,0,0,0,0,0,0,0,0};
		int[] crocolisk = {2,3,2,1,0,0,0,0,0,0,0,0,0,0};
		int[] yeti =      {4,5,4,0,0,0,0,0,0,0,0,0,0,0};
		int[] senjin =    {3,5,4,0,0,1,0,0,0,0,0,0,0,0};
		minionLibrary.put("Goldshire Footman", goldshire);
		minionLibrary.put("Wisp", wisp);
		minionLibrary.put("Frostwolf Grunt", frostwolf);
		minionLibrary.put("Kobold Geomancer", kobold);
		minionLibrary.put("River Crocolisk", crocolisk);
		minionLibrary.put("Chillwind Yeti", yeti);
		minionLibrary.put("Sen'jin Shieldmasta", senjin);
		return minionLibrary;
	}
	protected Minion(String name, int att, int health, int cost, int typeOfMinion, int sDmg, int taunt, int charge,
		int battleCry, int deathRattle, int divineShield, int enrage , int endTurn, 
		int startTurn, int immune)
	{
		name = name;
		normalAtt = att;
		currAtt = normalAtt;
		maxHP = health;
		currHP = health;
		cost = cost;
		type = typeOfMinion;
		spellDmg = sDmg;
		hasTaunt = taunt == 1;
		hasCharge = charge == 1;
		hasBC = battleCry == 1;
		hasDR = deathRattle == 1;
		hasDS = divineShield == 1;
		hasER = enrage == 1;
		hasET = endTurn == 1;
		hasST = startTurn == 1;
		isImmune = immune == 1;
	}

	protected Minion(String dictName) 
	{
		int[] minionValues = minionDict.get(dictName);
		this.name = dictName;
		this.normalAtt = minionValues[0];
		this.currAtt = minionValues[0];
		this.maxHP = minionValues[1];
		this.currHP = minionValues[1];
		cost = minionValues[2];
		spellDmg = minionValues[4];
		hasTaunt = minionValues[5] == 1;
		hasCharge = minionValues[6] == 1;
		hasBC = minionValues[7] == 1;
		hasDR = minionValues[8] == 1;
		hasDS = minionValues[9] == 1;
		hasER = minionValues[10] == 1;
		hasET = minionValues[11] == 1;
		hasST = minionValues[12] == 1;
		isImmune = minionValues[13] == 1;
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

	public String ToString() {
		String outputMinion = " Name: " + name + "\n Attack: " + currAtt + "\n Health: " + currHP;
		return outputMinion;

	}

}