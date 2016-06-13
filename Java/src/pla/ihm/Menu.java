package pla.ihm;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.*;

import pla.Jeu;

public class Menu extends BasicGameState {
	 public static final int ID = 0;
	 //private Image fond;
	 private StateBasedGame game;
	 Image background;
	 Image play, play2;
	 Image exitGame;
	 Music sound;
	 MouseOverArea ms, ms2;

	public Menu() {

	}
	public void init(GameContainer gc, StateBasedGame game) throws SlickException{
	    //this.game = game;
	    //this.fond = new Image("res/menu/back.jpg");
		this.background = new Image("res/Menu.jpg");
		play = new Image("res/menu/jouer.png");
		play2 = new Image("res/menu/moche.png");
		ms = new MouseOverArea(gc, play, 300, 300, 200, 100);
		ms2 = new MouseOverArea(gc, play2, 300, 300, 200, 100);
		//sound = new Music("res/thug.ogg");
		//sound.loop();
		
	}
	public void render(GameContainer gc,StateBasedGame sbg, Graphics g) throws SlickException {
		//fond.draw(0, 0, container.getWidth(), container.getHeight());
		background.draw(0, 0, gc.getWidth(), gc.getHeight());
		g.drawString("Appuyer sur une touche", 500, 500);
		//play.draw(640,384);
		g.drawString(Mouse.getX() + " " +  Mouse.getY(), 10,30);
		g.drawString(Integer.toString(ms.getHeight()) +" " + Integer.toString(ms.getWidth() ), 10, 50);
		if (ms.isMouseOver()) { ms.render(gc, g);} else { ms2.render(gc, g);}
	}
	public void update(GameContainer gc,StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		
		
		if((posX>150 && posX<250)&&(posY>150 && posY<250)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}
		
	}
	public void keyReleased(int key, char c) {
		//game.enterState(1);
	}
	
	public int getID() {
		return 0;
	}

}
