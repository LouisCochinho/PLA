package pla;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class Decor{
	private int id;
	private ArrayList<Action> actions;
	private Image image;
	

	public Decor(Image img){
		this.image = img;
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
	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
