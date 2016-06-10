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

import pla.decor.Decor;
import pla.ihm.Case;
import pla.ihm.Map;

public class Jeu extends BasicGame {
	private Map map = new Map(); // carte du jeu
	private List<Personnage> personnages = new ArrayList<Personnage>(); // Liste
																		// des
																		// personnages
	private GameContainer gc; // conteneur
	private int camX, camY;
	private final static int DEPLACEMENT = 15;
	// private static final int PAUSE = 25; // temps de latence

	//private float zoom = 0.1f;
	Music sound;

	/*
	 * private float z1 = 0.01f; private float z2 = 0.01f;
	 */

	public Jeu(String titre) {
		super(titre); // Nom du jeu

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
		this.map.init();
		ajouterPersonnage(
				new Personnage("res/thugBleu.png", 400.f, 400.f, 2, 0, 64, 64, new Automate(), Color.blue));
		ajouterPersonnage(
				new Personnage("res/thugRouge.png", 300.f, 300.f, 1, 0, 64, 64, new Automate(), Color.green));

		// Marche pas => Revoir sprite policier
		ajouterPersonnage(new Personnage("res/Bernard.png",200.f,200.f,3,0,64,64, new Automate(), Color.green));

		for (Personnage p : personnages) {
			p.init();
			this.map.placerAutoRandom(personnages);
			//this.map.placerAutomate(p.getAutomate(), p.getCouleur(), gc.getGraphics());
			
		}
		this.map.placerPersonnageRandom(personnages);
		sound = new Music("res/thug.ogg");
		sound.loop();
	}

	// Affiche le contenu du jeu
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.translate(camX, camY);
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
				camY+=DEPLACEMENT;
		}
		if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			camY-=DEPLACEMENT;
		} 
		if(gc.getInput().isKeyDown(Input.KEY_RIGHT)){
			camX-= DEPLACEMENT;
		}
		if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			camX+=DEPLACEMENT;
		} 
	}

	// Arreter correctement le jeu en appuyant sur ECHAP
	@Override
	public void keyReleased(int key, char c) {

		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}

	/*
	 * 
	 * public void dessinerCarte(Graphics g) { this.map.paint(personnages, g); }
	 * 
	 * public void dessinerPersonnages(Graphics g) { for (Personnage p :
	 * personnages) { map.placerPersonnage(p, g); // sale
	 * map.dessinerContoursAutomate(p, g); } }
	 * 
	 * public void dessinerElements(Graphics g) { // Pour chaque persoonage de
	 * la liste de personnages, le dessiner et // dessiner son automate for
	 * (Personnage p : personnages) { // map.placerAutoRandom(personnages);
	 * map.placerAutomate(p.getAutomate(), p.getCouleur(), g); //
	 * map.placerPersonnageRandom( personnages); map.placerPersonnage(p, g);
	 * 
	 * } }
	 */
	public void deplacerPersonnage(Personnage p, int delta) {

		// Prendre sa couleur et ses coordonn��es => conversion � v�rifier
		int coordX = (int) p.getCoordX();
		int coordY = (int) p.getCoordY();
		// La case sur lequel le personnage etait doit revenir a son etat
		// d'origine
		// map.modifierDecorCase(coordI, coordJ, getImageParCouleur(couleur));
		// On enleve le personnage p a la liste des personnages de la case que
		// le personnage s'apprete � quitter
		// map.getCase(coordI, coordJ).supprimerPersonnage(p);

		// Liste des indices en J possibles : indexJ d'une condition possible
		// dans le tableau tabCondition de l'automate du joueur p
		ArrayList<Integer> indexPossibles = new ArrayList<Integer>();
		// id de l'�tat courant du joueur p
		int etatCourantId = p.getAutomate().getEtatCourant().getId();
		// boolean pour savoir si une condition simple est v�rifi�e
		boolean conditionVerifiee;


		for (int i = 0; i < p.getAutomate().getNbLignes(); i++) {
			Condition c = p.getAutomate().getTabCondition()[i][etatCourantId];
			conditionVerifiee = true;
			// Verifier si chaque condition simple est vraie
			for (ConditionSimple cs : c.getConditions()) {
				// si la case au NORD|SUD|EST|OUEST|CASE de la case sur laquelle se trouve le personnage
				// contient le decor contenu dans condition simple alors la conditionSimple est verifiée


				Case caseCourante = map.getCase(coordX, coordY);
				Cellule cell = cs.getCellule();
				Case caseOrientee = map.getCase(caseCourante, cell);
				Decor decorCaseOrientee = caseOrientee.getDecor();
				Decor decorCondition = cs.getDecor();

				// m�me le trou du cul de golum est moins sale que la ligne qui
				// suit =D
				// A changer bien evidemment
				if (decorCaseOrientee.getClass().getSimpleName().equals(decorCondition.getClass().getSimpleName())) {
					// contient le decor contenu dans condition simple alors la
					// conditionSimple est verifi�e
					conditionVerifiee = false;
				}
			}
			if (conditionVerifiee) {
				indexPossibles.add(i);
			}
		}

		Random r = new Random();
		int indexChoisi = 0;
		if (!indexPossibles.isEmpty()) {
			// Prendre un index au hasard dans la liste
			indexChoisi = indexPossibles.get(r.nextInt(indexPossibles.size()));
		}

		// nouvel etat courant du personnage 
		p.getAutomate().setEtatCourant(p.getAutomate().getTabEtatSuivant()[indexChoisi][etatCourantId]);
		p.deplacer(delta);
	}
	/*
	 * private Image getImageParCouleur(Color c) { try { if (c == Color.blue) {
	 * return new Image("res/sol_bleu.jpg"); } else if (c == Color.green) {
	 * return new Image("res/sol_vert.jpg"); } else { return null; } } catch
	 * (SlickException e) { System.out.println(
	 * "Une image n'a pas pu �tre charg�e"); } return null; }
	 */
}
