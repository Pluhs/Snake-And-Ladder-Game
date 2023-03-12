
import java.util.Scanner;

/**
 * 
 * @author this class is core of the game, it has the the core
 *         features of the game except for the class player.
 */
public class LadderAndSnake {
	/**
	 * dice variable to store the dice value 2D array board for storing the snakes
	 * and ladders 2D array displayboard for displaying the board dicecounter to
	 * count the number of times it was needed to decide the order of players
	 * numplayers to hold the number of players
	 */
	private static int dice;
	private Player[] players;
	private String[][] board;
	private String[][] displayboard;
	private int dicecounter;
	private int numplayers;
	Scanner keyIn = new Scanner(System.in);

	/**
	 * default constructor dice value to 0 players array set to 2 creating the
	 * displayboard board for display setting the positions of snakes and ladders on
	 * the board array
	 */
	public LadderAndSnake() {
		dice = 0;
		players = new Player[2];
		board = new String[10][10];
		displayboard = new String[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				if (i == 0)
					displayboard[i][j] = Integer.toString(100 - j);
				if (i == 1)
					displayboard[i][j] = Integer.toString(j + 81);
				if (i == 2)
					displayboard[i][j] = Integer.toString(80 - j);
				if (i == 3)
					displayboard[i][j] = Integer.toString(j + 61);
				if (i == 4)
					displayboard[i][j] = Integer.toString(60 - j);
				if (i == 5)
					displayboard[i][j] = Integer.toString(j + 41);
				if (i == 6)
					displayboard[i][j] = Integer.toString(40 - j);
				if (i == 7)
					displayboard[i][j] = Integer.toString(j + 21);
				if (i == 8)
					displayboard[i][j] = Integer.toString(20 - j);
				if (i == 9)
					displayboard[i][j] = Integer.toString(j + 1);
			}
		board[0][0] = "37";
		board[0][3] = "13";
		board[0][8] = "30";
		board[2][0] = "41";
		board[2][7] = "83";
		board[3][4] = "43";
		board[5][9] = "66";
		board[7][0] = "99";
		board[7][9] = "90";
		board[9][2] = "77";
		board[9][3] = "75";
		board[9][5] = "23";
		board[9][7] = "67";
		board[7][1] = "18";
		board[6][3] = "59";
		board[4][7] = "29";
		board[1][4] = "5";

	}

	/**
	 * 
	 * @param players, passes the value of number of players decided by the user
	 *                 parameterized constructor, players value passed as parameter
	 *                 to set the number of players in this method, if number of
	 *                 players is over 2 it will set the number to 2, if numbers of
	 *                 players is under 2 it will exit the game
	 */
	public LadderAndSnake(int players) {
		this();
		numplayers = players;
		if (players > 2) {
			System.out.print("Initialization was attempted for " + numplayers
					+ " member of players; however, this is only expected for an extended version the game. Value will be set to 2\n");
			numplayers = 2;
			for (int i = 0; i < numplayers; i++) {
				System.out.print("Please enter Player " + (i + 1) + " name: ");
				String name = keyIn.next();
				this.players[i] = new Player(name);
			}
		} else if (numplayers < 2) {
			System.out.print("Error: Cannot execute the game with less than 2 players! Will exit");
			System.exit(0);
		} else if (numplayers == 2) {
			System.out.print("The game is now set for 2 players\n");
			for (int i = 0; i < numplayers; i++) {
				System.out.print("Please enter Player " + (i + 1) + " name: ");
				String name = keyIn.next();
				this.players[i] = new Player(name);
			}
		}
	}

	/**
	 * a method that returns a dice value between 1-6
	 * 
	 * @return dice value
	 */
	public static int flipDice() {
		dice = (int) (Math.random() * 6) + 1;
		return dice;
	}

	/**
	 * this method will decide the order of the players, if the dice value of both
	 * players is the same it will reroll again to decide the order (while loop)
	 * while couting the number of times it took to decide the order
	 */
	public void decidingPlayer() {
		System.out.println("Now deciding which player will start playing");
		boolean notie = true;
		while (notie) {
			for (int i = 0; i < numplayers; i++) {
				System.out.println("\nPlayer " + players[i].getName() + ", press enter to roll dice.");
				try {
					// will read until end of stream
					// System.in.read() method works only with exception handling
					//looked online, couldn't make it look good with keyIn.nextLine(); alone
					System.in.read();
					keyIn.nextLine();
				} catch (Exception e) {
				}
				players[i].setDice(flipDice());
				System.out.println(players[i].getName() + " got a dice value of " + players[i].getDice());
				if (i == 1) {
					dicecounter++;
					if (players[0].getDice() == players[1].getDice()) {
						System.out.println("\nA tie was achieved between " + players[0].getName() + " and "
								+ players[1].getName() + ". Attempting to break the tie.");
					} else if (players[0].getDice() > players[1].getDice()) {
						System.out.println("\nIt took " + dicecounter + " attempt(s) to decide order of playing. "
								+ players[0].getName() + " will start.");
						notie = false;
					} else {
						System.out.println("\nIt took " + dicecounter + " attempt(s) to decide order of playing. "
								+ players[1].getName() + " will start.");
						notie = false;
						Player[] temp = new Player[2];
						temp[0] = players[1];
						temp[1] = players[0];
						players = temp;
					}

				}
			}
		}
	}

