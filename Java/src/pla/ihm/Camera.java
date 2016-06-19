package pla.ihm;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

public class Camera {

	private static float camX;
	private static float camY;
	private static float zoomX=1;
	private static float zoomY=1;
	private final static float DEPLACEMENT = 5;
	private static int SIZE_WINDOW_X ;
	private static int SIZE_WINDOW_Y ;
	private static float currentSizeMapX ;
	private static float currentSizeMapY ;
	private final static float ZOOM = 0.03f;
	private final static float ZOOM_MAX = 5;
	private static float bordure_centrageX =0,  bordure_centrageY=0;

	/*
	 * Modification de la camera
	 */
	public static void moveCamera(Graphics g){
		g.translate(camX, camY);
		g.scale(zoomX, zoomY);

	}

	/*
	 * Permet d'initialiser la camera en debut de jeu
	 */
	public static void initCamera(Map map, int largeur, int hauteur){
		SIZE_WINDOW_X = largeur;
		SIZE_WINDOW_Y = hauteur;

		currentSizeMapX = SIZE_WINDOW_X;
		currentSizeMapY = SIZE_WINDOW_Y;

		if((float)SIZE_WINDOW_X/(float)map.getLargeur()>=(float)SIZE_WINDOW_Y/(float)map.getHauteur()){
			zoomX = (float)SIZE_WINDOW_Y/(float)map.getHauteur();
			zoomY = zoomX;

			currentSizeMapX = zoomX*map.getHauteur();
			bordure_centrageX = ((float)SIZE_WINDOW_X-currentSizeMapX)/2;
			camX = +bordure_centrageX;
			camY =0;
		}
		else{
			zoomX = (float)SIZE_WINDOW_X/(float)map.getLargeur();
			zoomY = zoomX;

			currentSizeMapY = zoomY*map.getLargeur();
			bordure_centrageY = ((float)SIZE_WINDOW_Y-currentSizeMapY)/2;
			camY = +bordure_centrageY;
			camX =0;
		}

	}

	/*
	 * Permet de déplacer la camera vers le bas
	 */
	public static void cameraDown(){
		if(SIZE_WINDOW_Y <= currentSizeMapY+camY-DEPLACEMENT+bordure_centrageY ){camY-=DEPLACEMENT;}
		else{camY = SIZE_WINDOW_Y-currentSizeMapY-bordure_centrageY;}
	}

	/*
	 * Permet de déplacer la caméra vers le haut
	 */
	public static void cameraUP(){
		if(camY<= bordure_centrageY-DEPLACEMENT){camY+=DEPLACEMENT;}
		else{camY = +bordure_centrageY;}
	}

	/*
	 * Permet de déplacer la caméra vers la gauche
	 */
	public static void cameraLEFT(){
		if(camX<= bordure_centrageX-DEPLACEMENT){camX+=DEPLACEMENT;}
		else{camX = +bordure_centrageX;}
	}

	/*
	 * Permet de déplacer la caméra vers la droite
	 */
	public static void cameraRIGHT(){
		if(SIZE_WINDOW_X <= (currentSizeMapX+bordure_centrageX)+camX-DEPLACEMENT){camX-= DEPLACEMENT;}
		else{camX = SIZE_WINDOW_X-currentSizeMapX-bordure_centrageX;}
	}

	/*
	 * Permet de dezoomer, prendre du recul 
	 */
	public static void cameraDezoom(Map map){
		
		float lastSizeMapX = currentSizeMapX;
		float lastSizeMapY = currentSizeMapY;
		
		//dezoom normal avec dentrage
		if((zoomX-ZOOM)*(map.getLargeur()+bordure_centrageX) >= SIZE_WINDOW_X && 
				(zoomY-ZOOM)*(map.getHauteur()+bordure_centrageY) >= SIZE_WINDOW_Y){
			zoomX -= ZOOM; zoomY -= ZOOM;
			currentSizeMapX = zoomX*(map.getLargeur());
			currentSizeMapY = zoomY*(map.getHauteur());
			
			//zoom au centre de l'écrant
			camX = camX-(currentSizeMapX-lastSizeMapX)/2;
			camY = camY-(currentSizeMapY-lastSizeMapY)/2;
			
		}
		else{//Permet de ne pas dezoomer à l'infini
			
			if((float)SIZE_WINDOW_X/(float)map.getLargeur()>=(float)SIZE_WINDOW_Y/(float)map.getHauteur()){
				zoomX = (float)SIZE_WINDOW_Y/(float)map.getHauteur();

			}
			else{
				zoomX = (float)SIZE_WINDOW_X/(float)map.getLargeur();
			}
			zoomY = zoomX;
			camX = +bordure_centrageX;
			camY = +bordure_centrageY;
			currentSizeMapX = zoomX*map.getLargeur();
			currentSizeMapY = zoomY*map.getHauteur();
		}
		
		//Ne pas céloigner de la map quand on dezoom
		if(currentSizeMapX+camX<SIZE_WINDOW_X){camX = (SIZE_WINDOW_X-currentSizeMapX)-bordure_centrageX;}
		if(currentSizeMapY+camY<SIZE_WINDOW_Y){camY = (SIZE_WINDOW_Y-currentSizeMapY)-bordure_centrageY;}
		if(camX>=bordure_centrageX){camX = +bordure_centrageX;}
		if(camY>=bordure_centrageY){camY = +bordure_centrageY;}
	}

	/*
	 * Permet de zoomer
	 */
	public static void cameraZoom(Map map){
		float lastSizeMapX = currentSizeMapX;
		float lastSizeMapY = currentSizeMapY;
		if(zoomX<=ZOOM_MAX && zoomY<=ZOOM_MAX){
			zoomX += ZOOM; zoomY += ZOOM;
			currentSizeMapX = zoomX*(map.getLargeur());
			currentSizeMapY = zoomY*(map.getHauteur());
			
			//zoom au centre de l'écrant
			camX = camX-(currentSizeMapX-lastSizeMapX)/2;
			camY = camY-(currentSizeMapY-lastSizeMapY)/2;

		}	
	}
}
