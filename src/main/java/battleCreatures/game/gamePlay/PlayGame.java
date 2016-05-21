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
		
	/**
	 * Main function that initializes an object of Monster to play the game
	 * Runs the game via a while loop until one monster's health reaches 0
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		Random rand = new Random(System.currentTimeMillis());
		int monster1 = rand.nextInt(4);
		int monster2 = rand.nextInt(4);
		
		int turn = 0; // keeps track of which monster's turn it is to attack
		// instantiates 2 new monsters
		Monster m1 = new Monster(monster1);
		Monster m2 = new Monster(monster2);
		int attack;
		
		Monster temp1 = null;
		Monster temp2 = null;
		while (m1.getHealth()>0 && m2.getHealth()>0) {

			// set temp variables to different monsters depending on the turn
			if (turn%2==0) {
				temp1 = m1;
				temp2 = m2;
			} else {
				temp1 = m2;
				temp2 = m1;
			}
			
			// calculate attack
			attack = temp1.calculateAttack();
			
			// decrease health
			temp2.healthChange(attack);
			
			// print out health outcome
			System.out.println(m1.getType()+": "+m1.getHealth());
			System.out.println(m2.getType()+": "+m2.getHealth()+"\n");
			
			// increase turn value
			turn++;
		}
		
		// print out who won
		if (m1.getHealth()==0)
			System.out.println(m2.getType()+" won!\n");
		else
			System.out.println(m1.getType()+" won!\n");
		
	}

}