package game;

import javax.swing.JLabel;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {

	/**
	 * Current working gamefield.
	 */
	private Label cash;
	private Label shield;
	private Map map;
	/**
	 * Player who controls robot
	 */
	private Player player;
	/**
	 * Utility buttons
	 */
	private UtilityButton mapButton;
	private UtilityButton menu;
	private UtilityButton inv;

	/**
	 * Creates new Game with sample gamefield.
	 */
	public Game() {

		map = Map.getMap();
		player = Player.getPlayer() ;
		
		mapButton = new UtilityButton("map");
		inv = new UtilityButton("inv");
		menu = new UtilityButton("menu");
		cash = new Label("cash") ;
		shield = new Label("shield") ;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {

		map.init();
		mapButton.init();
		menu.init();
		inv.init();
		cash.init();
		shield.init();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		map.draw(g);
		mapButton.draw(g);
		
		menu.draw(g);
		cash.draw(g , Map.getMap().getRobot().getHealth() , player.getCash() );
		shield.draw(g , Map.getMap().getRobot().getHealth() , player.getCash());
		inv.draw(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame arg1, int arg2)
			throws SlickException {
		map.update(gc);
		mapButton.update(gc);
		inv.update(gc);
		menu.update(gc);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
