package pla.ihm;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import pla.Action;

public class Decor {
	private int id;
	private ArrayList<Action> actions;
	private Image image;

	public Decor(Image img, int id) {
		this.image = img;
		this.id = id;
	}

	public Decor(Image img) {
		if(img == null){
			try {
				this.image = new Image("res/beton.jpg");
			} catch (SlickException e) {
				System.out.println("L'image du beton n'a pas pu être chargée");
			}
		}
		else{
			this.image = img;
		}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

	public void ajouterAction(Action a) {
		if (a != null) {
			this.actions.add(a);
		} else {
			System.out.println("L'action que vous voulez ajouter dans la liste des actions est vide");
		}
	}

	public void supprimerAction(Action a) {
		if (a != null && this.actions.contains(a)) {
			this.actions.remove(a);
		} else {
			System.out.println("L'action que vous voulez supprimer n'est pas dans la liste ou est nul");
		}
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
