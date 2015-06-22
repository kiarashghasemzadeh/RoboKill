package game.objects.weapons;

import game.Position;
import game.objects.AddOne;
import game.objects.Robot;

import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public abstract class Weapon extends AddOne {
	/**
	 * Properties
	 */
	protected int power;
	protected float speed;
	protected int price;
	protected String name;
	/**
	 * Mouse Point
	 */
	protected Point mousePoint;
	/**
	 * Image of weapon
	 */

	protected String imageInFieldAddress;
	/**
	 * Image of gun
	 */
	protected Image imageInField;

	public Weapon() {
	}

	/**
	 * Getter for power
	 * 
	 * @return power of weapon
	 */
	public int getPower() {
		return power;
	}

	/**
	 * Getter for speed
	 * 
	 * @return speed of weapon
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Getter for price
	 * 
	 * @return price of weapon
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Getter for name
	 * 
	 * @return name of weapon
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for mouse point
	 * 
	 * @param p
	 */
	public void setMousePoint(Point p) {
		mousePoint = p;
	}

	/**
	 * Getter for mouse point
	 * 
	 * @return Mouse point
	 */
	public Point getMousePoint() {
		return mousePoint;
	}

	/**
	 * Getter for image of weapon in inventory
	 * 
	 * @return Image address that is in inventory
	 */
	public String getImageInInventoryAddress() {
		return imageInInventoryAddress;
	}

	/**
	 * Getter for image of weapon in game field
	 * 
	 * @return Image address that is in game field
	 */
	public String getImageInFieldAddress() {
		return imageInFieldAddress;
	}

	/**
	 * Add a gun to robot in Specified place
	 * 
	 * @param place
	 *            Specified place in robot
	 * @param robot
	 *            Gun add to this robot
	 */
	public void addGunToRobot(int place, Robot robot) {

	}

	public abstract void shot(float angleRad, Position pos);

	public abstract void update(GameContainer gc);

	public abstract void init();

	public abstract void draw(Position pos, int robotWidth, int robotHeight,
			float angle);
}
