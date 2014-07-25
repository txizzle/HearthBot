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
	protected int att; //initially same as normalAtt
	protected boolean isSilenced;

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
	
	//A vanilla minion (att, health, type)
	private Minion(String name, int att, int maxHP, int cost, int typeOfMinion)
	{

	}

	//A minion with battlecry
	private Minion(String name, int att, int maxHP, int cost, int typeOfMinion, boolean hasBC)
	{

	}

	//A minion with deathrattle
	private Minion(String name, int att, int maxHP, int cost, int typeOfMinion, boolean hasBC)
}