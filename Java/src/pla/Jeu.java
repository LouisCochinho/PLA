package pla;

import java.util.ArrayList;
import java.util.List;

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
	private GameContainer gc; // Slick2D

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
		ajouterPersonnage(new Personnage(Color.blue, 10, 10, "res/perso_bleu.gif"));
		ajouterPersonnage(new Personnage(Color.green, 20, 20, "res/perso_vert.png", new Automate(10, 10)));
		ajouterPersonnage(new Personnage(null, 15, 15, "res/cop.png", new Automate(1, 1)));
	}

	// Affiche le contenu du jeu
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

		dessiner_carte(g);
		dessiner_elements(g);// dessine les automates et les personnages sur la
								// carte
	}

	// Met à jour les éléments de la scène en fonction du delta temps survenu.
	// C'est ici que la logique du jeu est enfermé.
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		// Déplacement test du personnage bleu vers la droite
		deplacerPersonnage(0,delta);
	}

	// Arreter correctement le jeu en appuyant sur ECHAP
	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}

	public void dessiner_carte(Graphics g) {
		this.map.paint(personnages, g);
	}

	public void dessiner_elements(Graphics g) {
		// Pour chaque persoonage de la liste de personnages, le dessiner et
		// dessiner son automate
		for (Personnage p : personnages) {
			map.placerPersonnage(p, g);
			map.placerAutomate(p.getAutomate(), p.getCouleur(), g);
		}
	}
	public void deplacerPersonnage(int indexPerso, int delta){
		
		Personnage p = this.personnages.get(indexPerso);
		int i = p.getPosX();
		int j = p.getPosY();
		this.map.modifierDecorCase(i, j, getImageParCouleur(p.getCouleur()));
		this.personnages.get(indexPerso).setPosX((this.personnages.get(indexPerso).getPosX() + 1));
		try {
			Thread.sleep(delta); // latence
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Image getImageParCouleur(Color c){
		try{
			if (c == Color.blue){	
				return new Image("res/sol_bleu.jpg");
			}		
			else if(c == Color.green){
				return new Image("res/sol_vert.jpg");
			}
			else 
			{
				return null;
			}
		}catch(SlickException e){
			System.out.println("Une image n'a pas pu être chargée");
		}
		return null;
	}
}
