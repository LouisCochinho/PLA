package pla;

import java.util.ArrayList;

import org.newdawn.slick.Color;

public class Case{
	private ArrayList<Personnage> personnages;
	private Decor decor;

	
	public Case(){
		this.decor = new Decor(Color.gray);
	}

	public Color getCouleur() {
		return decor.getCouleur();
	}
	
	public void setDecor(Decor decor){
		this.decor = decor;
	}

	public ArrayList<Personnage> getPersonnages() {
		return personnages;
	}

	public void setPersonnages(ArrayList<Personnage> personnages) {
		this.personnages = personnages;
	}

	public Decor getDecor() {
		return decor;
	}
	
	
}
