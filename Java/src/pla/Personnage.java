package pla;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Personnage {
	private float x,y;
	private int direction;
	private boolean bouge = true;
	private Animation[] animations = new Animation[8];
	private SpriteSheet sperso;
	private int typePerso;
	private Automate automate;
	private Color couleur;
	private float wSprite;
	private float hSprite;
	
	public Personnage(String ref,float x, float y,int direction,int typePerso,int wSprite,int hSprite,Automate a,Color c) throws SlickException {
		this.x = x; 
		this.y = y;
		this.direction = direction;
		this.typePerso = typePerso;
		this.automate = a;
		this.couleur = c;
		this.wSprite = wSprite;
		this.hSprite = hSprite;
		try {
			this.sperso = new SpriteSheet(ref, wSprite, hSprite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("le fichier "+ref+ " n'a pas pu être trouvé");
		}
	}

	public void init() throws SlickException {
		switch(typePerso){
			case 0 : initTagueur();break;
			case 1 : initPolicier();break;
		}
	}
	
	private void initTagueur()throws SlickException{
		this.animations[0] = chargerAnimation(sperso, 0, 1, 0);
		this.animations[1] = chargerAnimation(sperso, 0, 1, 1);
		this.animations[2] = chargerAnimation(sperso, 0, 1, 2);
		this.animations[3] = chargerAnimation(sperso, 0, 1, 3);
		this.animations[4] = chargerAnimation(sperso, 1, 9, 0);
		this.animations[5] = chargerAnimation(sperso, 1, 9, 1);
		this.animations[6] = chargerAnimation(sperso, 1, 9, 2);
		this.animations[7] = chargerAnimation(sperso, 1, 9, 3);
	}
	
	private void initPolicier()throws SlickException{
		this.animations[0] = chargerAnimation(sperso, 0, 1, 0);
		this.animations[1] = chargerAnimation(sperso, 0, 1, 1);
		this.animations[2] = chargerAnimation(sperso, 0, 1, 2);
		this.animations[3] = chargerAnimation(sperso, 0, 1, 3);
		this.animations[4] = chargerAnimation(sperso, 1, 4, 0);
		this.animations[5] = chargerAnimation(sperso, 1, 4, 1);
		this.animations[6] = chargerAnimation(sperso, 1, 4, 2);
		this.animations[7] = chargerAnimation(sperso, 1, 4, 3);
	}

	private Animation chargerAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}

	public void afficher(Graphics g) throws SlickException {
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(x - 16, y - 8, 32, 16);
		g.drawAnimation(animations[direction + (bouge ? 4 : 0)], x - 32, y - 60);
		g.setColor(couleur);		
		g.drawRect(automate.getPosX(), automate.getPosY(), automate.getNbColonnes()*wSprite, automate.getNbLignes()*hSprite);
	}

	public void deplacer(int delta) {
		automate.getEtatCourant().getActionEtat().executer(this,delta);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean getBouge() {
		return bouge;
	}

	public void setbouge(boolean bouge) {
		this.bouge = bouge;
	}
	public Automate getAutomate(){
		return this.automate;
	}
	
	public int getCoordX(){
		return (int)(this.x / wSprite);
	}
	
	public int getCoordY(){
		return (int)(this.y / hSprite);
	}
	
	public Color getCouleur(){
		return this.couleur;
	}
}
