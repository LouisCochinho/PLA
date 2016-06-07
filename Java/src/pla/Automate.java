package pla;

public class Automate {
	private int tab_etatSuivant[][]; // [Decor_id][etat_courant_id]
	private int tab_actionTransition[][]; //[Decor_id][etat_courant_id]
	private int tab_action[];//[etat_courant_id]
	private int posX;
	private int posY;
	
	public Automate(){		
		posX = 5;
		posY = 6;
		init_tab_actionTransition(4);
		init_tab_etatSuivant(getTaille());
	}
	public Automate(int posX,int posY){
		this.posX = posX;
		this.posY = posY;
		init_tab_actionTransition(4);
		init_tab_etatSuivant(getTaille());
	}
	
	private void init_tab_actionTransition(int taille){
		tab_actionTransition = new int [taille][taille];
		tab_actionTransition[0][0] = 0; tab_actionTransition[0][1] = 1; tab_actionTransition[0][2] = 3; tab_actionTransition[0][3] = 1;
		tab_actionTransition[1][0] = 1; tab_actionTransition[1][1] = 0; tab_actionTransition[1][2] = 0; tab_actionTransition[1][3] = 0;
		tab_actionTransition[2][0] = 0; tab_actionTransition[2][1] = 0; tab_actionTransition[2][2] = 0; tab_actionTransition[2][3] = 1;
		tab_actionTransition[3][0] = 4; tab_actionTransition[3][1] = 4; tab_actionTransition[3][2] = 4; tab_actionTransition[3][3] = 0;
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

	public int getTaille() {
		return tab_actionTransition[0].length;
	}
	
	public void init_tab_etatSuivant(int taille){
		tab_etatSuivant = new  int[taille][taille];
		tab_etatSuivant[0][0] = 0; tab_etatSuivant[0][1] = 3; tab_etatSuivant[0][2] = 0; tab_etatSuivant[0][3] = 3;
		tab_etatSuivant[1][0] = 0; tab_etatSuivant[1][1] = 1; tab_etatSuivant[1][2] = 2; tab_etatSuivant[1][3] = 3;
		tab_etatSuivant[2][0] = 3; tab_etatSuivant[2][1] = 1; tab_etatSuivant[2][2] = 2; tab_etatSuivant[2][3] = 2;
		tab_etatSuivant[3][0] = 1; tab_etatSuivant[3][1] = 2; tab_etatSuivant[3][2] = 0; tab_etatSuivant[3][3] = 3;
	}
}
