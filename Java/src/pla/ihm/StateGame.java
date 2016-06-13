package pla.ihm;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import pla.Jeu;

public class StateGame extends StateBasedGame {
	
	public static void main(String [] args) throws SlickException {
		new AppGameContainer(new StateGame(), 1280, 768, false).start();
	}
	
	public StateGame() {
		super("Thug tag");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new Menu());
		addState(new Jeu());
		
		
	}
}