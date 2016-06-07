package pla.ihm;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import pla.Personnage;

public class Case {
	private ArrayList<Personnage> personnages; // Liste des personnages pr�sents
												// sur la case
	private Decor decor; // Decor de la case
	private int indexI; // Position sur la grille
	private int indexJ;

	public Case(int i, int j) {
		personnages = new ArrayList<Personnage>();
		indexI = i;
		indexJ = j;
		String str = "res/beton.jpg";
		try {
			this.decor = new Decor(new Image(str));
		} catch (SlickException e) {
			System.out.println("L'image " + str + " n'a pas pu �tre charg�e");
		}
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
}
