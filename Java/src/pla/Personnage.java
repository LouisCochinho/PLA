package pla;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Personnage {
	private Automate automate;
	private Color couleur;
	private int posX; // position en abcisses sur la map
	private int posY; // position en ordonnées sur la map
	private Image image;

	public Personnage(Color c, int posX, int posY, String img) {
		this.couleur = c;
		this.posX = posX;
		this.posY = posY;
		this.automate = new Automate();
		try {
			this.image = new Image(img);
		} catch (SlickException e) {
			System.out.println("L'image " + img + " n'a pas pu être chargée");
		}
	}

	public Personnage(Color c, int posX, int posY, String img, Automate a) {
		this.couleur = c;
		this.posX = posX;
		this.posY = posY;
		this.automate = new Automate();
		try {
			this.image = new Image(img);
		} catch (SlickException e) {
			System.out.println("L'image " + img + " n'a pas pu être chargée");
		}
		this.automate = a;
	}

	public Automate getAutomate() {
		return automate;
	}

	public Color getCouleur() {
		return couleur;
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
