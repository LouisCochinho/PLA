package pla.ihm;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import pla.Jeu;
import pla.util.Musique;



public class StateGame extends StateBasedGame {
	
	static private AppGameContainer app;
	
	public static void main(String [] args) throws SlickException {
		app = new AppGameContainer(new StateGame(), 1280, 768, false);
		app.setIcon("res/logo.png");
		app.start();
		
	}
	
	public StateGame() {
		super("Thug tag");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new Menu());
		addState(new Jeu(1280,768));
		
		
	}
}