package pla.decor;

import java.util.ArrayList;
import org.newdawn.slick.Image;

import pla.action.Action;

public abstract class Decor {
	private int id;
	private ArrayList<Action> actions;
	protected Image image;

	public Decor(int id) {
		actions = new ArrayList<Action>();
		this.id = id;
	}

	public Decor() {
		actions = new ArrayList<Action>();
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
        
        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
}
