package pla.ihm;

import org.newdawn.slick.Graphics;

public class Camera {

	private static float camX;
	private static float camY;
	private static float zoomX=1;
	private static float zoomY=1;
	private final static float DEPLACEMENT = 13;
	private static int SIZE_WINDOW_X ;
	private static int SIZE_WINDOW_Y ;
	private static float currentSizeMapX ;
	private static float currentSizeMapY ;
	private final static float ZOOM = 0.03f;
	private final static float ZOOM_MAX = 5;
	private static float bordure_centrageX =0,  bordure_centrageY=0;

	public static void moveCamera(Graphics g){
		g.translate(camX, camY);
		g.scale(zoomX, zoomY);

	}

	public static void initCamera(Map map, int largeur, int hauteur){
		SIZE_WINDOW_X = largeur;
		SIZE_WINDOW_Y = hauteur;

		currentSizeMapX = SIZE_WINDOW_X;
		currentSizeMapY = SIZE_WINDOW_Y;

		if(largeur/map.getLargeur()>=hauteur/map.getHauteur()){
			bordure_centrageX = (SIZE_WINDOW_X-map.getLargeur())/2;
			zoomX = (float)SIZE_WINDOW_Y/(float)map.getHauteur();
			zoomY = zoomX;
			camX = -bordure_centrageX;
			camY =0;
			currentSizeMapX = SIZE_WINDOW_X-bordure_centrageX;
		}
		else{
			bordure_centrageY = (SIZE_WINDOW_Y-map.getHauteur())/2;
			zoomX = (float)SIZE_WINDOW_X/(float)map.getLargeur();
			zoomY = zoomX;
			camY = -bordure_centrageY;
			camX =0;
			currentSizeMapY = SIZE_WINDOW_Y-bordure_centrageY;
		}
		/*
		if(currentSizeMapX+camX<SIZE_WINDOW_X){camX = camX+(SIZE_WINDOW_X-currentSizeMapX-camX);}
		if(currentSizeMapY+camY<SIZE_WINDOW_Y){camY = camY+(SIZE_WINDOW_Y-currentSizeMapY-camY);}
		*/


	}

	public static void cameraDown(){
	/*	if(camY-DEPLACEMENT >= -currentSizeMapY+SIZE_WINDOW_Y-bordure_centrageY*2){camY-=DEPLACEMENT;}
		else{camY = camY - (camY +currentSizeMapY-SIZE_WINDOW_Y-bordure_centrageY*2);}	*/
		camY-=DEPLACEMENT;

	}

	public static void cameraUP(){
		/*if(camY+DEPLACEMENT <= -bordure_centrageY){camY+=DEPLACEMENT;}
		else{camY = -bordure_centrageY;}*/
		camY+=DEPLACEMENT;

	}

	public static void cameraLEFT(){
		/*if(camX+DEPLACEMENT <= -bordure_centrageX){camX+=DEPLACEMENT;}
		else{camX = -bordure_centrageX;}*/
		camX+=DEPLACEMENT;

	}

