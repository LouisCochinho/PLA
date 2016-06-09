package tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import pla.Jeu;

public class TestFenetre {

	// conteneur qui permet de configurer dans quel contexte va tourner notre
	// jeu et lui dire quznd démarrer, s'arreter, etc.
	static private AppGameContainer app;

	public static void main(String[] args) throws SlickException {
		// Ce conteneur s'instancie en passant un objet implémentant Game (dont
		// BasicGame) comme notre fenetre, à son constructeur
		app = new AppGameContainer(new Jeu("Thug tag"));
		// Spécifie le mode d'affichage : le deuxieme parametre est le mode
		// plein ecran ou non
		app.setDisplayMode(640, 480, true);
		// Démarre le jeu
		app.start();
	}
}
