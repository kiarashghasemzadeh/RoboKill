package game.objects.weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.Position;

public class LightBlasterMissile extends BlasterMissile {

	public LightBlasterMissile(float angle, Position pos) {
		super(angle, pos);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		try {
			img = new Image("pics/fires/image 693.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
