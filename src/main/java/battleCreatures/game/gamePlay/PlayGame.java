/**
Code written by: Tiffany Chiou
Part of Jenkins How-to Tutorial, Assignment 5
**/

package battleCreatures.game.gamePlay;

import java.util.HashMap;
import java.util.Random;

/**
 * PlayGame class implements a simple monster battling game
 * Each monster has pre-programmed attacks, defense, and health
 * Game features a 2 player game that plays to completion
 * Game ends when one monster's HP reaches 0
 * @author tiffanychiou
 * Code has a bug - works if the monsters are different but not if the two monsters are of the same type
 */

public class PlayGame {
	
	// define the pre-determined monster stats as instance variables of the class
	public String monster[] = {"Troll","Penguin","Rabbit","Sloth","Fox"};
	protected int attack[] = {30,20,20,10,27};
	protected int defense[] = {3,12,12,2,15};
	public int health[] = {50,45,45,200,50};
	
	// methods that calculate game play
	
	/**
	 * Calculates the attack value for a monster
	 * A random boost may be applied via a random number generator
	 * @param idx
	 * @return attackVal (the calculated attack value)
	 */
	protected int calculateAttack(int idx) {
		// calculate to see if the attack gets a boost or not - random number generator
		Random rand = new Random(System.currentTimeMillis());
		int boost = rand.nextInt(5);
		int attackVal = attack[idx];
		
		if (boost % 2 == 0) {
			attackVal = attackVal*=1.5;
			System.out.println(monster[idx] + "'s attack was increased 1.5xs.\n");
		} else if (boost == 4) {
			attackVal = attackVal*=20;
			System.out.println(monster[idx] + "'s attack was increased 20xs.\n");
		}
		System.out.println(monster[idx] +" attacked " + attackVal + " points!\n");
		
		return attackVal;
	}
	
	/**
	 * Calculates the health change of the monster that is attacked
	 * @param attackVal
	 * @param idx
	 * @param monsterHp
	 */
	protected void healthChange (int attackVal, int idx, HashMap<String,Integer> monsterHp) {
		attackVal = attackVal - defense[idx];
		int newHealth;
		// if the attack has not been negated by defense, then subtract from health
		// otherwise attack has no impact on health
		if (attackVal > 0) {
			newHealth = monsterHp.get(monster[idx]) - attackVal;
			// update the monster's health
			monsterHp.put(monster[idx], newHealth);
			// if health is less than 0, then set it to 0
			if (newHealth < 0)
				monsterHp.put(monster[idx], 0);
			
			System.out.println(monster[idx]+ "'s health decreased by " +attackVal+" points.\n");
		}
		
	}
	
	/**
	 * Function is called every turn, carries out all the actions of one turn
	 * @param monster1 - monster that is doing the attacking
	 * @param monster2 - monster that is being attacked
	 * @param monsterHp - HashMap object that holds the HP value of the monster
	 */
	public void turn (int monster1, int monster2, HashMap<String,Integer> monsterHp) {
		// get the attack value
		int attackVal = calculateAttack(monster1);
		
		// call healthChange to appropriately decrease the health of monster2 from monster1's attack
		healthChange(attackVal,monster2, monsterHp);
		System.out.println(monster[monster1] + ": " + monsterHp.get(monster[monster1]));
		System.out.println(monster[monster2] + ": " + monsterHp.get(monster[monster2])+"\n");
	}
	
	/**
	 * Main function that initializes an object of PlayGame to play the game
	 * Runs the game via a while loop until one monster's health reaches 0
	 * @param args
	 */
	
	public static void main(String[] args) {
		PlayGame newGame = new PlayGame();
		
		Random rand = new Random(System.currentTimeMillis());
		int monster1 = rand.nextInt(4);
		int monster2 = rand.nextInt(4);
		
		int turn = 0; // keeps track of which monster's turn it is to attack
		
		// take in values for health
		// create a new hash map to keep track of each monster's health
		// the key is the monster's name, the value is the monster's health
		HashMap<String,Integer> monsterHp = new HashMap<String,Integer>();
		
		int health = newGame.health[monster1];
		monsterHp.put(newGame.monster[monster1], newGame.health[monster1]);
		monsterHp.put(newGame.monster[monster2], newGame.health[monster2]);
		
		// while both monsters have health greater than 0, keep playing game in while loop
		while (monsterHp.get(newGame.monster[monster1]) > 0 && monsterHp.get(newGame.monster[monster2]) > 0) {
			turn++;
			// if turn is an even number, monster1 attacks
			if (turn % 2 == 0) {
				newGame.turn(monster1, monster2, monsterHp);
			} else {
			// if turn is an even number, monster 2 attacks
				newGame.turn(monster2,  monster1, monsterHp);
			}
		}
		
		// print out results
		if (monsterHp.get(newGame.monster[monster1]) == 0)
			System.out.println(newGame.monster[monster2] + " won!\n");
		else
			System.out.println(newGame.monster[monster1] + " won!\n");	
	}

}