	public static void cameraRIGHT(){
		/*if(camX-DEPLACEMENT >= -currentSizeMapX+SIZE_WINDOW_X+bordure_centrageX){camX-= DEPLACEMENT;}
		else{camX = camX - (camX +currentSizeMapX-SIZE_WINDOW_X)+bordure_centrageX;}*/
		camX-= DEPLACEMENT;

	}
/*
	public static void camerajDezoom(Map map){
		float lastSizeMapX = currentSizeMapX;
		float lastSizeMapY = currentSizeMapY;
		if((zoomX-ZOOM)*map.getLargeur() >= SIZE_WINDOW_X && (zoomY-ZOOM)*map.getHauteur() >= SIZE_WINDOW_Y
				&& currentSizeMapX+camX < SIZE_WINDOW_X && camX<=0){
			zoomX -= ZOOM; zoomY -= ZOOM;
			currentSizeMapX = zoomX*map.getLargeur();
			currentSizeMapY = zoomY*map.getHauteur();
			camX = camX-(currentSizeMapX-lastSizeMapX)/2;
			camY = camY-(currentSizeMapY-lastSizeMapY)/2;
		}
		else if(currentSizeMapX+camX >= SIZE_WINDOW_X){
			camX=0;
		}
		else{
			if(map.getLargeur() >= map.getHauteur()){
				zoomX = (float)SIZE_WINDOW_Y/(float)map.getHauteur();
				zoomX = SIZE_WINDOW_X/(float)map.getLargeur();
				zoomY = zoomX;
			}
			else{
				zoomX = (float)SIZE_WINDOW_Y/(float)map.getHauteur();
				zoomY = SIZE_WINDOW_Y/(float)map.getHauteur();
				zoomX = zoomY;
			}
		}
		if(currentSizeMapX+camX<SIZE_WINDOW_X-bordure_centrageX){camX = camX+(SIZE_WINDOW_X-currentSizeMapX-camX);}
		if(currentSizeMapY+camY<SIZE_WINDOW_Y){camY = camY+(SIZE_WINDOW_Y-currentSizeMapY-camY);}

	}
	*/
	public static void cameraDezoom(Map map){
		zoomX -= ZOOM; zoomY -= ZOOM;
		/*float lastSizeMapX = currentSizeMapX;
		float lastSizeMapY = currentSizeMapY;
		if((zoomX-ZOOM)*currentSizeMapX >= SIZE_WINDOW_X && 
				(zoomY-ZOOM)*currentSizeMapY >= SIZE_WINDOW_Y){
			zoomX -= ZOOM; zoomY -= ZOOM;
			currentSizeMapX = zoomX*map.getLargeur();
			currentSizeMapY = zoomY*map.getHauteur();
			camX = camX-(currentSizeMapX-lastSizeMapX)/2;
			camY = camY-(currentSizeMapY-lastSizeMapY)/2;
		}
		else{
			if(SIZE_WINDOW_X/map.getLargeur()<=SIZE_WINDOW_Y/map.getHauteur()){
				zoomX = (float)SIZE_WINDOW_Y/(float)map.getHauteur();
				zoomY = zoomX;
				camX = -bordure_centrageX;
				camY =0;
				currentSizeMapX = SIZE_WINDOW_X-bordure_centrageX;
			}
			else{
				zoomX = (float)SIZE_WINDOW_X/(float)map.getLargeur();
				zoomY = zoomX;
				camY = -bordure_centrageY;
				camX =0;
				currentSizeMapY = SIZE_WINDOW_Y-bordure_centrageY;
			}
		}
		if(currentSizeMapX+camX<SIZE_WINDOW_X){camX = camX+(SIZE_WINDOW_X-currentSizeMapX-camX)+bordure_centrageX;}
		if(currentSizeMapY+camY<SIZE_WINDOW_Y){camY = camY+(SIZE_WINDOW_Y-currentSizeMapY-camY)+bordure_centrageY;}
		if(currentSizeMapX-camX>=SIZE_WINDOW_X){camX = -bordure_centrageX;}
		if(currentSizeMapY-camY>=SIZE_WINDOW_Y){camY = -bordure_centrageY;}*/
	}

	public static void cameraZoom(Map map){
		/*float lastSizeMapX = currentSizeMapX;
		float lastSizeMapY = currentSizeMapY;
		if(zoomX<=ZOOM_MAX && zoomY<=ZOOM_MAX){
			zoomX += ZOOM; zoomY += ZOOM;
			currentSizeMapX = zoomX*(map.getLargeur()-bordure_centrageX);
			currentSizeMapY = zoomY*(map.getHauteur()-bordure_centrageY);
			camX = camX-(currentSizeMapX-lastSizeMapX)/2;
			camY = camY-(currentSizeMapY-lastSizeMapY)/2;
		}	*/
		zoomX += ZOOM; zoomY += ZOOM;
	}
}
