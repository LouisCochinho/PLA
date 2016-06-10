package pla.decor;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import pla.action.Action;

public abstract class Decor {
	private int id;
	private ArrayList<Action> actions;
	protected SpriteSheet sprite;
	private float wSprite;
	private float hSprite;
	
	public Decor(int id) {
		actions = new ArrayList<Action>();
		this.id = id;
	}
	
	public Decor(String ref, int wSprite, int hSprite){
		this.wSprite = wSprite;
		this.hSprite = hSprite;
		try {
			this.sprite = new SpriteSheet(ref, wSprite, hSprite);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actions = new ArrayList<Action>();
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

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
