package game.objects;

import game.GameField;
import game.Position;
import game.inventory.Inventory;
import game.inventory.Item;
import game.objects.weapons.MissilesDatabase;
import game.objects.weapons.Weapon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * This is a Robot!
 * 
 * @author mahdi
 *
 */
public class Robot {

	/**
	 * Position of robot
	 */
	private Position pos;
	/**
	 * Weapons that robot carries them
	 */
	private Weapon[] weapons;
	/**
	 * Healthy of robot
	 */
	private int health;
	/**
	 * Robot angle
	 */
	private float imageAngleRad = 0;
	private float imageAngleDeg = 0;
	private String imageOfBodyAddress;

	private Image imageOfBody;

	/**
	 * Place for add a gun that ordered from left to right
	 */
	private int[] places = { 0, 0, 0, 0 };
	private Image text;
	/**
	 * Animate robot for moving to up
	 */
	private Animation toUp;
	private boolean iskeyUpPressed = false;

	private Animation fixState;

	private Animation toDown;
	private boolean iskeyDownPressed = false;

	private Animation toRight;
	private boolean isKeyRightPressed = false;

	private Animation toLeft;
	private boolean isKeyLeftPressed = false;

	private Animation toUpRight;

	private Animation toDownLeft;

	private Animation toUpLeft;

	private Animation toDownRight;

	public static Robot instance = null;

	private GameField field;

	private Animation fall;

	private boolean isDead = false;

	private boolean falling = false;
	
	private boolean hasKey = false ;

	private Robot() {
		super();

		weapons = new Weapon[4];
		health = 100;

		imageOfBodyAddress = ("pics/robot/image" + " " + places[0] + places[1]
				+ places[2] + places[3] + ".png");

		health = 100;
		instance = this;
	}

	public static Robot getRobot() {
		if (instance == null)
			new Robot();
		return instance;
	}

	/**
	 * Sets a field that the robot is inside it
	 * 
	 * @param field
	 */
	public void setActiveField(GameField field) {
		this.field = field;
	}

	/**
	 * get robot position
	 * 
	 * @return
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Set an angle for robot rotating
	 * 
	 * @param r
	 */
	public void setImageAngle(float r) {
		imageAngleRad = r;
	}