	/**
	 * method that takes in an object of type Player this method sets the position
	 * of the player based on the dice value and the snakes and ladders square
	 * position it also holds the condition of a players position exceeding 100
	 * 
	 * @param player, player here is an object of type Player
	 */
	public void moveConditions(Player player) {
		player.setPosition(player.getDice() + player.getPosition());
		if (player.getPosition() == 1) {
			System.out.println("A ladder!");
			player.setPosition(Integer.parseInt(board[0][0]) + 1);
		} else if (player.getPosition() == 4) {
			player.setPosition(Integer.parseInt(board[0][3]) + 1);
			System.out.println("A ladder!");
		} else if (player.getPosition() == 9) {
			player.setPosition(Integer.parseInt(board[0][8]) + 1);
			System.out.println("A ladder!");
		} else if (player.getPosition() == 21) {
			player.setPosition(Integer.parseInt(board[2][0]) + 1);
			System.out.println("A ladder!");
		} else if (player.getPosition() == 28) {
			player.setPosition(Integer.parseInt(board[2][7]) + 1);
			System.out.println("A ladder!");
		} else if (player.getPosition() == 36) {
			player.setPosition(Integer.parseInt(board[3][4]) + 1);
			System.out.println("A ladder!");
		} else if (player.getPosition() == 51) {
			player.setPosition(Integer.parseInt(board[5][9]) + 1);
			System.out.println("A ladder!");
		} else if (player.getPosition() == 71) {
			player.setPosition(Integer.parseInt(board[7][9]) + 1);
			System.out.println("A ladder!");
		} else if (player.getPosition() == 80) {
			player.setPosition(Integer.parseInt(board[7][0]) + 1);
			System.out.println("A ladder!");
		} else if (player.getPosition() == 98) {
			player.setPosition(Integer.parseInt(board[9][2]) + 1);
			System.out.println("A Snake!");
		} else if (player.getPosition() == 97) {
			player.setPosition(Integer.parseInt(board[9][3]) + 1);
			System.out.println("A Snake!");
		} else if (player.getPosition() == 95) {
			player.setPosition(Integer.parseInt(board[9][5]) + 1);
			System.out.println("A Snake!");
		} else if (player.getPosition() == 93) {
			player.setPosition(Integer.parseInt(board[9][7]) + 1);
			System.out.println("A Snake!");
		} else if (player.getPosition() == 79) {
			player.setPosition(Integer.parseInt(board[7][1]) + 1);
			System.out.println("A Snake!");
		} else if (player.getPosition() == 64) {
			player.setPosition(Integer.parseInt(board[6][3]) + 1);
			System.out.println("A Snake!");
		} else if (player.getPosition() == 48) {
			player.setPosition(Integer.parseInt(board[4][7]) + 1);
			System.out.println("A Snake!");
		} else if (player.getPosition() == 16) {
			player.setPosition(Integer.parseInt(board[1][4]) + 1);
			System.out.println("A Snake!");
		}
		System.out.println(player);
		if (player.getPosition() > 100) {
			player.setPosition(100 - (player.getPosition() - 100));
			System.out.println("Exceeded 100, going back; Player " + player.getName() + " is now in square "
					+ player.getPosition());
		}

	}

	/**
	 * this method is the core method of the game in a while loop it will ask the
	 * player to roll the dice and will set the position based on the dice value if
	 * two players are on the same square, one of them will go down back to position
	 * 0. if a player gets to position 100, the program will exit
	 */
	public void play() {
		System.out.println("Both players are in position 0." + "\nRolling dice to move positions");
		boolean gameDone = true;
		while (gameDone) {
			for (int i = 0; i < numplayers; i++) {
				System.out.println("\nPlayer " + players[i].getName() + ", press enter to roll dice.");
				try {
					System.in.read();
					keyIn.nextLine();
				} catch (Exception e) {
				}
				players[i].setDice(flipDice());
				moveConditions(players[i]);
				if (i == 0) {
					if (players[0].getPosition() == players[1].getPosition()) {
						System.out.println("Since " + players[1].getName() + " was already in square "
								+ players[1].getPosition() + ", " + players[0].getName() + " moved and displaced "
								+ players[1].getName() + " to square 0.");
						players[1].setPosition(0);

					}
				}
				if (i == 1) {
					if (players[0].getPosition() == players[1].getPosition()) {
						System.out.println("Since " + players[0].getName() + " was already in square "
								+ players[0].getPosition() + ", " + players[1].getName() + " moved and displaced "
								+ players[0].getName() + " to square 0.");
						players[0].setPosition(0);
					}
					if (players[0].getPosition() != 100 && players[1].getPosition() != 100) {
						System.out.println("Game not over, flipping again.");
					}
				}
				if (players[i].getPosition() == 100) {
					gameDone = false;
					System.out.println(players[i].getName() + " won the game!");
					board();
					System.out.println("Will end program now.");
					break;
				}
				board();
			}
		}

	}

	/**
	 * this method is used to display the game board
	 */
	public void board() {
		System.out.println("-------------------------------------------------");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print("[");
				if (players[0].getPosition() == Integer.parseInt(displayboard[i][j]))
					System.out.print(players[0].getName() + "]" + " ");
				else if (players[1].getPosition() == Integer.parseInt(displayboard[i][j]))
					System.out.print(players[1].getName() + "]" + " ");
				else
					System.out.print(displayboard[i][j] + "]" + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}
}
