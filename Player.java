/**
 *--------------------------------------
 * Mohammed Alassad
 * COMP249
 * Assignment 1
 * 02/02/2023
 *--------------------------------------
 */
/**
 * 
 * @author Mohammed Alassad
 * this class is the class Player where an object of that class a name, a position and a dice value
 *  */
public class Player {
private int dice;
private int position;
private String name;
/**
 * default constructor sets the name to "", position to 0 and dice to 0
 */
public Player () {
	name = "";
	position = 0;
	dice = 0;
}
/**
 * paramaterized constructor that takes a name as a parameter
 * @param name
 */
public Player (String name) {
	this ();
	this.name = name;
}
/**
 * setter for name
 * @param name
 */
public void setName (String name) {
	this.name = name;
}
/**
 * getter for name
 * @return name of player
 */
public String getName () {
	return name;
}
/**
 * getter for dice
 * @return dice value of player
 */
public int getDice () {
	return dice;
}
/**
 * setter for dice
 * @param dice value of player
 */
public void setDice (int dice) {
	this.dice= dice;
}
/**
 * getter for position
 * @return position of the player
 */
public int getPosition() {
	return position;
}
/**
 * setter for position
 * @param position of the player
 */
public void setPosition(int position) {
	this.position=position;
}
/**
 * toString method
 */
public String toString () {
	return name + " rolled " + dice + " and is now in square " + position;
}

}

