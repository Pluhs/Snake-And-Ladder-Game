import java.util.*;
/**
 *--------------------------------------
 * COMP249
 * Assignment 1
 * 02/02/2023
 *--------------------------------------
 *This code is for the Snake And Ladder Game for 2 players. It has a class player that
 *allows the user to have a name and position. It has a 2D array for displaying the board
 *with players positions displayed. There are if statements used for the snakes and ladders
 *that sets the position of the player based on the square position of the ladders and snakes.
 The players move up the board up to position 100 to win the game.
 */
/**
 * @author Mohammed Alassad
 * this is the driver class
 *it is creating a new LadderAndSnake object with the parameterized constructor where the passed value of players
 *is taken from the input of the user
 */
public class PlayLadderAndSnake {
	public static void main(String[] args) {
		System.out.println("Welcome to the snake and ladder game!");
		System.out.print("Enter the number of players: ");
		Scanner keyIn = new Scanner(System.in);
		int players = keyIn.nextInt();
		LadderAndSnake game = new LadderAndSnake (players);
		game.decidingPlayer();
		game.play();	
	}

}
