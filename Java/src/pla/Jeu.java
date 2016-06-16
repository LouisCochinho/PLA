package pla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import pla.action.transition.*;
import pla.decor.*;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pla.ihm.Camera;
import pla.ihm.Map;
import pla.util.Musique;


public class Jeu extends BasicGameState {
	private Map map ; // carte du jeu
	private List<Personnage> personnages = new ArrayList<Personnage>(); // Liste
																		// des
	public static final int ID = 1;										// personnages
	
	private GameContainer gc; // conteneur

	Image ImageNoire;
	
	Music test2;
	
	private boolean MusicEnable = true; 
	Image play, play2, play3;
	

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
		
		ImageNoire = new Image("res/ImageNoire.png");
		
		ajouterPersonnage(new Personnage(TypePersonnage.BLEU, 2, 64, 64, new Automate()));
		ajouterPersonnage(new Personnage(TypePersonnage.ROUGE, 1, 64, 64, new Automate()));

		// Marche pas => Revoir sprite policier
		ajouterPersonnage(new Personnage(TypePersonnage.BERNARD, 3, 64, 64, new Automate()));
		
		map = new Map((int)SIZE_WINDOW_X, (int)SIZE_WINDOW_Y, personnages);

		this.map.init();
		Camera.initCamera(map, SIZE_WINDOW_X, SIZE_WINDOW_Y);
		for (Personnage p : personnages) {
			p.init();

		

		}
		
		if (MusicEnable) {
			test2 = new Music("res/thug.ogg");
			//test2.loop();
		}

        this.map.placerAutoRandom(personnages, gc.getGraphics());
		this.map.placerPersonnageRandom(personnages);
                //map.getCaseFromCoord(0, 0).setDecor(new BoucheEgout());
                //map.getCaseFromCoord(640, 640).setDecor(new BoucheEgout());
                //new Dupliquer().executer(personnages.get(0), map.getCaseFromCoord(0, 0), this, 0);
                //new Dupliquer().executer(personnages.get(1), map.getCaseFromCoord(0, 0), this, 0);
                
		play = new Image("res/menu/pause/reprendre.png");
		play2 = new Image("res/menu/pause/accueil.png");
		play3 = new Image("res/menu/pause/quitter.png");

	}

	// Affiche le contenu du jeu
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		if (!gc.isPaused()) {
			Camera.moveCamera(g);

			this.map.afficher();
			for (Personnage p : personnages) {
				p.afficher(g);
			}
			g.drawString(Mouse.getX() + " " +  Mouse.getY(), 10,30);
			
		} else {

			Camera.moveCamera(g);

			this.map.afficher();
			for (Personnage p : personnages) {
				p.afficher(g);
			}
			g.resetTransform();
			ImageNoire.setAlpha(0.7f);
			ImageNoire.draw(0,0,gc.getWidth(),gc.getHeight());   // Taille de la fenetre modifiée par Antoine
			play.draw(gc.getWidth()/2-175,12*gc.getHeight()/20-37);
			play2.draw(gc.getWidth()/2-175,15*gc.getHeight()/20-37);
			play3.draw(gc.getWidth()/2-175,18*gc.getHeight()/20-37);
			g.setColor(Color.white);
			g.drawString("PAUSE",gc.getWidth()/2-20,4*gc.getHeight()/10);
			g.drawString(Mouse.getX() + " " +  Mouse.getY(), 10,30);
			
		}
	}

	// Met � jour les �l�ments de la sc�ne en fonction du delta temps
	// survenu.
	// C'est ici que la logique du jeu est enferm�e.
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		
		for (Personnage p : personnages) {
			if(p.isDeplacementTermine()){
				changerEtatAutomate(p, delta);
			}
			deplacerPersonnage(p, delta);			
		}



		if (gc.getInput().isKeyPressed(Input.KEY_M) && gc.isMusicOn() && MusicEnable) {
			test2.resume();
		}

		if (gc.getInput().isKeyPressed(Input.KEY_P) && MusicEnable) {
			test2.pause();
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
		
		if(gc.isPaused()) {
			
			if((posX>gc.getWidth()/2-175 && posX<gc.getWidth()/2+175)&&(posY>8*gc.getHeight()/20-37 && posY<8*gc.getHeight()/20+37)){ // Reprendre
				if(Mouse.isButtonDown(0)){
					gc.setPaused(!gc.isPaused());
				}
			}
			
			if((posX>gc.getWidth()/2-175 && posX<gc.getWidth()/2+175)&&(posY>5*gc.getHeight()/20-37 && posY<5*gc.getHeight()/20+37)){ // Accueil
				if(Mouse.isButtonDown(0)){
					gc.setPaused(!gc.isPaused());
					game.enterState(0);
					gc.reinit();
				}
			}
			
			if((posX>gc.getWidth()/2-175 && posX<gc.getWidth()/2+175)&&(posY>2*gc.getHeight()/20-37 && posY<2*gc.getHeight()/20+37)){ // Quitter
				if(Mouse.isButtonDown(0)){
					gc.exit();
				}
			}
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
		int etatCourantId = p.getEtatCourant().getId();
		Random r = new Random();
		int indexChoisi = 0;
		
		for (int i = 0; i < p.getAutomate().getNbLignes(); i++) {
			Condition c = p.getAutomate().getTabCondition()[i][etatCourantId];
			if(c.nombreConditions()!=0&&c.estVerifiee(p, map)){
				indexPossibles.add(i);
			}
		}
		
		// Affichage test
		//System.out.println(p.toString());
		//System.out.println(this.map.getCaseFromCoord((int)p.getX(), (int)p.getY()).getDecor().toString());
		
		if (!indexPossibles.isEmpty()) {
			// Prendre un index au hasard dans la liste
			indexChoisi = indexPossibles.get(r.nextInt(indexPossibles.size()));	
			//System.out.println("index choisi : "+indexChoisi);
			//System.out.println("etat suivant : "+p.getAutomate().getTabEtatSuivant()[indexChoisi][etatCourantId].getId());
			p.setEtatCourant(p.getAutomate().getTabEtatSuivant()[indexChoisi][etatCourantId]);
		}
		else{
			p.setEtatCourant(p.getAutomate().getEtatInitial());
		}			
		// initier le mouvement
		//System.out.println("action etat courant : "+p.getEtatCourant().getActionEtat().toString());
		p.setDeplacementCourant(0);	
		
	}
	
	public void deplacerPersonnage(Personnage p, int delta){		
		p.deplacer(delta,map.getLargeur(),map.getHauteur());
	}

    public Map getMap() {
        return map;
    }

	@Override
	public int getID() {
		return ID;
	}

    public List<Personnage> getPersonnages() {
        return personnages;
    }

    public void enter(GameContainer gc, StateBasedGame game) {
    	
    	test2.loop();
    	System.out.println(">>>>>>> ENTRER DANS JEU <<<<<<<"); 
    }
    
    public void leave(GameContainer gc, StateBasedGame game) {
    	System.out.println(">>>>>>> SORTIE DE JEU <<<<<<<");
    }

   
    
}
