package pla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import pla.action.transition.Construire;
import pla.action.transition.Demolir;



import pla.ihm.Camera;

import pla.ihm.Map;
import pla.util.Musique;


public class Jeu extends BasicGameState {
	private Map map ; // carte du jeu
	private List<Personnage> personnages = new ArrayList<Personnage>(); // Liste
																		// des
	public static final int ID = 1;										// personnages
	
	private GameContainer gc; // conteneur

	Musique musique;

	private int SIZE_WINDOW_X ;
	private int SIZE_WINDOW_Y ;
	// private static final int PAUSE = 25; // temps de latence

	// private float zoom = 0.1f;
	

	/*
	 * private float z1 = 0.01f; private float z2 = 0.01f;
	 */


	public Jeu(int largeur,int hauteur) {
		SIZE_WINDOW_X = largeur;

		SIZE_WINDOW_Y = hauteur;

		personnages = new ArrayList<Personnage>();
	}

	public void ajouterPersonnage(Personnage p) {
		if (p != null) {
			this.personnages.add(p);
		} else {
			System.out.println("Le personnage que vous voulez ajouter dans la liste des personnages est vide");
		}
	}

	public void supprimerPersonnage(Personnage p) {
		if (p != null && this.personnages.contains(p)) {
			this.personnages.remove(p);
		} else {
			System.out.println("Le personnage que vous voulez supprimer n'est pas dans la liste ou est nul");
		}
	}

	// Initialise le contenu du jeu, charge les graphismes, la musique, etc..
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {

		this.gc = gc;
		
		ajouterPersonnage(new Personnage("res/thugBleu.png", 2, 64, 64, new Automate(), Color.blue));
		ajouterPersonnage(new Personnage("res/thugRouge.png", 1, 64, 64, new Automate(), Color.red));

		// Marche pas => Revoir sprite policier
		ajouterPersonnage(new Personnage("res/Bernard.png", 3, 64, 64, new Automate(), Color.black));
		
		map = new Map((int)SIZE_WINDOW_X, (int)SIZE_WINDOW_Y, personnages);

		this.map.init();
		Camera.initCamera(map, SIZE_WINDOW_X, SIZE_WINDOW_Y);
		for (Personnage p : personnages) {
			p.init();

		
		// this.map.placerPersonnageRandom(personnages);
		//sound = new Music("res/thug.ogg");
		//musique = new Musique();
		

			//this.map.placerAutomate(p.getAutomate(), p.getCouleur(), gc.getGraphics());


			//this.map.placerAutomate(p.getAutomate(), p.getCouleur(), gc.getGraphics());
		}

        this.map.placerAutoRandom(personnages, gc.getGraphics());
		this.map.placerPersonnageRandom(personnages);
                //new Construire().executer(personnages.get(0), map.getCaseFromCoord(0, 0), 0);
                //System.out.println(map.getCaseFromCoord(0, 0).getDecor());
	//	sound = new Music("res/thug.ogg");
	//	sound.loop();

	}

	// Affiche le contenu du jeu
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {

		Camera.moveCamera(g);

		this.map.afficher();
		for (Personnage p : personnages) {
			p.afficher(g);
		}
	}

	// Met � jour les �l�ments de la sc�ne en fonction du delta temps
	// survenu.
	// C'est ici que la logique du jeu est enferm�e.
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		for (Personnage p : personnages) {
			if(p.isDeplacementTermine()){
				changerEtatAutomate(p, delta);
			}
			deplacerPersonnage(p, delta);			
		}



		if (gc.getInput().isKeyPressed(Input.KEY_M) && gc.isMusicOn()) {
			musique.resumeJeu();
		}
		if (gc.getInput().isKeyPressed(Input.KEY_S)) {
			musique.stopJeu();
		}
		if (gc.getInput().isKeyPressed(Input.KEY_P)) {
			musique.pauseJeu();
		}	
		if (gc.getInput().isKeyDown(Input.KEY_UP)) {
			Camera.cameraUP();		
		}
		if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			Camera.cameraDown();
		} 
		if(gc.getInput().isKeyDown(Input.KEY_RIGHT)){
			Camera.cameraRIGHT();		
		}
		if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			Camera.cameraLEFT();	
		} 

		if (gc.getInput().isKeyDown(Input.KEY_A)) {
			Camera.cameraZoom(map);
		}
		if (gc.getInput().isKeyDown(Input.KEY_B)) {
			Camera.cameraDezoom(map);
		}
		if(gc.getInput().isKeyPressed(Input.KEY_F1)){
			gc.setPaused(!gc.isPaused());
		}

	}
	
	public void mouseWheelMoved(int change) {
		if(change<0){
			Camera.cameraDezoom(map);
		}
		else{
			Camera.cameraZoom(map);
		}
	}

	// Arreter correctement le jeu en appuyant sur ECHAP
	@Override
	public void keyReleased(int key, char c) {

		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}
	
	

	public void changerEtatAutomate(Personnage p, int delta) {

		ArrayList<Integer> indexPossibles = new ArrayList<Integer>();
		int etatCourantId = p.getAutomate().getEtatCourant().getId();
		Random r = new Random();
		int indexChoisi = 0;
		
		for (int i = 0; i < p.getAutomate().getNbLignes(); i++) {
			Condition c = p.getAutomate().getTabCondition()[i][etatCourantId];
			if(c.nombreConditions()!=0&&c.estVerifiee(p, map)){
				indexPossibles.add(i);
			}
		}
		
		// Affichage test
		System.out.println(p.toString());
		System.out.println(this.map.getCaseFromCoord((int)p.getX(), (int)p.getY()).getDecor().toString());
		
		if (!indexPossibles.isEmpty()) {
			// Prendre un index au hasard dans la liste
			indexChoisi = indexPossibles.get(r.nextInt(indexPossibles.size()));	
			System.out.println("index choisi : "+indexChoisi);
			System.out.println("etat suivant : "+p.getAutomate().getTabEtatSuivant()[indexChoisi][etatCourantId].getId());
			p.getAutomate().setEtatCourant(p.getAutomate().getTabEtatSuivant()[indexChoisi][etatCourantId]);
		}
		else{
			p.getAutomate().setEtatCourant(p.getAutomate().getEtatInitial());
		}			
		// initier le mouvement
		System.out.println("action etat courant : "+p.getAutomate().getEtatCourant().getActionEtat().toString());
		p.setDeplacementCourant(0);	
		
	}
	
	public void deplacerPersonnage(Personnage p, int delta){		
		p.deplacer(delta,map.getLargeur(),map.getHauteur());
	}


	@Override
	public int getID() {
		return ID;
	}
		

	
}
