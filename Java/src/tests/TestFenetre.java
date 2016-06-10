package tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import pla.Jeu;

public class TestFenetre {

	// conteneur qui permet de configurer dans quel contexte va tourner notre
	// jeu et lui dire quznd d�marrer, s'arreter, etc.
	static private AppGameContainer app;

	public static void main(String[] args) throws SlickException {
		// Ce conteneur s'instancie en passant un objet impl�mentant Game
		// (dont
		// BasicGame) comme notre fenetre, � son constructeur
		app = new AppGameContainer(new Jeu("Thug tag"));
		// Sp�cifie le mode d'affichage : le deuxieme parametre est le mode
		// plein ecran ou non

		app.setDisplayMode(1280, 1024, false);
		// D�marre le jeu

		app.setIcon("res/logo.png");
		app.start();
	}
}
