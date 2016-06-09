package pla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import pla.ihm.Map;

public class Jeu extends BasicGame {
	private Map map; // carte du jeu
	private List<Personnage> personnages; // Liste des personnages
	private GameContainer gc; // conteneur
	private boolean dejaDessine; // boolean pour savoir si il faut dessiner les automates ou non
	private static final int PAUSE = 50; // temps de latence

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
		// TODO Auto-generated method stub
		this.gc = gc;
		// Cr�ation de la carte
		this.map = new Map();
		// Cr�ation des personnages
		ajouterPersonnage(new Personnage(Color.blue, 20, 10, "res/perso_bleu.gif"));
		ajouterPersonnage(new Personnage(Color.green, 20, 20, "res/perso_vert.png", new Automate(10, 10)));
		ajouterPersonnage(new Personnage(Color.black, 15, 15, "res/cop.png", new Automate(1, 1)));
		ajouterPersonnage(new Personnage(Color.black, 5, 5, "res/cop.png",new Automate(1, 1)));
		ajouterPersonnage(new Personnage(Color.black, 5, 5, "res/cop.png", new Automate(1, 1)));
	}

	// Affiche le contenu du jeu
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		dessinerCarte(g);
		if (!dejaDessine) {
			dessinerElements(g);// dessine les automates et les personnages sur
								// la carte
			dejaDessine = true;
		}
		dessinerPersonnages(g);
	}

	// Met � jour les �l�ments de la sc�ne en fonction du delta temps survenu.
	// C'est ici que la logique du jeu est enferm�.
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		deplacerPersonnage(0);
		deplacerPersonnage(1);
		deplacerPersonnage(2);
		deplacerPersonnage(3);
		deplacerPersonnage(4);
	}

	// Arreter correctement le jeu en appuyant sur ECHAP
	@Override
	public void keyReleased(int key, char c) {

		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}

		/*
		 * Marche pas if (Input.KEY_P == key) { gc.pause(); }
		 */
	}

	public void dessinerCarte(Graphics g) {
		this.map.paint(personnages, g);
	}

	public void dessinerPersonnages(Graphics g) {
		for (Personnage p : personnages) {
			map.placerPersonnage(p, g);
			// sale
			map.dessinerContoursAutomate(p, g);
		}
	}

	public void dessinerElements(Graphics g) {
		// Pour chaque persoonage de la liste de personnages, le dessiner et
		// dessiner son automate
		for (Personnage p : personnages) {
			map.placerPersonnage(p, g);
			map.placerAutomate(p.getAutomate(), p.getCouleur(), g);
		}
	}

	public void deplacerPersonnage(int indexPerso) {

		// Chercher le personnage correspondant � l'indexPerso
		Personnage p = personnages.get(indexPerso);
		// Prendre sa couleur et ses coordonn�es
		Color c = p.getCouleur();
		int coordI = p.getPosX();
		int coordJ = p.getPosY();
		// La case sur lequel le personnage �tait doit revenir � son etat d'origine
		map.modifierDecorCase(coordI, coordJ, getImageParCouleur(c));
		// On enleve le personnage p a la liste des personnages de la case que le personnage s'apprete � quitter 
		map.getCases()[coordI][coordJ].supprimerPersonnage(p);

		// d�placement du personnage =>
		// Allez chercher dans tabEtatSuivant l'etat Suivant de l'automate
			// => Decor_id 
			// => etat_courant_id
		// Modifier l'etat Courant de l'automate
		int decor_id = map.getCase(coordI, coordJ).getDecor().getId();
		int etat_courant_id = p.getAutomate().getEtatCourant().getId();
		Etat etatSuivant = p.getAutomate().getEtatSuivant(decor_id, etat_courant_id);
		p.getAutomate().setEtatCourant(etatSuivant);
		p.deplacer();
		/*Random r = new Random();	
		switch(r.nextInt(4)){
			case 0 : 
				 personnages.get(indexPerso).deplacerGauche(map.getLargeur());break;
			
			case 1 : 
				 personnages.get(indexPerso).deplacerDroite(map.getLargeur());break;
				 
			case 2 : 
				 personnages.get(indexPerso).deplacerHaut(map.getLongueur());break;
				 
			case 3 : 
				 personnages.get(indexPerso).deplacerBas(map.getLongueur());break;
		}*/
		
		
		
		// Pause
		try {
			Thread.sleep(PAUSE); // latence
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Image getImageParCouleur(Color c) {
		try {
			if (c == Color.blue) {
				return new Image("res/sol_bleu.jpg");
			} else if (c == Color.green) {
				return new Image("res/sol_vert.jpg");
			} else {
				return null;
			}
		} catch (SlickException e) {
			System.out.println("Une image n'a pas pu �tre charg�e");
		}
		return null;
	}
}
