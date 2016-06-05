package pla;

import org.newdawn.slick.Color;

public class Automate {
	private int tab_etatSuivant[][]; // [Decor_id][etat_courant_id]
	private int tab_actionTransition[][]; //[Decor_id][etat_courant_id]
	private int tab_action[];//[etat_courant_id]
	private Color tab_couleur[]; //juste pour test
	
	private static final int taille = 4;
	private int posX;
	private int posY;
	
	public Automate(){
		
		posX = 5;
		posY = 6;
		init_tab_actionTransition();
		init_tabCouleur();
	}
	
	private void init_tab_actionTransition(){
		tab_actionTransition = new int [taille][taille];
		tab_actionTransition[0][0] = 0; tab_actionTransition[0][1] = 1; tab_actionTransition[0][2] = 3; tab_actionTransition[0][3] = 1;
		tab_actionTransition[1][0] = 1; tab_actionTransition[1][1] = 0; tab_actionTransition[1][2] = 0; tab_actionTransition[1][3] = 0;
		tab_actionTransition[2][0] = 0; tab_actionTransition[2][1] = 0; tab_actionTransition[2][2] = 0; tab_actionTransition[2][3] = 1;
		tab_actionTransition[3][0] = 4; tab_actionTransition[3][1] = 4; tab_actionTransition[3][2] = 4; tab_actionTransition[3][3] = 0;
	}
	
	private void init_tabCouleur(){
		tab_couleur = new Color[5];
		tab_couleur[0] = Color.gray;
		tab_couleur[1] = Color.green;
		tab_couleur[2] = Color.blue;
		// Inutile puisqu'il correspond à un mur donc couleur grise par défaut
		tab_couleur[3] = Color.black;
		tab_couleur[4] = Color.white;
	}
	
	public Color getColorByIndex(int index){
		return tab_couleur[index];
	}

	public int[][] getTab_etatSuivant() {
		return tab_etatSuivant;
	}

	public void setTab_etatSuivant(int[][] tab_etatSuivant) {
		this.tab_etatSuivant = tab_etatSuivant;
	}

	public int[][] getTab_actionTransition() {
		return tab_actionTransition;
	}

	public void setTab_actionTransition(int[][] tab_actionTransition) {
		this.tab_actionTransition = tab_actionTransition;
	}

	public int[] getTab_action() {
		return tab_action;
	}

	public void setTab_action(int[] tab_action) {
		this.tab_action = tab_action;
	}

	public Color[] getTab_couleur() {
		return tab_couleur;
	}

	public void setTab_couleur(Color[] tab_couleur) {
		this.tab_couleur = tab_couleur;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public static int getTaille() {
		return taille;
	}
	
}
