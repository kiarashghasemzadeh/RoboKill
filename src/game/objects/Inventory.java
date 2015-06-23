package game.objects;

import game.Item;
import game.Position;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Inventory {
	private ArrayList<AddOne> addOnes;
	private Image inventoryImage;
	private Image itemDiscr;
	private Image robot;
	private Image inventoryText;
	private Position pos;
	/**
	 * Character states.
	 */
	private Image character;
	private String inventoryAddress;
	private String itemDiscrAddress;
	private String characterAddress;
	private String robotAddress;
	private String inventoryTextAddress;
	private Item[][] itemsTabular;
	private Item[] weapons;
	public static Inventory instance = null;

	private Inventory() {
		addOnes = new ArrayList<AddOne>();

		inventoryAddress = "pics/inventory/inventory.png";
		itemDiscrAddress = "pics/inventory/itemDiscr.png";
		characterAddress = "pics/inventory/character.png";
		robotAddress = "pics/inventory/robot.png";
		inventoryTextAddress = "pics/texts/inventory.png";
		itemsTabular = new Item[7][4];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				itemsTabular[i][j] = new Item();
			}
		}

		weapons = new Item[4];
		weapons[0] = new Item(new Position(175, 270));
		weapons[1] = new Item(new Position(230, 270));
		weapons[2] = new Item(new Position(105, 360));
		weapons[3] = new Item(new Position(300, 360));

		pos = new Position(50, 50);
		instance = this;
	}

	public static Inventory getInventory() {
		if (instance == null)
			new Inventory();
		return instance;
	}

	public void init() {
		try {
			inventoryImage = new Image(inventoryAddress);
			itemDiscr = new Image(itemDiscrAddress);
			character = new Image(characterAddress);
			robot = new Image(robotAddress);
			inventoryText = new Image(inventoryTextAddress);
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 4; j++) {
					itemsTabular[i][j].init();
				}
			}
			for (Item item : weapons) {
				item.init();
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method is for adding a certain addOne to inventory.
	 * 
	 * @param addOne
	 */
	public void add(AddOne addOne, int place) {
		weapons[place].add(addOne);
	}

	public void draw() {
		inventoryImage.draw(pos.getX(), pos.getX());
		character.draw(pos.getX() + 50, pos.getY() + 45);
		itemDiscr.draw(pos.getX() + 343, pos.getY() + 45);
		robot.draw(pos.getX() + 50, pos.getY() + 200);
		inventoryText.draw(pos.getX() + 343, pos.getY() + 250);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				itemsTabular[i][j].draw();
			}
		}
		for (Item item : weapons) {
			item.draw();
		}
	}
	
	public void update(GameContainer gc){
		for (Item item : weapons) {
			item.update(gc);
		}
	}

}
