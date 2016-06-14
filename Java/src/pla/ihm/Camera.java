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
	
	public static void moveCamera(Graphics g){
		g.translate(camX, camY);
		g.scale(zoomX, zoomY);

	}
	
	public static void initCamera(Map map, int largeur, int hauteur){
		currentSizeMapX = map.getLargeur();
		currentSizeMapY = map.getHauteur();
		SIZE_WINDOW_X = largeur;
		SIZE_WINDOW_Y = hauteur;
	}
	public static void cameraDown(){
		if(camY-DEPLACEMENT >= -currentSizeMapY+SIZE_WINDOW_Y){camY-=DEPLACEMENT;}
		else{camY = camY - (camY +currentSizeMapY-SIZE_WINDOW_Y);}		
		
	}

	public static void cameraUP(){
		if(camY+DEPLACEMENT <= 0){camY+=DEPLACEMENT;}
		else{camY = 0;}
		
	}

	public static void cameraLEFT(){
		if(camX+DEPLACEMENT <= 0){camX+=DEPLACEMENT;}
		else{camX = 0;}
		
	}

	public static void cameraRIGHT(){
		if(camX-DEPLACEMENT >= -currentSizeMapX+SIZE_WINDOW_X){camX-= DEPLACEMENT;}
		else{camX = camX - (camX +currentSizeMapX-SIZE_WINDOW_X);}
		
	}

	public static void cameraDezoom(Map map){
		float lastSizeMapX = currentSizeMapX;
		float lastSizeMapY = currentSizeMapY;
		if((zoomX-ZOOM)*map.getLargeur() >= SIZE_WINDOW_X && (zoomY-ZOOM)*map.getHauteur() >= SIZE_WINDOW_Y
			/*	&& currentSizeMapX+camX < SIZE_WINDOW_X && camX<=0*/){
			zoomX -= ZOOM; zoomY -= ZOOM;
			currentSizeMapX = zoomX*map.getLargeur();
			currentSizeMapY = zoomY*map.getHauteur();
			camX = camX-(currentSizeMapX-lastSizeMapX)/2;
			camY = camY-(currentSizeMapY-lastSizeMapY)/2;
		}
		/*else if(currentSizeMapX+camX >= SIZE_WINDOW_X){
			camX=0;
		}*/
		else{
			if(map.getLargeur() >= map.getHauteur()){
				zoomX = SIZE_WINDOW_X/(float)map.getLargeur();
				zoomY = zoomX;
			}
			else{
				zoomY = SIZE_WINDOW_Y/(float)map.getHauteur();
				zoomX = zoomY;
			}
		}
		if(currentSizeMapX+camX<SIZE_WINDOW_X){camX = camX+(SIZE_WINDOW_X-currentSizeMapX-camX);}
		if(currentSizeMapY+camY<SIZE_WINDOW_Y){camY = camY+(SIZE_WINDOW_Y-currentSizeMapY-camY);}

	}

	public static void cameraZoom(Map map){
		float lastSizeMapX = currentSizeMapX;
		float lastSizeMapY = currentSizeMapY;
		if(zoomX<=ZOOM_MAX && zoomY<=ZOOM_MAX){
			zoomX += ZOOM; zoomY += ZOOM;
			currentSizeMapX = zoomX*map.getLargeur();
			currentSizeMapY = zoomY*map.getHauteur();
			camX = camX-(currentSizeMapX-lastSizeMapX)/2;
			camY = camY-(currentSizeMapY-lastSizeMapY)/2;
		}		
	}
}
