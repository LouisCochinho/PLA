package pla.ihm;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
	 public static final int ID = 1;
	 //private Image fond;
	 //private StateBasedGame game;
	 Image play;
	 
	public Menu(int state){
		
	}
	public void init(GameContainer gc,StateBasedGame sbg) throws SlickException{
	    //this.game = game;
	    //this.fond = new Image("res/menu/back.jpg");
		play= new Image("res/menu/play.png");
	}
	public void render(GameContainer gc,StateBasedGame sbg, Graphics g) throws SlickException {
		//fond.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString("Welcome à Thug City", 100, 50);  
		play.draw(100,100);
	}
	public void update(GameContainer gc,StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		if((posX>100 && posX<311)&&(posY>209 && posY<260)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}
	}
	/*public void keyReleased(int key, char c) {
		game.enterState(Map.ID);
	}
	*/
	public int getID() {
		return 0;
	}

}
