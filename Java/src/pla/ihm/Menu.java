package pla.ihm;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.*;

import pla.Jeu;
import pla.util.Musique;

public class Menu extends BasicGameState {
	public static final int ID = 0;
	// private Image fond;
	private StateBasedGame game;
	Image background;
	Image titreMenu, play, play2, play3, play4, play5, play6, play7, play8, fouad;
	Image exitGame;
	Musique musique;
	MouseOverArea ms, ms2, ms3, ms4, ms5, ms6, ms7, ms8, msTouche, msCredit;

	private boolean MusicEnable = false;

	public Menu() {

	}

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {

		this.background = new Image("res/test.png");
		titreMenu = new Image("res/titre/titreGold.png");
		play = new Image("res/menu/image/doree/jouerdoree.png");
		play2 = new Image("res/menu/image/bleu/jouerbleu.png");
		play3 = new Image("res/menu/image/doree/touchedoree.png");
		play4 = new Image("res/menu/image/bleu/touchebleu.png");
		play5 = new Image("res/menu/image/doree/creditdoree.png");
		play6 = new Image("res/menu/image/bleu/creditbleu.png");
		play7 = new Image("res/menu/image/doree/quitterdoree.png");
		play8 = new Image("res/menu/image/bleu/quitterbleu.png");

		fouad = new Image("res/Fouad.png");

		if (MusicEnable) {
			musique = new Musique();
			musique.jouerMenu();
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0, gc.getWidth(), gc.getHeight());
		g.drawString(Mouse.getX() + " " + Mouse.getY(), 10, 30);
		g.drawString(Integer.toString(ms.getHeight()) + " " + Integer.toString(ms.getWidth()), 10, 50);
		titreMenu.draw(gc.getWidth() / 2 - 240, 60);
		if (ms.isMouseOver()) {
			ms.render(gc, g);
		} else {
			ms2.render(gc, g);
		}
		if (ms3.isMouseOver()) {
			ms3.render(gc, g);
		} else {
			ms4.render(gc, g);
		}
		if (ms5.isMouseOver()) {
			ms5.render(gc, g);
		} else {
			ms6.render(gc, g);
		}
		if (ms7.isMouseOver()) {
			ms7.render(gc, g);
		} else {
			ms8.render(gc, g);
		}
		if (ms3.isMouseOver()) {
			msTouche.render(gc, g);
		}
		if (ms5.isMouseOver()) {
			msCredit.render(gc, g);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();

		if ((posX > 541 && posX < 740) && (posY > 369 && posY < 466)) {
			if (Mouse.isButtonDown(0)) {

				if (MusicEnable) {
					musique.stopMenu();
					musique.jouerJeu();
				}

				sbg.enterState(1);
			}
		}
		if ((posX > 541 && posX < 740) && (posY > 70 && posY < 167)) {
			if (Mouse.isButtonDown(0)) {
				gc.exit();
			}
		}

	}

	public void keyReleased(int key, char c) {
		// game.enterState(1);
	}

	public int getID() {
		return ID;
	}

}
