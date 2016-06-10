package pla.ihm;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class StateGame extends StateBasedGame {
	  public static void main(String[] args) throws SlickException {
	    new AppGameContainer(new StateGame(), 800, 600, false).start();
	  }
	  public StateGame() {
	    super("Lesson 15 :: StateGame");
	  }
	  public void initStatesList(GameContainer container) throws SlickException {
	    addState(new Menu(2));
	    addState((GameState)new Map());
	  }
	}