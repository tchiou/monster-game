/**
Code written by: Tiffany Chiou
Part of Jenkins How-to Tutorial, Assignment 5 :)
**/

package battleCreatures.game.gamePlay;

import java.util.HashMap;
import java.util.Random;

public class Monster {

	// monster attribute values
	private int attack;
	private int defense;
	private int health;
	private String type;
	
	// define the pre-determined monster stats as instance variables of the class
	private String monsterType[] = {"Troll","Penguin","Rabbit","Sloth","Fox"};
	private int attacks[] = {30,20,20,10,27};
	private int defenses[] = {3,12,12,2,15};
	private int healths[] = {50,45,45,200,50};
	
	public Monster(int mId) {
		// when instantiated, set monster based on mId
		// if the value of mId is out of bounds, then the default creature is troll
		if (mId > 4 || mId < 0)
			mId = 0;
		attack = attacks[mId];
		defense = defenses[mId];
		health = healths[mId];
		type = monsterType[mId];
	}
	
	// if no mId is specified then troll is the default
	public Monster() {
		this(0);
	}
	
	/**
	 * Calculates attack value given the boost and the monster's inherent attack value
	 * @return
	 */
	public int calculateAttack() {
		// calculate to see if the attack gets a boost or not - random number generator
		Random rand = new Random(System.currentTimeMillis());
		int boost = rand.nextInt(5);
		int attackVal = attack;
		
		if (boost % 2 == 0) {
			attackVal = attackVal*=1.5;
			System.out.println(type + "'s attack was increased 1.5xs.\n");
		} else if (boost == 4) {
			attackVal = attackVal*=20;
			System.out.println(type + "'s attack was increased 20xs.\n");
		}
		System.out.println(type +" attacked " + attackVal + " points!\n");
		
		return attackVal;
	}
	
	/**
	 * Calculates the health change of the monster that is attacked
	 * @param attackVal
	 * @param idx
	 * @param monsterHp
	 */
	
	public void healthChange (int attackVal) {
		attackVal = attackVal - defense;
		// if the attack has not been negated by defense, then subtract from health
		// otherwise attack has no impact on health
		if (attackVal > 0) {
			health = health - attackVal;
			if (health < 0)
				health = 0;
			System.out.println(type+ "'s health decreased by " +attackVal+" points.\n");
		}
		
	}
	
	/**
	 * Returns the health value
	 * @return health (int)
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns the monster type
	 * @return type (String)
	 */
	public String getType() {
		return type;
	}
	 
	
	
}