	public String getImageOfBody() {
		return imageOfBodyAddress;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public void setHasKey( boolean hasKey ){
		this.hasKey = hasKey ;
	}
	
	public boolean getKey(){
		return hasKey ;
	}
	/**
	 * Loads image of robot
	 */
	public void init() {
		try {
			imageOfBody = new Image(this.getImageOfBody());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			text = new Image("pics/game/warning.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toUp = new Animation(createToUpFrames(), 100);
		fixState = new Animation(createFixStateFrame(), 100);
		toDown = new Animation(createToDownFrames(), 100);
		toRight = new Animation(createToRightFrames(), 100);
		toLeft = new Animation(createToLeftFrames(), 100);
		toDownLeft = new Animation(createToDownLeftFrames(), 85);
		toUpRight = new Animation(createToUpRightFrames(), 85);
		toDownRight = new Animation(createToDownRightFrames(), 85);
		toUpLeft = new Animation(createToUpLeftFrames(), 85);
		fall = new Animation(createFallFrames(), 65);
	}

	public Image getText() {
		return text;
	}

	/**
	 * Draw robot with an angle for rotating that based on mouse position
	 * 
	 * @param angle
	 */
	public void draw(Graphics g) {

		for (Weapon gun : weapons) {
			if (gun != null)
				gun.draw();

		}

		// Manage drawing animations
		if (health == 0) {
			if (falling == true) {
				imageOfBody.draw(-50, -50);
				fall.setLooping(false);
				fall.draw(pos.getX() - imageOfBody.getWidth() / 2, pos.getY()
						- imageOfBody.getHeight() / 2);
				fall.start();

				if (fall.getFrame() == 15) {
					falling = false;
					fall = null;
					fall = new Animation(createFallFrames(), 65);
					health = 100;
					isDead = true;
				}
			} else {
				health = 100;
				isDead = true;
			}
		} else {
			if (iskeyUpPressed == true && isKeyRightPressed == true) {
				toUpRight.draw(pos.getX() - imageOfBody.getWidth() / 2,
						pos.getY() - imageOfBody.getHeight() / 2);

			} else if (iskeyDownPressed == true && isKeyLeftPressed == true) {
				toDownLeft.draw(pos.getX() - imageOfBody.getWidth() / 2,
						pos.getY() - imageOfBody.getHeight() / 2);

			} else if (iskeyUpPressed == true && isKeyLeftPressed == true) {
				toUpLeft.draw(pos.getX() - imageOfBody.getWidth() / 2,
						pos.getY() - imageOfBody.getHeight() / 2);

			} else if (iskeyDownPressed == true && isKeyRightPressed == true) {
				toDownRight.draw(pos.getX() - imageOfBody.getWidth() / 2,
						pos.getY() - imageOfBody.getHeight() / 2);

			} else if (iskeyUpPressed == true) {
				toUp.draw(pos.getX() - imageOfBody.getWidth() / 2, pos.getY()
						- imageOfBody.getHeight() / 2);

			}

			else if (iskeyDownPressed == true) {
				toDown.draw(pos.getX() - imageOfBody.getWidth() / 2, pos.getY()
						- imageOfBody.getHeight() / 2);

			}

			else if (isKeyRightPressed == true) {
				toRight.draw(pos.getX() - imageOfBody.getWidth() / 2,
						pos.getY() - imageOfBody.getHeight() / 2);
			} else if (isKeyLeftPressed == true) {
				toLeft.draw(pos.getX() - imageOfBody.getWidth() / 2, pos.getY()
						- imageOfBody.getHeight() / 2);
			} else {
				fixState.draw(pos.getX() - imageOfBody.getWidth() / 2,
						pos.getY() - imageOfBody.getHeight() / 2);
			}
			imageOfBody.setRotation(imageAngleDeg);
			imageOfBody.drawCentered(pos.getX(), pos.getY());
		}

	}

	public void setFalling(boolean x) {
		falling = x;
	}

	/**
	 * Updates robot state in GameContainer gc
	 * 
	 * @param gc
	 */
	public void update(GameContainer gc) {
		float xPos = this.getPos().getX();
		float yPos = this.getPos().getY();

		Input input = gc.getInput();
		if (health != 0) {
			if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
				if (yPos > 25)
					yPos -= 0.25;
				iskeyUpPressed = true;
			} else
				iskeyUpPressed = false;
			if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
				if (yPos < 575)
					yPos += 0.25;
				iskeyDownPressed = true;
			} else
				iskeyDownPressed = false;
			if (input.isKeyDown(Input.KEY_RIGHT)
					|| input.isKeyDown(Input.KEY_D)) {
				if (xPos < 775)
					xPos += 0.25;
				isKeyRightPressed = true;
			} else
				isKeyRightPressed = false;
			if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
				if (xPos > 25)
					xPos -= 0.25;
				isKeyLeftPressed = true;
			} else
				isKeyLeftPressed = false;

			if (field.isNounCell(xPos, yPos)) {
				System.out.println(field.getModel().getCellWithPos(xPos, yPos)
						.getIsNoun());
				System.out.println("in Robot , line 420 "
						+ this.getPos().getX() + " " + this.getPos().getY());
				falling = true;
				this.health = 0;
			}

			if (field.isValidPos(new Position(xPos, yPos))) {
				this.setPos(new Position(xPos, yPos));
			}

			double dx = input.getMouseX() - this.getPos().getX();
			double dy = input.getMouseY() - this.getPos().getY();
			imageAngleRad = (float) (Math.atan2(dy, dx) - Math.PI / 2);

			imageAngleDeg = (float) (imageAngleRad * 180 / Math.PI);

			if (input.isMouseButtonDown(0))
				fire();
		}

		for (Weapon gun : weapons) {
			if (gun != null)
				gun.update(gc);
		}

		// Updates gun from inventory
		Item[] items = Inventory.getInventory().getWeaponsItems();
		for (int i = 0; i < 4; i++) {
			if (items[i].getAddOne() != null && places[i] == 0) {
				((Weapon) items[i].getAddOne()).setPlace(i);
				addGun((Weapon) items[i].getAddOne(), i);
			} else if (items[i].getAddOne() == null && places[i] == 1)
				remGun(i);

		}

		int healthReduction = MissilesDatabase.getMissilesDatabase()
				.explodeAreaForRobot(
						new Position(pos.getX() - 22.5f, pos.getY() - 22.5f),
						45, 45);

		if (healthReduction > health)
			health = 0;
		else
			health -= healthReduction;
	}

	/**
	 * Fires missiles
	 */
	public void fire() {

		for (Weapon gun : weapons) {
			if (gun != null)
				gun.shot(imageAngleRad, pos, imageOfBody.getWidth() * 70 / 100);
		}
	}

	public boolean getIsDead() {
		return isDead;
	}

	public void setIsDead(boolean s) {
		isDead = s;
	}

