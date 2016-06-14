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
import pla.action.transition.Construire;
import pla.action.transition.Demolir;
import pla.action.transition.Voyager;
import pla.decor.BoucheEgout;
import pla.ihm.Map;

public class Jeu extends BasicGame {
	private Map map ; // carte du jeu
	private List<Personnage> personnages = new ArrayList<Personnage>(); // Liste
	// des
	// personnages
	private GameContainer gc; // conteneur
	private float camX, camY;
	private float zoomX=1, zoomY=1;
	private final static float DEPLACEMENT = 13;
	private int SIZE_WINDOW_X ;
	private int SIZE_WINDOW_Y ;
	private float currentSizeMapX ;
	private float currentSizeMapY ;
	private final static float ZOOM = 0.03f;
	private final static float ZOOM_MAX = 5;
	// private static final int PAUSE = 25; // temps de latence

	// private float zoom = 0.1f;
	Music sound;

	/*
	 * private float z1 = 0.01f; private float z2 = 0.01f;
	 */

	public Jeu(String titre,int largeur,int hauteur) {
		super(titre); // Nom du jeu
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
	public void init(GameContainer gc) throws SlickException {

		this.gc = gc;
		
		ajouterPersonnage(new Personnage("res/thugBleu.png", 2, 64, 64, new Automate(), Color.blue));
		ajouterPersonnage(new Personnage("res/thugRouge.png", 1, 64, 64, new Automate(), Color.red));

		// Marche pas => Revoir sprite policier
		ajouterPersonnage(new Personnage("res/Bernard.png", 3, 64, 64, new Automate(), Color.black));
		
		map = new Map((int)SIZE_WINDOW_X, (int)SIZE_WINDOW_Y, personnages);

		this.map.init();
		currentSizeMapX = map.getLargeur();
		currentSizeMapY = map.getHauteur();
		for (Personnage p : personnages) {
			p.init();

			//this.map.placerAutomate(p.getAutomate(), p.getCouleur(), gc.getGraphics());

		}
        this.map.placerAutoRandom(personnages, gc.getGraphics());
		this.map.placerPersonnageRandom(personnages);
                map.getCaseFromCoord(1, 0).setDecor(new BoucheEgout());
                new Voyager().executer(personnages.get(0), map.getCaseFromCoord(0, 0), map, 0);
                //System.out.println(map.getCaseFromCoord(0, 0).getDecor());
                
	//	sound = new Music("res/thug.ogg");
	//	sound.loop();

	}

	// Affiche le contenu du jeu
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.translate(camX, camY);
		g.scale(zoomX, zoomY);
		this.map.afficher();
		for (Personnage p : personnages) {
			p.afficher(g);
		}
	}

	// Met � jour les �l�ments de la sc�ne en fonction du delta temps
	// survenu.
	// C'est ici que la logique du jeu est enferm�e.
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		for (Personnage p : personnages) {
			if(p.isDeplacementTermine()){
				//System.out.println("X = "+p.getX()+" y = "+p.getY());
				changerEtatAutomate(p, delta);
			}
			deplacerPersonnage(p, delta);
		}



