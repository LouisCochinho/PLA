package pla;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class Decor{
	private int id;
	private ArrayList<Action> actions;
	private Color couleur;
	private Image image;
	
	public Decor(Color couleur){
		this.couleur = couleur;
	}
	public Decor(Image img){
		this.image = img;
		
		// couleur grise par défaut
		this.couleur=Color.gray;
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
	public Color getCouleur() {
		return couleur;
	}
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
