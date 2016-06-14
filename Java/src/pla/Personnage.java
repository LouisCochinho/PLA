package pla;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import pla.decor.DecorPersonnage;

public class Personnage {
	private float x,y;
	private int direction;
	private boolean bouge = true;
	private Animation[] animations = new Animation[8];
	private SpriteSheet sperso;
	private Automate automate;
	private Color couleur;
	private float wSprite;
	private float hSprite;
	private float deplacementCourant;
        private boolean inverse;
        private String ref;
        private TypePersonnage typePersonnage;
	
	public Personnage(TypePersonnage typePersonnage,int direction,int wSprite,int hSprite,Automate a) throws SlickException {
                this.typePersonnage = typePersonnage;
                this.ref = typePersonnage.getRef();
		this.direction = direction;
		this.automate = a;
		this.couleur = typePersonnage.getColor();
		this.wSprite = wSprite;
		this.hSprite = hSprite;
		try {
			this.sperso = new SpriteSheet(ref, wSprite, hSprite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("le fichier "+ref+ " n'a pas pu �tre trouv�");
		}
                this.inverse = typePersonnage.isInverse();
                if(inverse)
                    a.inverser();
	}

	public void init() throws SlickException {
		this.animations[0] = chargerAnimation(sperso, 0, 1, 8);
		this.animations[1] = chargerAnimation(sperso, 0, 1, 9);
		this.animations[2] = chargerAnimation(sperso, 0, 1, 10);
		this.animations[3] = chargerAnimation(sperso, 0, 1, 11);
		this.animations[4] = chargerAnimation(sperso, 1, 9, 8);
		this.animations[5] = chargerAnimation(sperso, 1, 9, 9);
		this.animations[6] = chargerAnimation(sperso, 1, 9, 10);
		this.animations[7] = chargerAnimation(sperso, 1, 9, 11);
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
		g.drawRect(automate.getPosY(), automate.getPosX(), automate.getNbColonnes()*wSprite, automate.getNbLignes()*hSprite);
	}

	public void deplacer(int delta,int modulo_tore_x,int modulo_tore_y) {
		
		automate.getEtatCourant().getActionEtat().executer(this,delta, modulo_tore_x, modulo_tore_y);
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
	public float getDeplacementCourant() {
		return deplacementCourant;
	}

	public void setDeplacementCourant(float deplacementCourant) {
		this.deplacementCourant = deplacementCourant;
	}
	
	public boolean isDeplacementTermine(){
	//	return this.deplacementCourant >= distanceDeplacement;
		return Math.round(x)%32==0 && Math.round(x)%64!=0 && Math.round(y)%32==0 && Math.round(y)%64!=0;
	}

    public boolean isInverse() {
        return inverse;
    }

    public String getRef() {
        return ref;
    }

    public float getwSprite() {
        return wSprite;
    }

    public float gethSprite() {
        return hSprite;
    }

    public TypePersonnage getTypePersonnage() {
        return typePersonnage;
    }
}
