package pla.util;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Musique {

	public static Music soundMenu, soundJeu;

	public Musique() {
		try {
			this.soundMenu = new Music("res/ThugMenu.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.soundJeu = new Music("res/thug.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void jouerMenu() {

		soundMenu.play();

	}

	public void stopMenu() {
		
		soundMenu.stop();
	}
	
	public void jouerJeu() {

		soundJeu.play();
	}

	public void resumeJeu() {
		
		soundJeu.resume();
	}
	
	public void pauseJeu() {
		
		soundJeu.pause();
	}
	
	public void stopJeu() {
		
		soundJeu.stop();
	}
}

