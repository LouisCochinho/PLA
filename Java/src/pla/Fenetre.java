package pla;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Fenetre extends BasicGame{
	
	private GameContainer gc;
	private Map map;

	public Fenetre(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	// Initialise le contenu du jeu, charge les graphismes, la musique, etc..
	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		this.gc = gc;
		this.map = new Map();
	}
	
	// Affiche le contenu du jeu
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
	  /*g.setColor( Color.white );
	    g.drawLine( 100, 150, 300, 350);
		g.setColor( new Color( 128, 128, 128 ) );
		g.drawString( "Basic font test", 0, 0);*/
		this.map.paint(new Personnage(Color.red,2,3,"res/perso_bleu.gif"),g);
		
		//this.map.placerAutomate(new Automate(10,10), g);
	}

	//Met à jour les éléments de la scène en fonction du delta temps survenu. C'est ici que la logique du jeu est enfermé.
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub		
	}
	
	//Arreter correctement le jeu
	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}
}
