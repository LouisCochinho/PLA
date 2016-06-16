package pla.ihm;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.*;

import pla.Jeu;
import pla.util.Musique;

public class Menu extends BasicGameState {

	 public static final int ID = 0;
	 //private Image fond;
	 private StateBasedGame game;
	 Image background;
	 Image titreMenu, play, play2, play3, play4, play5, play6, play7, play8, accueil, timerI;
	 Image exitGame;
	 //Musique musique = new Musique();
	 Music test1;
	 MouseOverArea ms, ms2, ms3, ms4, ms5, ms6, ms7, ms8;
	 
	 private boolean CreditOn = false;
	 private boolean ToucheOn = false;
	 
	 private boolean MusicEnable = true;


	public Menu() {

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////// INIT //////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

		accueil = new Image("res/menu/pause/accueil.png");
		ms = new MouseOverArea(gc, play, (gc.getWidth()/2)-100, 300, 200, 100);
		ms2 = new MouseOverArea(gc, play2, (gc.getWidth()/2)-100, 300, 200, 100);
		ms3 = new MouseOverArea(gc, play3, (gc.getWidth()/2)-100, 400, 200, 100);
		ms4 = new MouseOverArea(gc, play4, (gc.getWidth()/2)-100, 400, 200, 100);
		ms5 = new MouseOverArea(gc, play5, (gc.getWidth()/2)-100, 500, 200, 100);
		ms6 = new MouseOverArea(gc, play6, (gc.getWidth()/2)-100, 500, 200, 100);
		ms7 = new MouseOverArea(gc, play7, (gc.getWidth()/2)-100, 600, 200, 100);
		ms8 = new MouseOverArea(gc, play8, (gc.getWidth()/2)-100, 600, 200, 100);


		if (MusicEnable) {
			
			test1 = new Music("res/thugMenu.ogg");
			//test1.loop();
		}


			
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////// RENDER ////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void render(GameContainer gc,StateBasedGame sbg, Graphics g) throws SlickException {
		

		
		if (CreditOn || ToucheOn) {
			if (CreditOn) {
				g.setColor(Color.orange);
				g.drawString(Mouse.getX() + " " +  Mouse.getY(), 10,30);
				accueil.draw(880,670);			
				g.drawString("Equipe de developpement", gc.getWidth()/3, 150);
				g.setColor(Color.white);
				g.drawString("Antoine BLANC", gc.getWidth()/3,200);
				g.drawString("Louis COCHINHO", gc.getWidth()/3,250);
				g.drawString("Raphaël FUSTES", gc.getWidth()/3,300);
				g.drawString("Antoine GAMBRO", gc.getWidth()/3,350);
				g.drawString("Fuad OUAZZANI-IBRAHIMI", gc.getWidth()/3,400);
				g.drawString("Damien SIEST", gc.getWidth()/3,450);
			} 
			if (ToucheOn){
				g.setColor(Color.orange);
				g.drawString(Mouse.getX() + " " +  Mouse.getY(), 10,30);
				accueil.draw(880,670);			
				g.drawString("Touches", gc.getWidth()/3, 150);
				g.setColor(Color.white);
				g.drawString("Flèches directionnelles :	  Caméra", gc.getWidth()/3,200);
				g.drawString("Molette de la souris    :	  Zoom", gc.getWidth()/3,250);
				g.drawString("F1                      :	  Menu Pause", gc.getWidth()/3,300);
				g.drawString("Tab                     :	  Score", gc.getWidth()/3,350);
				g.drawString("I                       :	  Inventaire", gc.getWidth()/3,400);
				g.drawString("P / M                   :	  Pause / Lecture musique", gc.getWidth()/3,450);
			}
		} else {
			background.draw(0, 0, gc.getWidth(), gc.getHeight());
			g.drawString(Mouse.getX() + " " +  Mouse.getY(), 10,30);
			g.drawString(Integer.toString(ms.getHeight()) +" " + Integer.toString(ms.getWidth() ), 10, 50);
			titreMenu.draw(gc.getWidth()/2-240, 60);
			if (ms.isMouseOver()) { ms.render(gc, g);} else { ms2.render(gc, g);}
			if (ms3.isMouseOver()) { ms3.render(gc, g);} else { ms4.render(gc, g);}
			if (ms5.isMouseOver()) { ms5.render(gc, g);} else { ms6.render(gc, g);}
			if (ms7.isMouseOver()) { ms7.render(gc, g);} else { ms8.render(gc, g);}
		}
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////// UPDATE ////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void update(GameContainer gc,StateBasedGame sbg, int delta) throws SlickException {
		System.out.println("CHRISTOPHER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		
		
		if((posX>541 && posX<740)&&(posY>369 && posY<466) && !(ToucheOn || CreditOn)){   // BOUTON JOUER
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				if (MusicEnable) {
					test1.stop();

				}
				System.out.println("start");
				Jeu.finDuJeu();
                                Jeu jeu = (Jeu)sbg.getState(1);
                                jeu.initAutomates();
				sbg.enterState(1);
				//gc.reinit();
			}
		}

		if((posX>541 && posX<740)&&(posY>268 && posY<367) && !CreditOn){   // BOUTON TOUCHES
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				ToucheOn = true;
			}
		}

		
		if((posX>541 && posX<740)&&(posY>168 && posY<267) && !ToucheOn){   // BOUTON CREDIT
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){

				CreditOn = true;
			}
		}
		
		if((posX>541 && posX<740)&&(posY>70 && posY<167) && !(ToucheOn || CreditOn)){   // BOUTON QUITTER
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				gc.exit();
			}
		}
		
		if((posX>890 && posX<1210)&&(posY>40 && posY<92)){   // BOUTON ACCUEIL_DANS_CREDIT
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				CreditOn = false;
				ToucheOn = false;
			}
		}
		

	}

	public void keyReleased(int key, char c) {
		// game.enterState(1);
	}

	public int getID() {
		return ID;
	}

	

    public void enter(GameContainer gc, StateBasedGame game) {
    	gc.getInput().clearMousePressedRecord();
    	if (MusicEnable) {
    		test1.loop();
    	}
    	//System.out.println(">>>>>>> ENTRER DANS MENU <<<<<<<"); 
    }
    
public void leave(GameContainer gc, StateBasedGame game) {
    	
    	
		if (MusicEnable) {
			test1.loop();
		}
    	//System.out.println(">>>>>>> SORTIE DE MENU <<<<<<<"); 
    	}


}
