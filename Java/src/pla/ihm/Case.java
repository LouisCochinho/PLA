package pla.ihm;

import pla.decor.Decor;
import java.util.ArrayList;

import pla.Personnage;
import pla.decor.*;

public class Case {
	private ArrayList<Personnage> personnages; // Liste des personnages
												// pr�sents
												// sur la case
	private Decor decor; // Decor de la case
	private int indexI; // Position sur la grille
	private int indexJ;
	private boolean estDansAutomate;

	public Case(int i, int j) {
		personnages = new ArrayList<Personnage>();
		indexI = i;
		indexJ = j;
		this.decor = new SolNormal();

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

	public void setDecor(Decor decor) {
		this.decor = decor;
	}

	public Decor getDecor() {
		return decor;
	}

	public int getIndexI() {
		return indexI;
	}

	public void setIndexI(int indexI) {
		this.indexI = indexI;
	}

	public int getIndexJ() {
		return indexJ;
	}

	public void setIndexJ(int indexJ) {
		this.indexJ = indexJ;
	}

	public int getNbPersonnage() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean estDansAutomate() {
		return estDansAutomate;
	}

	public void setEstDansAutomate(boolean estDansAutomate) {
		this.estDansAutomate = estDansAutomate;
	}

	public ArrayList<Personnage> getPersonnages() {
		return personnages;
	}
}
