package game.cells;

import game.objects.Thing;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DownRightCell extends Cell {

	public DownRightCell(int row, int column) {
		super(row, column);
		// TODO Auto-generated constructor stub
	}
	
	public DownRightCell(int row, int column, Thing thing) {
		super( row , column , thing ) ;
	}
	
	public DownRightCell(int row, int column, boolean isNoun) {
		super( row , column , isNoun ) ;
	}
	/**
	 * Loads image
	 */
	public void init(){
		try {
			image = new Image("pics/cells/image test8.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
