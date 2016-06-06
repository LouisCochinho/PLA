package pla;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Personnage {
	private Automate automate;
	private Color couleur;
	private int posX;
	private int posY;
	private Image image;
	
	public Personnage(Color c, int posX, int posY, String img){
		this.couleur = c;
		this.posX = posX;
		this.posY = posY;
		this.automate = new Automate();
		try {
			this.image = new Image(img);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Automate getAutomate() {
		return automate;
	}
	public void setAutomate(Automate automate) {
		this.automate = automate;
	}
	public Color getCouleur() {
		return couleur;
	}
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
