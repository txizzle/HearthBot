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

*/
public class Action
{

	//Minion attack
	private Action(Minion attacker, Minion defender)
	{
		if (attacker.getAtt() <= 0) {
			//throw MinionCantAttack
		}
		else {
			defender.takeDmg(attacker.getAtt());
			attacker.takeDmg(attacker.get)
			if (defender.getHealth() <= 0) {
				defender.die();
				if (defender.hasDR()) {
					//invoke DR
					
				}
			}
		}
	}
}