	/**
	 * Add a gun to robot in specified place
	 * 
	 * @param gun
	 * @param place
	 */
	public void addGun(Weapon gun, int place) {
		weapons[place] = gun;
		places[place] = 1;
		imageOfBodyAddress = ("pics/robot/image" + " " + places[0] + places[1]
				+ places[2] + places[3] + ".png");
		// Destroy previous image of body
		try {
			imageOfBody.destroy();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create new image of body
		try {
			imageOfBody = new Image(this.getImageOfBody());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Remove a gun from robot that exists in certain place
	 * 
	 * @param index
	 */
	public void remGun(int index) {
		weapons[index] = null;
		places[index] = 0;

		imageOfBodyAddress = ("pics/robot/image" + " " + places[0] + places[1]
				+ places[2] + places[3] + ".png");
		// Destroy previous image of body
		try {
			imageOfBody.destroy();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create new image of body
		try {
			imageOfBody = new Image(this.getImageOfBody());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Create frames of an animation
	private Image[] createToUpFrames() {
		Image[] frames = new Image[5];

		try {
			Image image1 = new Image("pics/robot/image 123.png");
			frames[0] = image1;
			Image image2 = new Image("pics/robot/image 231.png");
			frames[1] = image2;
			Image image3 = new Image("pics/robot/image 485.png");
			frames[2] = image3;
			Image image4 = new Image("pics/robot/image 653.png");
			frames[3] = image4;
			Image image5 = new Image("pics/robot/image 589.png");
			frames[4] = image5;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frames;
	}

	// Create frames of an animation
	private Image[] createFixStateFrame() {
		Image[] frames = new Image[1];

		try {
			Image image1 = new Image("pics/robot/image 123.png");
			frames[0] = image1;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return frames;
	}

	// Create frames of an animation
	private Image[] createToDownFrames() {

		Image[] frames = new Image[5];

		try {
			Image image1 = new Image("pics/robot/image 123.png");
			frames[4] = image1;
			Image image2 = new Image("pics/robot/image 231.png");
			frames[3] = image2;
			Image image3 = new Image("pics/robot/image 485.png");
			frames[2] = image3;
			Image image4 = new Image("pics/robot/image 653.png");
			frames[1] = image4;
			Image image5 = new Image("pics/robot/image 589.png");
			frames[0] = image5;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return frames;

	}

	// Create frames of an animation
	private Image[] createToRightFrames() {

		Image[] frames = new Image[5];

		try {
			Image image1 = new Image("pics/robot/image 1231.png");
			frames[4] = image1;
			Image image2 = new Image("pics/robot/image 2311.png");
			frames[3] = image2;
			Image image3 = new Image("pics/robot/image 4851.png");
			frames[2] = image3;
			Image image4 = new Image("pics/robot/image 6531.png");
			frames[1] = image4;
			Image image5 = new Image("pics/robot/image 5891.png");
			frames[0] = image5;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frames;

	}

	// Create frames of an animation
	private Image[] createToLeftFrames() {

		Image[] frames = new Image[5];

		try {
			Image image1 = new Image("pics/robot/image 1231.png");
			frames[0] = image1;
			Image image2 = new Image("pics/robot/image 2311.png");
			frames[1] = image2;
			Image image3 = new Image("pics/robot/image 4851.png");
			frames[2] = image3;
			Image image4 = new Image("pics/robot/image 6531.png");
			frames[3] = image4;
			Image image5 = new Image("pics/robot/image 5891.png");
			frames[4] = image5;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frames;

	}

	private Image[] createToUpRightFrames() {

		Image[] frames = new Image[5];

		try {
			Image image1 = new Image("pics/robot/image 1232.png");
			frames[0] = image1;
			Image image2 = new Image("pics/robot/image 2312.png");
			frames[1] = image2;
			Image image3 = new Image("pics/robot/image 4852.png");
			frames[2] = image3;
			Image image4 = new Image("pics/robot/image 6532.png");
			frames[3] = image4;
			Image image5 = new Image("pics/robot/image 5892.png");
			frames[4] = image5;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frames;

	}

	private Image[] createToDownLeftFrames() {

		Image[] frames = new Image[5];

		try {
			Image image1 = new Image("pics/robot/image 1232.png");
			frames[4] = image1;
			Image image2 = new Image("pics/robot/image 2312.png");
			frames[3] = image2;
			Image image3 = new Image("pics/robot/image 4852.png");
			frames[2] = image3;
			Image image4 = new Image("pics/robot/image 6532.png");
			frames[1] = image4;
			Image image5 = new Image("pics/robot/image 5892.png");
			frames[0] = image5;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frames;

	}

	private Image[] createToDownRightFrames() {

		Image[] frames = new Image[5];

		try {
			Image image1 = new Image("pics/robot/image 1233.png");
			frames[4] = image1;
			Image image2 = new Image("pics/robot/image 2313.png");
			frames[3] = image2;
			Image image3 = new Image("pics/robot/image 4853.png");
			frames[2] = image3;
			Image image4 = new Image("pics/robot/image 6533.png");
			frames[1] = image4;
			Image image5 = new Image("pics/robot/image 5893.png");
			frames[0] = image5;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frames;

	}

	private Image[] createToUpLeftFrames() {

		Image[] frames = new Image[5];

		try {
			Image image1 = new Image("pics/robot/image 1233.png");
			frames[0] = image1;
			Image image2 = new Image("pics/robot/image 2313.png");
			frames[1] = image2;
			Image image3 = new Image("pics/robot/image 4853.png");
			frames[2] = image3;
			Image image4 = new Image("pics/robot/image 6533.png");
			frames[3] = image4;
			Image image5 = new Image("pics/robot/image 5893.png");
			frames[4] = image5;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frames;
	}

	// Creates fall animation frames
	private Image[] createFallFrames() {
		Image[] frames = new Image[16];
		try {
			for (int i = 0; i < frames.length; i++) {
				int x = i+1 ;
				frames[i] = new Image("pics/robot/fall " + x + ".png");
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frames;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int x) {
		health = x;
	}
}
