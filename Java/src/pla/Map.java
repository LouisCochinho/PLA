package pla;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;




public class Map {

	/* largeur de la map */
	private static final int WIDTH = 100;
	
	/* longueur de la map */
	private static final int HEIGHT = 100;
	
	/** taille de la case */
	private static final int TILE_SIZE = 20;
	
	private Case cases[][];

	
	public Map() {
		cases = new Case[WIDTH][HEIGHT];
		
		// Création de la matrice des cases
		for(int i = 0;i<WIDTH;i++){
			for(int j = 0;j<HEIGHT;j++){
				cases[i][j] = new Case();
			}
		}
	}

	public void paint(Graphics g) {

		for(int i = 0; i< WIDTH; i++){
			for(int j = 0; j<HEIGHT;j++){
				
				// carrés de fond noir
				// position en x, en y, largeur et longueur
				g.fillRect(i*TILE_SIZE,j*TILE_SIZE,TILE_SIZE,TILE_SIZE);							
				g.setColor(Color.black);											
				g.drawRect(i*TILE_SIZE,j*TILE_SIZE,TILE_SIZE,TILE_SIZE);
				g.setColor(cases[i][j].getCouleur());
				
				
			}
		}		
	}
	
	public void placerAutomate(Automate a, Graphics g){
		for(int i = 0; i< a.getTab_actionTransition().length;i++)
			for(int j = 0;j< a.getTab_actionTransition().length;j++){
				// pour chaque valeur dans le tableau action-transition 
				// si la valeur est 3 alors c'est un mur => a chanqer
				if(a.getTab_actionTransition()[i][j] == 3){									
					try {						
						// Attention : Inversion i et j => x et y dans la map
						//MAP
						/* 	i		
						 * ----> x
						 * |
						 *j|
						 * |
						 * -
						 * y
						 */
						//CASE
						/*	j
						 * --->
						 * |
						* i|
						 * |
						 * -
						 */
						 					
						cases[j+a.getPosX()][i+a.getPosY()].setDecor(new Decor(new Image("res/wall.png")));
					} catch (Exception e) {
						g.setColor(Color.red);
						g.drawString("Erreur : L'image du mur n'a pas pu être chargée", 50, 0);						
					}
					// si la case possède une image
					if(cases[j+a.getPosX()][i+a.getPosY()].getDecor().getImage() != null){
						// dessin de l'image
						g.drawImage(cases[j+a.getPosX()][i+a.getPosY()].getDecor().getImage(),(i+a.getPosY()+1)*TILE_SIZE+2,(j+a.getPosX())*TILE_SIZE+2); // Multiple de 20 (TILE_SIZE+2 pour centrer l'image)
					}
				}				
				// sinon le décor n'est qu'une couleur
				else{				
					// Attention : Inversion i et j => x et y dans la map voir au dessus.
					cases[j+a.getPosX()][i+a.getPosY()].setDecor(new Decor(a.getColorByIndex(a.getTab_actionTransition()[i][j]))); 
				}
			}
	}

	public Case[][] getCases() {
		return cases;
	}

	public void setCases(Case[][] cases) {
		this.cases = cases;
	}
	
}
