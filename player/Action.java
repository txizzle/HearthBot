package player;


/*
A class to describe actions that change the state of the board - 
	player choices that will use Action will include:
1) placing minions onto the board
2) attacking with a minion
	a) attacking face
	b) attacking another minion
3) battlecries
	a) may take in a target minion (Elven Archer, buffs)
	b) may take in a target hand
4) deathrattles
5) spells 
	a) AOE spells (spells like Flamestrike will take in all target minions, 
		while Multishot will need RNG applied to targetable minions)
	b) buff spells (will take in a target minion)
	c) hand-affecting spells (Arcane Intellect, Thoughtsteal)
	d) TODO: IMPLEMENT SPELLCAST CHECKING BEFORE CASTING SPELLS (mana wyrm, spellblock, counterspell, sorc apprentice). maybe a spellCastTrigger() method?

*/
public class Action
{

	protected boolean isSpell;

	//Minion attacking enemy minion
	private Action(Minion attacker, Minion defender)
	{
		isSpell = false;
		if (attacker.getAtt() <= 0) {
			//throw MinionCantAttack
		}
		else {
			defender.takeDmg(attacker.getAtt()); //check for DS here or in Minion class?
			attacker.takeDmg(attacker.getAtt());
			if (defender.getHealth() <= 0) {
				defender.die();
				if (defender.hasDR()) {
					//invoke DR
					///maybe put the invoking DR part in the minion class instead, and have checking for silence in the minion class
				}
			}
			if (attacker.getHealth() <= 0) {
				attacker.die();
				if (attacker.hasDR()) {
					//invoke DR
					//same as defender, maybe put this in the minion class
				}
			}
		}
	}

	//Minion attacking enemy hero
	private Action(Minion attacker, Player hero) 
	{
		isSpell = false;
		if (attacker.getAtt() <= 0) {
			//throw MinionCantAttack
		}
		else if (attacker.isFrozen() == true) {
			//Minion is frozen and can't attack
		}
		else {
			hero.takeDmg(attacker.getAtt());
			if (hero.getHealth() <= 0) {
				hero.die();
				hero.getOtherPlayer().win();
			}
		}
	}

	/* Minion-affecting spell 
		Heals: maxHPchange = 0, currHPchange = heal amount 
		HP buffs: maxHPchange = buff amount, currHPchange = same as maxHPchange.
		TODO: check for Auchenai Soulpriest, also figure out whether HP buffs deal damage w/ auchenai on board

		Attack buff, divine shield, taunt are simple
		TODO: make abusive searg. have only one turn attack buff

	TODO: still need extra case for divine spirit and blessing of champions, the x2 att/hp multipliers, Humility (1 att), Equality

	*/
	private Action(Player caster, boolean spell, Minion target, int maxHPchange, int currHPchange, int attChange, boolean givesTaunt, boolean givesDS, boolean givesCharge)
	{
		isSpell = true;
		//implement spell-checking

		//Spell that damages minion
		if (currHPchange < 0) {
			currHPchange -= target.getBoard().getSD(caster.getOtherPlayer());
			target.takeDmg(-currHPchange);
			if (target.getHealth() <= 0) {
				target.die();
			}
		}

		//Spell that increases max health of minion and/or heals minion
		else if (currHPchange > 0) {
			if (currHPchange != maxHPchange) {
				//we fucked up, no buff (yet) that has a different number for amount healed and amount max health increased
			}

			if (maxHPchange > 0) { //Spell increases max health of minion
				target.addHealth(maxHPchange);
			}

			target.heal(maxHPchange);
		}

		//Spell that changes attack value of minion

		//Spell that gives Divine Shield
		if (givesDS == true) {
			target.hasDS = true;
		}

		if (givesTaunt = true) {
			target.hasTaunt = true;
		}

		if (givesCharge == true) {
			target.hasCharge = true; 
			//TODO: what happens when a minion that gives other charge while alive dies (ie. warsong commander)
		}
	}

	/* Minion-affecting non-spell action
	
	*/
	private Action(boolean spell, Minion target, int maxHPchange, int currHPchange, int attChange, boolean givesTaunt, boolean givesDS)
	{
		isSpell = false;

	}

	//Returning a minion to hand
	private Action(Minion target, Hand hand) 
	{
		hand.AddCard(target.ToCard());
		target.getBoard().removeMinion(target); //NOT target.die()! Because we don't want to trigger deathrattle

	}

	//Returns whether or not current action is the result of a spell (for counterspell/spellblock purposes?)
	public boolean isSpell() 
	{
		return this.isSpell;
	}
}