		if (gc.getInput().isKeyPressed(Input.KEY_M) && gc.isMusicOn()) {
			sound.resume();
		}
		if (gc.getInput().isKeyPressed(Input.KEY_S)) {
			sound.stop();
		}
		if (gc.getInput().isKeyPressed(Input.KEY_P)) {
			sound.pause();
		}	
		if (gc.getInput().isKeyDown(Input.KEY_UP)) {
			cameraUP();		
		}
		if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			cameraDown();
		} 
		if(gc.getInput().isKeyDown(Input.KEY_RIGHT)){
			cameraRIGHT();		
		}
		if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			cameraLEFT();	
		} 
		if (gc.getInput().isKeyDown(Input.KEY_A)) {
			cameraZoom();
		}
		if (gc.getInput().isKeyDown(Input.KEY_B)) {
			cameraDezoom();
		}
		if(gc.getInput().isKeyPressed(Input.KEY_F1)){
			gc.setPaused(!gc.isPaused());
		}

	}
	
	public void mouseWheelMoved(int change) {
		if(change<0){
			cameraDezoom();
		}
		else{
			cameraZoom();
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
			if(c.estVerifiee(p, map)){
				indexPossibles.add(i);
			}
		}

		if (!indexPossibles.isEmpty()) {
			// Prendre un index au hasard dans la liste
			indexChoisi = indexPossibles.get(r.nextInt(indexPossibles.size()));	
			p.getAutomate().setEtatCourant(p.getAutomate().getTabEtatSuivant()[indexChoisi][etatCourantId]);
		}
		else{
			p.getAutomate().setEtatCourant(p.getAutomate().getEtatInitial());
		}			
		// initier le mouvement
		p.setDeplacementCourant(0);		
	}
	
	public void deplacerPersonnage(Personnage p, int delta){		
		p.deplacer(delta,map.getLargeur(),map.getHauteur());
	}

	void cameraDown(){
		if(camY-DEPLACEMENT >= -currentSizeMapY+SIZE_WINDOW_Y){camY-=DEPLACEMENT;}
		else{camY = camY - (camY +currentSizeMapY-SIZE_WINDOW_Y);}		
		
	}

	void cameraUP(){
		if(camY+DEPLACEMENT <= 0){camY+=DEPLACEMENT;}
		else{camY = 0;}
		
	}

	void cameraLEFT(){
		if(camX+DEPLACEMENT <= 0){camX+=DEPLACEMENT;}
		else{camX = 0;}
		
	}

	void cameraRIGHT(){
		if(camX-DEPLACEMENT >= -currentSizeMapX+SIZE_WINDOW_X){camX-= DEPLACEMENT;}
		else{camX = camX - (camX +currentSizeMapX-SIZE_WINDOW_X);}
		
	}

	void cameraDezoom(){
		float lastSizeMapX = currentSizeMapX;
		float lastSizeMapY = currentSizeMapY;
		if((zoomX-ZOOM)*map.getLargeur() >= SIZE_WINDOW_X && (zoomY-ZOOM)*map.getHauteur() >= SIZE_WINDOW_Y
			/*	&& currentSizeMapX+camX < SIZE_WINDOW_X && camX<=0*/){
			zoomX -= ZOOM; zoomY -= ZOOM;
			currentSizeMapX = zoomX*map.getLargeur();
			currentSizeMapY = zoomY*map.getHauteur();
			camX = camX-(currentSizeMapX-lastSizeMapX)/2;
			camY = camY-(currentSizeMapY-lastSizeMapY)/2;
		}
		/*else if(currentSizeMapX+camX >= SIZE_WINDOW_X){
			camX=0;
		}*/
		else{
			if(map.getLargeur() >= map.getHauteur()){
				zoomX = SIZE_WINDOW_X/(float)map.getLargeur();
				zoomY = zoomX;
			}
			else{
				zoomY = SIZE_WINDOW_Y/(float)map.getHauteur();
				zoomX = zoomY;
			}
		}
		if(currentSizeMapX+camX<SIZE_WINDOW_X){camX = camX+(SIZE_WINDOW_X-currentSizeMapX-camX);}
		if(currentSizeMapY+camY<SIZE_WINDOW_Y){camY = camY+(SIZE_WINDOW_Y-currentSizeMapY-camY);}

	}

	void cameraZoom(){
		float lastSizeMapX = currentSizeMapX;
		float lastSizeMapY = currentSizeMapY;
		if(zoomX<=ZOOM_MAX && zoomY<=ZOOM_MAX){
			zoomX += ZOOM; zoomY += ZOOM;
			currentSizeMapX = zoomX*map.getLargeur();
			currentSizeMapY = zoomY*map.getHauteur();
			camX = camX-(currentSizeMapX-lastSizeMapX)/2;
			camY = camY-(currentSizeMapY-lastSizeMapY)/2;
		}		
	}
	
}
