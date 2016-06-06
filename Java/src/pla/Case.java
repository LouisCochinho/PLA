package pla;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Case{
	private ArrayList<Personnage> personnages;
	private Decor decor;

	
	public Case(){
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
	
	
}
