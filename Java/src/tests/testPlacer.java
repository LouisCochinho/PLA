package tests;

import java.util.List;
import java.util.Random;
import pla.ihm.Map;
import pla.Personnage;

public class testPlacer {
	
	private static final int CERCLE = 360;
	private static final int DISTANCE = 2;
	
	public void placerAutoRandom(Map m, List<Personnage> lPersonnage){
		Random rand =  new Random();
		rand.setSeed(5);
		int nbAutomate = lPersonnage.size();
		int rayonCercle, centreCercleX, centreCercleY, anglePersonnage, firstAngle, hmax, lmax;
		
		hmax = hauteurMax(lPersonnage);
		lmax = largeurMax(lPersonnage);
		
		if(hmax < lmax){
			rayonCercle = lmax*(nbAutomate)/2 + nbAutomate;
		}
		else{
			rayonCercle = hmax*(nbAutomate)/2 + nbAutomate;
		}
		centreCercleX = rayonCercle;//+varx
		centreCercleY = rayonCercle;//+vary
		
		anglePersonnage = CERCLE/nbAutomate;
		firstAngle = rand.nextInt()*(CERCLE+1);
		
		lPersonnage.get(0).getAutomate().setPosX(rayonCercle*(int)Math.cos(firstAngle)+centreCercleX);
		lPersonnage.get(0).getAutomate().setPosX(rayonCercle*(int)Math.sin(firstAngle)+centreCercleY);
		
		for(int i=1; i<nbAutomate; i++){
			rayonCercle += anglePersonnage;
			lPersonnage.get(i).getAutomate().setPosX(rayonCercle*(int)Math.cos(firstAngle));
			lPersonnage.get(i).getAutomate().setPosX(rayonCercle*(int)Math.sin(firstAngle));
		}
	}
	
	private int hauteurMax(List<Personnage> lPersonnage){
		int hmax=0;
		for(int i=0; i<lPersonnage.size(); i++){
			if(lPersonnage.get(i).getAutomate().getNbLignes() > hmax){
				hmax = lPersonnage.get(i).getAutomate().getNbColonnes();
			}
		}
		return hmax;
	}
	
	private int largeurMax(List<Personnage> lPersonnage){
		int lmax=0;
		for(int i=0; i<lPersonnage.size(); i++){
			if(lPersonnage.get(i).getAutomate().getNbColonnes() > lmax){
				lmax = lPersonnage.get(i).getAutomate().getNbLignes();
			}
		}
		return lmax;
	}
	
	void placerPersonnageRandom(Map m, List<Personnage> lPersonnage){
		Random rand =  new Random();
		rand.setSeed(5);
		
		int posX, posY;
		int w = m.getCases().length;
		int h = m.getCases()[0].length;
		
		do{
			posX = rand.nextInt()*w;
			posY = rand.nextInt()*h;
		}while(m.getCases()[posX][posY].getNbPersonnage() != 0);
		
		lPersonnage.get(0).setX(posX);
		lPersonnage.get(0).setY(posY);
		
		for(int i=1; i<lPersonnage.size(); i++){
			do{
				posX = rand.nextInt()*w;
				posY = rand.nextInt()*h;				
			}while(m.getCases()[posX][posY].getNbPersonnage() != 0 || personnagePresent(lPersonnage, posX, posY, i));

			lPersonnage.get(i).setX(posX);
			lPersonnage.get(i).setY(posY);			
		}
		
	}

	private boolean personnagePresent(List<Personnage> lPersonnage, int posX, int posY, int i) {
		boolean present = false;
		for(int j=0; j<i; j++){
			if((lPersonnage.get(j).getX() - posX)+ (lPersonnage.get(j).getY() - posY) <= DISTANCE ||
					(lPersonnage.get(j).getX() - posX)+ (lPersonnage.get(j).getY() - posY) <= DISTANCE){
				present = true;
			}
		}
		
		return present;
	}


	
}
