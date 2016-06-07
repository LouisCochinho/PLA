package pla;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Case{
	private ArrayList<Personnage> personnages;
	private Decor decor;
	private int indexI;
	private int indexJ;

	
	public Case(int i, int j){
		indexI = i;
		indexJ = j;
		try {
			this.decor = new Decor(new Image("res/beton.jpg"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
