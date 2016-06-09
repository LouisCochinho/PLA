package pla.ihm;

import pla.decor.Decor;

import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import pla.Automate;
import pla.Cellule;
import pla.Personnage;

public class Map {

	/* largeur de la map */
	private int largeur = 32;

	/* longueur de la map */
	private int longueur = 24;

	/** taille de la case */
	private static final int TILE_SIZE = 20;

	// Matrice des Cases
	private Case cases[][];

	public Map() {
		cases = new Case[largeur][longueur];
		// Cr�ation de la matrice des cases
		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < longueur; j++) {
				cases[i][j] = new Case(i, j);
			}
		}
	}

	public void paint(List<Personnage> persos, Graphics g) {

		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < longueur; j++) {
				// Fond gris
				g.setColor(Color.lightGray);
				g.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				try {
					// Pour chaque case, dessiner le d�cor correspondant
					dessinerImage(cases[i][j].getDecor().getImage(), i * TILE_SIZE, j * TILE_SIZE, g);
				} catch (ArrayIndexOutOfBoundsException e1) {
					System.out.println("La case [" + i + "][" + j + "] est inacessible");
				} catch (NullPointerException e2) {
					System.out.println("La case [" + i + "][" + j
							+ "] ne contient pas de d�cor ou l'image associ�e est inacessible\n");
					e2.printStackTrace();
				}
			}
		}
	}

	// Attention : Inversion i et j => x et y dans la map
	// MAP
	/*
	 * i ----> x | j| | - y
	 */
	// CASE
	/*
	 * j ---> | i| | -
	 */
	public void placerPersonnage(Personnage p, Graphics g) {
		
		// La case du personnage contient un nouveau decor contenant une image
		modifierDecorCase(p.getPosX(), p.getPosY(), p.getImage());
		// Ajouter le personnage � la liste des personnages de la case
		cases[p.getPosX()][p.getPosY()].ajouterPersonnage(p);
		// dessiner l'image du personnage
		dessinerImage(cases[p.getPosX()][p.getPosY()].getDecor().getImage(), p.getPosX() * TILE_SIZE,
				p.getPosY() * TILE_SIZE, g);
	}

	public void placerAutomate(Automate a, Color couleurPerso, Graphics g) {
		for (int i = 0; i < a.getTabActionTransition().length; i++)
			for (int j = 0; j < a.getTabActionTransition().length; j++) {
				// pour chaque valeur dans le tableau action-transition, charger
				// l'image dans la case
				chargerImage(a, g, i, j);
			}
	}
	
	public void dessinerContoursAutomate(Personnage p, Graphics g){
		// dessiner un rectangle autour de l'automate et le colorier de la
		// couleur du personnage � qui appartient cet automate.
		Automate a = p.getAutomate();
		g.setColor(p.getCouleur());
		//g.drawRect(a.getPosX() * TILE_SIZE, a.getPosY() * TILE_SIZE, a.getNbLignes()* TILE_SIZE,a.getNbColonnes()* TILE_SIZE);
		g.drawRect(a.getPosX() * TILE_SIZE, a.getPosY() * TILE_SIZE, 4* TILE_SIZE,4* TILE_SIZE);
	}

	public void chargerImage(Automate a, Graphics g, int i, int j) {
		int val = a.getTabActionTransition()[i][j];
		Image img = null;
		switch (val) { // Selon la valeur, charger le d�cor correspondant
		case 0:
			try {
				img = new Image("res/beton.jpg");
			} catch (SlickException e) {
				g.drawString("Erreur : L'image du sol n'a pas pu �tre charg�e", 50, 0);
				g.setColor(Color.red);
			}
			break;

		case 1:
			try {
				img = new Image("res/sol_vert.jpg");
			} catch (SlickException e) {
				g.drawString("Erreur : L'image du sol vert n'a pas pu �tre charg�e", 50, 0);
				g.setColor(Color.red);
			}
			break;

		case 2:
		case 4:
			try {
				img = new Image("res/sol_bleu.jpg");
			} catch (SlickException e) {
				g.drawString("Erreur : L'image du sol bleu n'a pas pu �tre charg�e", 50, 0);
				g.setColor(Color.red);
			}
			break;

		case 3:
			try {
				img = new Image("res/wall.png");
			} catch (SlickException e) {
				g.drawString("Erreur : L'image du mur n'a pas pu �tre charg�e", 50, 0);
				g.setColor(Color.red);
			}
			break;

		default:
			img = null;
		}
		// Attention inversion des indices entre les cases et la map
		// Modification du d�cor de la case
		modifierDecorCase(j + a.getPosX(), i + a.getPosY(), img);
	}

	public Case[][] getCases() {
		return cases;
	}

	public void setCases(Case[][] cases) {
		this.cases = cases;
	}

	public void modifierDecorCase(int i, int j, Image img) {
		if (i < largeur && j < longueur) {
			cases[i][j].setDecor(new Decor(img));
		}

	}

	public void effacerDecorCase(int i, int j) {
		try {
			modifierDecorCase(i, j, new Image("res/beton.jpg")); // Le beton est
																	// l'image
																	// de base
		} catch (SlickException e) {
			System.out.println("L'image du b�ton n'a pas pu �tre trouv�e lors de la r�initialisation d'une case");
		}
	}

	public void dessinerImage(Image img, float x, float y, Graphics g) {
		if (x < largeur * TILE_SIZE && y < longueur * TILE_SIZE) { // Si la
																	// position
																	// voulue
																	// est
																	// bien dans
																	// la
																	// grille
			g.drawImage(img, x, y);
		} else {
			System.out.println("L'image" + img.getResourceReference() + "n'a pas pu �tre dessin�e");
		}
	}

	public Case getCase(Case caseCourante, Cellule cellule) {
		try {
			switch (cellule) {
			case Nord:
				return cases[caseCourante.getIndexI()][caseCourante.getIndexJ() - 1];
			case Sud:
				return cases[caseCourante.getIndexI()][caseCourante.getIndexJ() + 1];
			case Est:
				return cases[caseCourante.getIndexI() + 1][caseCourante.getIndexJ()];
			case Ouest:
				return cases[caseCourante.getIndexI() - 1][caseCourante.getIndexJ()];
			case Case:
			default:
				return caseCourante;
			}
		} catch (ArrayIndexOutOfBoundsException e) { // Si on sort de la map
			return caseCourante;
		}
	}
	
	public Case getCase(int i, int j){
		return cases[i][j];
	}

	// Compteur qui retourne un entier correspondant aux nombres de cases dans
	// la grille contenant le decor d
	public int nbDecor(Decor d, Graphics g) {
		int res = 0;
		if (d != null) {
			for (int i = 0; i < largeur; i++)
				for (int j = 0; j < longueur; j++) {
					if (cases[i][j].getDecor() != null && cases[i][j].getDecor().getId() == d.getId()) {
						res++;
					}
				}
		}
		return res;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	private static final int CERCLE = 360;
	private static final int DISTANCE = 2;
	
	public void placerAutoRandom(List<Personnage> lPersonnage){
		Random rand =  new Random();
		rand.setSeed(5);
		int nbAutomate = lPersonnage.size();
		int rayonCercle, centreCercleX, centreCercleY, anglePersonnage, firstAngle, hmax, lmax;
		
		hmax = hauteurMax(lPersonnage);
		lmax = largeurMax(lPersonnage);
		
		if(hmax < lmax){
			rayonCercle = lmax*(nbAutomate)/2 + nbAutomate;
		}
		else{
			rayonCercle = hmax*(nbAutomate)/2 + nbAutomate;
		}
		centreCercleX = rayonCercle;//+varx
		centreCercleY = rayonCercle;//+vary
		
		anglePersonnage = CERCLE/nbAutomate;
		firstAngle = rand.nextInt()*(CERCLE+1);
		
		lPersonnage.get(0).getAutomate().setPosX(rayonCercle*(int)Math.cos(firstAngle)+centreCercleX);
		lPersonnage.get(0).getAutomate().setPosX(rayonCercle*(int)Math.sin(firstAngle)+centreCercleY);
		
		for(int i=1; i<nbAutomate; i++){
			rayonCercle += anglePersonnage;
			lPersonnage.get(i).getAutomate().setPosX(rayonCercle*(int)Math.cos(firstAngle));
			lPersonnage.get(i).getAutomate().setPosX(rayonCercle*(int)Math.sin(firstAngle));
		}
	}
	
	private int hauteurMax(List<Personnage> lPersonnage){
		int hmax=0;
		for(int i=0; i<lPersonnage.size(); i++){
			if(lPersonnage.get(i).getAutomate().getNbLignes() > hmax){
				hmax = lPersonnage.get(i).getAutomate().getNbColonnes();
			}
		}
		return hmax;
	}
	
	private int largeurMax(List<Personnage> lPersonnage){
		int lmax=0;
		for(int i=0; i<lPersonnage.size(); i++){
			if(lPersonnage.get(i).getAutomate().getNbColonnes() > lmax){
				lmax = lPersonnage.get(i).getAutomate().getNbLignes();
			}
		}
		return lmax;
	}
	
	public void placerPersonnageRandom( List<Personnage> lPersonnage){
		Random rand =  new Random();
		rand.setSeed(5);
		
		int posX, posY;
		int w = getCases().length;
		int h = getCases()[0].length;
		
		do{
			posX = rand.nextInt(1)*w;
			posY = rand.nextInt(1)*h;
		}while(getCases()[posX][posY].getNbPersonnage() != 0);
		
		lPersonnage.get(0).setPosX(posX);
		lPersonnage.get(0).setPosY(posY);
		
		for(int i=1; i<lPersonnage.size(); i++){
			do{
				posX = rand.nextInt(1)*w;
				posY = rand.nextInt(1)*h;				
			}while(getCases()[posX][posY].getNbPersonnage() != 0 || personnagePresent(lPersonnage, posX, posY, i));

			lPersonnage.get(i).setPosX(posX);
			lPersonnage.get(i).setPosY(posY);			
		}
		
	}

	private boolean personnagePresent(List<Personnage> lPersonnage, int posX, int posY, int i) {
		boolean present = false;
		for(int j=0; j<i; j++){
			if((lPersonnage.get(j).getPosX() - posX)+ (lPersonnage.get(j).getPosY() - posY) <= DISTANCE ||
					(lPersonnage.get(j).getPosX() - posX)+ (lPersonnage.get(j).getPosY() - posY) <= DISTANCE){
				present = true;
			}
		}
		
		return present;
	}

}
