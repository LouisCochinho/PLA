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

import pla.decor.Decor;
import pla.ihm.Case;
import pla.ihm.Map;

public class Jeu extends BasicGame {
	private Map map; // carte du jeu
	private List<Personnage> personnages; // Liste des personnages
	private GameContainer gc; // conteneur
	private boolean dejaDessine; // boolean pour savoir si il faut dessiner les automates ou non
	private static final int PAUSE = 50; // temps de latence
	
	/*private float z1 = 0.01f;
	  private float z2 = 0.01f;*/

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
		// Création de la carte
		this.map = new Map();
		// Création des personnages
		ajouterPersonnage(new Personnage(Color.blue, 17, 17, "res/perso_bleu.png"));
		ajouterPersonnage(new Personnage(Color.green, 20, 20, "res/perso_vert.png", new Automate(10, 10)));
		ajouterPersonnage(new Personnage(Color.black, 15, 15, "res/cop.png", new Automate(1, 1)));
		ajouterPersonnage(new Personnage(Color.black, 5, 5, "res/cop.png",new Automate(1, 1)));
		ajouterPersonnage(new Personnage(Color.black, 10, 10, "res/cop.png", new Automate(1, 1)));
	}

	// Affiche le contenu du jeu
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		dessinerCarte(g);
		if (!dejaDessine) {
			dessinerElements(g);// dessine les automates et les personnages sur								// la carte
			dejaDessine = true;
		}
		dessinerPersonnages(g);
	}

	// Met à jour les éléments de la scène en fonction du delta temps survenu.
	// C'est ici que la logique du jeu est enfermé.
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		deplacerPersonnage(0);
		deplacerPersonnage(1);
		deplacerPersonnage(2);
		deplacerPersonnage(3);
		deplacerPersonnage(4);
		
		/*if(gc.getInput().isKeyDown(Input.KEY_DOWN)){
			z1+=0.5f;
			z2+=0.5f;
			gc.getGraphics().scale(z1,z2);			
		}*/		
	}

	// Arreter correctement le jeu en appuyant sur ECHAP
	@Override
	public void keyReleased(int key, char c) {

		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
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

		
		// Chercher le personnage correspondant à l'indexPerso
		Personnage p = personnages.get(indexPerso);
		// Prendre sa couleur et ses coordonnées
		Color couleur = p.getCouleur();
		int coordI = p.getPosX();
		int coordJ = p.getPosY();
		// La case sur lequel le personnage était doit revenir à son etat d'origine
		//map.modifierDecorCase(coordI, coordJ, getImageParCouleur(couleur));
		// On enleve le personnage p a la liste des personnages de la case que le personnage s'apprete à quitter 
		map.getCase(coordI,coordJ).supprimerPersonnage(p);
	
		// Liste des indices en J possibles : indexJ d'une condition possible dans le tableau tabCondition de l'automate du joueur p
		ArrayList<Integer> indexJPossibles = new ArrayList<Integer>();
		// id de l'état courant du joueur p
		int etatCourantId = p.getAutomate().getEtatCourant().getId();
		// boolean pour savoir si une condition simple est vérifiée 
		boolean conditionVerifiee;
		// indice j de la condition a vérifier
		int indexJ = 0;
		// Pour chaque condition disponible pour l'etat courant 
		for(Condition c : p.getAutomate().getTabCondition()[etatCourantId]){
			conditionVerifiee = true;
			// Verifier si chaque condition simple est vraie
			for(ConditionSimple cs : c.getConditions()){
				// si la case au NORD|SUD|EST|OUEST|CASE de la case sur laquelle se trouve le personnage 
				// contient le decor contenu dans condition simple alors la conditionSimple est verifiée				
				Case caseCourante = map.getCase(coordI, coordJ);
				Cellule cell = cs.getCellule();
				Case caseOrientee = map.getCase(caseCourante, cell);
				Decor decorCaseOrientee = caseOrientee.getDecor();
				Decor decorCondition = cs.getDecor();
				if(map.getCase(map.getCase(coordI, coordJ),cs.getCellule()).getDecor() != cs.getDecor()){
					conditionVerifiee = false;
				}
			}
			// Si toutes les conditions simples de la condition complexe sont verifiées alors ajouter l'indice indexJ
			// a la liste des indicesJ possibles
			if(conditionVerifiee){
				indexJPossibles.add(indexJ);
			}
			// On s'apprete à changer de condition
			indexJ++;
		}
				
		Random r = new Random();
		int indexJChoisie = 0;
		if(!indexJPossibles.isEmpty()){
			// Prendre un indexJ au hasard dans la liste
			 indexJChoisie = indexJPossibles.get(r.nextInt(indexJPossibles.size()));
			 personnages.get(indexPerso).getAutomate().setEtatCourant(p.getAutomate().getTabEtatSuivant()[etatCourantId][indexJChoisie]);
		}
		else{
			// Si aucune transition n'est possible alors on revient à l'état initial de l'automate
			personnages.get(indexPerso).getAutomate().setEtatCourant(p.getAutomate().getEtatInitial());
		}
		
		//nouvel etat courant du personnage = tabEtatSuivant[etatCourantId][indexJChoisie] ou etatInitial si rien n'est possible	
		personnages.get(indexPerso).deplacer();
		
		/*
		Random r = new Random();	
		switch(r.nextInt(4)){
			case 0 : 
				 personnages.get(indexPerso).deplacerGauche(map.getLargeur());break;
			
			case 1 : 
				 personnages.get(indexPerso).deplacerDroite(map.getLargeur());break;
				 
			case 2 : 
				 personnages.get(indexPerso).deplacerHaut(map.getLongueur());break;
				 
			case 3 : 
				 personnages.get(indexPerso).deplacerBas(map.getLongueur());break;
		}
		*/
		
		
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
			System.out.println("Une image n'a pas pu être chargée");
		}
		return null;
	}
}
