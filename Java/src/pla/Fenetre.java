package pla;

import java.util.ArrayList;
import java.util.List;

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

		// A mettre ailleurs
		List<Personnage> persos = new ArrayList<Personnage>();
		persos.add(new Personnage(Color.black,10,10,"res/perso_bleu.gif"));
		persos.add(new Personnage(Color.green,20,20,"res/perso_vert.png",new Automate(10,10)));
		persos.add(new Personnage(Color.blue,15,15,"res/cop.png",new Automate(1,1)));
		this.map.paint(persos,g);

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
