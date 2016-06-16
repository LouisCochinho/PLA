package pla;

import java.util.TimerTask;


import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TimerFin extends TimerTask{
	static boolean fin=false;

	private static int currentTime=10;

	//private static int currentTime=0;
	
	@Override
	public void run() {
		
		if(currentTime>0){
			currentTime--;
		}
		else{
			Jeu.t.cancel();
			fin=true;
		}	
	}
	
	public static boolean getFinJeu(){
		return fin;
	}
	public static void afficherTimer(Graphics g, Image timerI) throws SlickException{
		int min, seconde, centrerX=1205, centrerY=62;
		min = currentTime/60;
		seconde = currentTime%60;
		
		if(seconde<10 && min<10){
			centrerX = 1200;
		}
		else if(seconde>=10 && min<10 || seconde<10 && min>=10){
			centrerX = 1195;
		}
		else{
			centrerX = 1190;
		}
		
		
		
		g.resetTransform();
		g.drawImage( timerI, 1170, 25);
		//g.scale(2, 2);
		g.setColor(Color.orange);	
		g.drawString(Integer.toString(min)+":"+Integer.toString(seconde) ,centrerX, centrerY);
	}
	
	

}
