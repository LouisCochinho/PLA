package pla;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import pla.action.transition.*;
import pla.decor.*;
import org.newdawn.slick.gui.MouseOverArea;

import pla.TimerFin;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pla.ihm.Camera;

import pla.ihm.Case;

import pla.ihm.Map;
import pla.util.Musique;

public class Jeu extends BasicGameState {
	private Map map; // carte du jeu
	private List<Personnage> personnages = new ArrayList<Personnage>(); // Liste																	// des
	public static final int ID = 1; // personnages
	private GameContainer gc; // conteneur


	Image ImageNoire;
	
	Music test2;
	
	private boolean MusicEnable = true; 
	Image play, play2, play3;
	

	private int SIZE_WINDOW_X ;
	private int SIZE_WINDOW_Y ;


	Musique musique;
	private Image inventaire_rouge, inventaire_rouge_eau, inventaire_rouge_bombe, inventaire_rouge_bike,
			inventaire_rouge_eau_bike, inventaire_rouge_bombe_bike;
	private Image inventaire_bleu, inventaire_bleu_eau, inventaire_bleu_bombe, inventaire_bleu_bike,
			inventaire_bleu_eau_bike, inventaire_bleu_bombe_bike;
	private Image score_rouge, score_bleu;

	private Image rougegagnant, bleugagnant,egaliter;


	


	private Image bouton_fin;
	
	int rouge_score, bleu_score;
	String rouge_score1, bleu_score1;
	
	MouseOverArea ms;
	
	public boolean fouad = true;
	
	//private static final int P_BAR_X = 15;
	//private static final int P_BAR_Y = 25;




	static Timer t;

	// private static final int PAUSE = 25; // temps de latence

	// private float zoom = 0.1f;

	/*
	 * private float z1 = 0.01f; private float z2 = 0.01f;
	 */


	Image timerI;
        
        String cheminXML = "../Ocaml/xml/";
        
	public Jeu(int largeur, int hauteur) {
		SIZE_WINDOW_X = largeur;

		SIZE_WINDOW_Y = hauteur;

		personnages = new ArrayList<Personnage>();
	}

	public void ajouterPersonnage(Personnage p) {
		if (p != null) {
			this.personnages.add(p);
		} else {
			System.out.println("Le personnage que vous voulez ajouter dans la liste des personnages est vide");
		}
	}

	public void supprimerPersonnage(Personnage p) {
		if (p != null && this.personnages.contains(p)) {
			this.personnages.remove(p);
		} else {
			System.out.println("Le personnage que vous voulez supprimer n'est pas dans la liste ou est nul");
		}
	}

	// Initialise le contenu du jeu, charge les graphismes, la musique, etc..
	@Override
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////// INIT //////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {

		this.gc = gc;

		
		ImageNoire = new Image("res/ImageNoire.png");
		

		timerI = new Image("res/modif.png");
		
		if (MusicEnable) {
			test2 = new Music("res/thug.ogg");
			//test2.loop();
		}
                
		play = new Image("res/menu/pause/reprendre.png");
		play2 = new Image("res/menu/pause/accueil.png");
		play3 = new Image("res/menu/pause/quitter.png");
                
		this.inventaire_rouge = new Image("res/hud/rouge/rougevide.png");
		this.inventaire_rouge_eau = new Image("res/hud/rouge/rougevide_eau.png");
		this.inventaire_rouge_bombe = new Image("res/hud/rouge/rougevide_bombe.png");
		this.inventaire_rouge_bike = new Image("res/hud/rouge/rougevide_bike.png");
		this.inventaire_rouge_eau_bike = new Image("res/hud/rouge/rougevide_eau_bike.png");
		this.inventaire_rouge_bombe_bike = new Image("res/hud/rouge/rougevide_bombe_bike.png");
		this.inventaire_bleu = new Image("res/hud/bleu/bleuvide.png");
		this.inventaire_bleu_eau = new Image("res/hud/bleu/bleuvide_eau.png");
		this.inventaire_bleu_bombe = new Image("res/hud/bleu/bleuvide_bombe.png");
		this.inventaire_bleu_bike  = new Image("res/hud/bleu/bleuvide_bike.png");
		this.inventaire_bleu_eau_bike  = new Image("res/hud/bleu/bleuvide_eau_bike.png");
		this.inventaire_bleu_bombe_bike  = new Image("res/hud/bleu/bleuvide_bombe_bike.png");
		
		//Image score
		this.score_rouge=new Image("res/hud/score/scorerouge.png");
		this.score_bleu=new Image("res/hud/score/scorebleu.png");
		
		//Image fin jeu
		this.rougegagnant  = new Image("res/rougegagnant.png");
		this.bleugagnant  = new Image("res/bleugagnant.png");
		this.egaliter  = new Image("res/egaliter.png");
		this.bouton_fin  = new Image("res/bouton_fin.png");

		//bouton fin cliquable
		// ms = new MouseOverArea(gc, bouton_fin, (gc.getWidth()/2)-80, (gc.getHeight()/2)+150, 245, 110);

	}
        
        public void initAutomates() throws SlickException {
            try {
                ajouterPersonnage(new Personnage(TypePersonnage.BLEU, 2, 64, 64, new Automate(cheminXML + "joueur1.xml")));
            } catch (IOException ex) {
                try {
                    ajouterPersonnage(new Personnage(TypePersonnage.BLEU, 2, 64, 64, new Automate(cheminXML + "defaut.xml")));
                } catch (IOException ex1) {
                    Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            try {
                ajouterPersonnage(new Personnage(TypePersonnage.ROUGE, 1, 64, 64, new Automate(cheminXML + "joueur2.xml")));
            } catch (IOException ex) {
                try {
                    ajouterPersonnage(new Personnage(TypePersonnage.ROUGE, 1, 64, 64, new Automate(cheminXML + "defaut.xml")));
                } catch (IOException ex1) {
                    Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

            try {
                ajouterPersonnage(new Personnage(TypePersonnage.BERNARD, 3, 64, 64, new Automate(cheminXML + "automateBernard.xml")));
            } catch (IOException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }


            map = new Map((int) SIZE_WINDOW_X, (int) SIZE_WINDOW_Y, personnages);

            this.map.init();
            Camera.initCamera(map, SIZE_WINDOW_X, SIZE_WINDOW_Y);
            for (Personnage p : personnages) {
                    p.init();
            }

            this.map.placerAutoRandom(personnages, gc.getGraphics());		
            this.map.setCasesEstDansAutomate(personnages);
            this.map.setNbCasesHorsAutomate();
            this.map.placerDecorRandom();
            this.map.placerPersonnageRandom(personnages);
        }

	// Affiche le contenu du jeu
	@Override
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////// RENDER ////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		TimerFin.getFinJeu();
		
		
		if (!TimerFin.getFinJeu() && !gc.isPaused() ) {    //Le jeu quand il est pas en pause
			Camera.moveCamera(g);

			this.map.afficher();
			for (Personnage p : personnages) {
				p.afficher(g);
			}
			g.drawString(Mouse.getX() + " " +  Mouse.getY(), 10,30);
			
		} else if (!TimerFin.getFinJeu() && gc.isPaused()) {   // Le jeu quand il est en pause et que le timer est fini
			
			
			Camera.moveCamera(g);

			this.map.afficher();
			for (Personnage p : personnages) {
				p.afficher(g);
			}
			g.resetTransform();
			ImageNoire.setAlpha(0.7f);
			ImageNoire.draw(0,0,gc.getWidth(),gc.getHeight());   // Taille de la fenetre modifiée par Antoine
			play.draw(gc.getWidth()/2-175,12*gc.getHeight()/20-37);
			play2.draw(gc.getWidth()/2-175,15*gc.getHeight()/20-37);
			play3.draw(gc.getWidth()/2-175,18*gc.getHeight()/20-37);
			g.setColor(Color.white);
			g.drawString("PAUSE",gc.getWidth()/2-20,4*gc.getHeight()/10);
			g.drawString(Mouse.getX() + " " +  Mouse.getY(), 10,30);
		
			
			
		} else if (TimerFin.getFinJeu() ) {   // Le jeu quand il est en pause et que le timer n'est pas fini
			
			
			if(rouge_score>bleu_score){
				g.resetTransform();  
				g.drawImage(this.rougegagnant, (gc.getWidth()/2)-162.5f, (gc.getHeight()/2)-350);
				g.drawImage(this.bouton_fin, (gc.getWidth()/2)-122.5f, (6*gc.getHeight()/10)+100);
				g.drawImage(this.score_rouge, (gc.getWidth()/2)-162.5f-125, (gc.getHeight()/2));
				g.drawImage(this.score_bleu, (gc.getWidth()/2)+162.5f, (gc.getHeight()/2));
				g.setColor(Color.yellow);
				g.drawString(this.bleu_score1, (gc.getWidth()/2)+162.5f+64, (gc.getHeight()/2)+75);
				g.drawString(this.rouge_score1, gc.getWidth()/2-162.5f-63, (gc.getHeight()/2)+75);

			}
			else if(rouge_score<bleu_score){
				g.resetTransform();  
				g.drawImage(this.bleugagnant, (gc.getWidth()/2)-162.5f, (gc.getHeight()/2)-350);
				g.drawImage(this.bouton_fin, (gc.getWidth()/2)-122.5f, (6*gc.getHeight()/10)+100);
				g.drawImage(this.score_rouge, (gc.getWidth()/2)-162.5f-125, (gc.getHeight()/2));
				g.drawImage(this.score_bleu, (gc.getWidth()/2)+162.5f, (gc.getHeight()/2));
				g.setColor(Color.yellow);
				g.drawString(this.bleu_score1, (gc.getWidth()/2)+162.5f+64, (gc.getHeight()/2)+75);
				g.drawString(this.rouge_score1, (gc.getWidth()/2)-162.5f-63, (gc.getHeight()/2)+75);

			} else if(rouge_score==bleu_score){
				g.resetTransform();  
				g.drawImage(this.egaliter, (gc.getWidth()/2)-162.5f, (gc.getHeight()/2)-350);
				g.drawImage(this.bouton_fin, (gc.getWidth()/2)-122.5f, (6*gc.getHeight()/10)+100);
				g.drawImage(this.score_rouge, (gc.getWidth()/2)-162.5f-125, (gc.getHeight()/2));
				g.drawImage(this.score_bleu, (gc.getWidth()/2)+162.5f, (gc.getHeight()/2));
				g.setColor(Color.yellow);
				g.drawString(this.bleu_score1, (gc.getWidth()/2)+162.5f+64, (gc.getHeight()/2)+75);
				g.drawString(this.rouge_score1, (gc.getWidth()/2)-162.5f-63, (gc.getHeight()/2)+75);
			}
			
		

		}

		TimerFin.afficherTimer( g, timerI);
		//inventaire
		
		if (gc.getInput().isKeyDown(Input.KEY_I)) {
			// rouge
			if (getPersonnageParType(TypePersonnage.ROUGE).getObjet() == null
					&& !getPersonnageParType(TypePersonnage.ROUGE).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_rouge, 15, 40);
			}
			if (getPersonnageParType(TypePersonnage.ROUGE).getObjet() instanceof BombePeinture
					&& !getPersonnageParType(TypePersonnage.ROUGE).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_rouge_bombe, 15, 40);
			}
			if (getPersonnageParType(TypePersonnage.ROUGE).getObjet() instanceof BombeEau
					&& !getPersonnageParType(TypePersonnage.ROUGE).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_rouge_eau, 15, 40);
			}
			if (getPersonnageParType(TypePersonnage.ROUGE).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_rouge_bike, 15, 40);
			}
			if (getPersonnageParType(TypePersonnage.ROUGE).getObjet() instanceof BombeEau
					&& getPersonnageParType(TypePersonnage.ROUGE).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_rouge_eau_bike, 15, 40);
			}
			if (getPersonnageParType(TypePersonnage.ROUGE).getObjet() instanceof BombePeinture
					&& getPersonnageParType(TypePersonnage.ROUGE).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_rouge_bombe_bike, 15, 40);
			}
			// bleu
			if (getPersonnageParType(TypePersonnage.BLEU).getObjet() == null
					&& !getPersonnageParType(TypePersonnage.BLEU).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_bleu, 15, 105);
			}
			if (getPersonnageParType(TypePersonnage.BLEU).getObjet() instanceof BombePeinture
					&& !getPersonnageParType(TypePersonnage.BLEU).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_bleu_bombe, 15, 105);
			}
			if (getPersonnageParType(TypePersonnage.BLEU).getObjet() instanceof BombeEau
					&& !getPersonnageParType(TypePersonnage.BLEU).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_bleu_eau, 15, 105);
			}
			if (getPersonnageParType(TypePersonnage.BLEU).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_bleu_bike, 15, 105);
			}
			if (getPersonnageParType(TypePersonnage.BLEU).getObjet() instanceof BombeEau
					&& getPersonnageParType(TypePersonnage.BLEU).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_bleu_eau_bike, 15, 105);
			}
			if (getPersonnageParType(TypePersonnage.BLEU).getObjet() instanceof BombePeinture
					&& getPersonnageParType(TypePersonnage.BLEU).hasVelo()) {
				g.resetTransform();
				g.drawImage(this.inventaire_bleu_bombe_bike, 15, 105);
			}
		}

		// afficher score
		if (gc.getInput().isKeyDown(Input.KEY_TAB)) {
			g.resetTransform();
			g.drawImage(this.score_rouge, (gc.getWidth()/2)-80, (gc.getHeight()/2));
			g.drawImage(this.score_bleu, (gc.getWidth()/2)+80, (gc.getHeight()/2));
			g.setColor(Color.yellow);
			g.drawString(this.bleu_score1, (gc.getWidth()/2)+140, (gc.getHeight()/2)+75);
			g.drawString(this.rouge_score1, (gc.getWidth()/2)-24, (gc.getHeight()/2)+75);
		}


		

			
			
		}


	

	// Met � jour les �l�ments de la sc�ne en fonction du delta temps
	// survenu.
	// C'est ici que la logique du jeu est enferm�e.
	@Override
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////// UPDATE ////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+TimerFin.getFinJeu()+"<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		ArrayList<Personnage> personnages = new ArrayList<Personnage>(this.personnages);
		for (Personnage p : personnages) {
			if (p.isDeplacementTermine()) {
				//System.out.println("Tableau avant : \n");
				//p.getAutomate().afficher();
				changerEtatAutomate(p, delta);


                                p.decrementeToursVelo();

			}

			map.getCaseFromCoord((int) p.getX(), (int) p.getY()).supprimerPersonnage(p);
			deplacerPersonnage(p, delta);
			map.getCaseFromCoord((int) p.getX(), (int) p.getY()).ajouterPersonnage(p);

		}

		if (gc.getInput().isKeyPressed(Input.KEY_M) && gc.isMusicOn() && MusicEnable) {

			test2.resume();

		}

		if (gc.getInput().isKeyPressed(Input.KEY_P) && MusicEnable) {

			test2.pause();
		}	

		if (gc.getInput().isKeyDown(Input.KEY_UP)) {
			Camera.cameraUP();
		}
		if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			Camera.cameraDown();
		}
		if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
			Camera.cameraRIGHT();
		}
		if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			Camera.cameraLEFT();
		}

		
		if(gc.getInput().isKeyPressed(Input.KEY_F1)){
			TimerFin.pause();
			gc.setPaused(!gc.isPaused());


		}
		

		if(gc.isPaused() && !TimerFin.getFinJeu()) {
			
			if((posX>gc.getWidth()/2-175 && posX<gc.getWidth()/2+175)&&(posY>8*gc.getHeight()/20-37 && posY<8*gc.getHeight()/20+37)){ // Reprendre
				if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					gc.setPaused(!gc.isPaused());
				}
			}
			
			if((posX>gc.getWidth()/2-175 && posX<gc.getWidth()/2+175)&&(posY>5*gc.getHeight()/20-37 && posY<5*gc.getHeight()/20+37)){ // Accueil
				if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					gc.setPaused(!gc.isPaused());
					game.enterState(0);
					gc.reinit();
				}
			}
			
			if((posX>gc.getWidth()/2-175 && posX<gc.getWidth()/2+175)&&(posY>2*gc.getHeight()/20-37 && posY<2*gc.getHeight()/20+37)){ // Quitter
				if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					gc.exit();
				}
			}
		}
		
		if(TimerFin.getFinJeu()) {
			
			if((posX>gc.getWidth()/2-122.5f && posX<gc.getWidth()/2+122.5f) && (posY>4*gc.getHeight()/10-100 && posY<4*gc.getHeight()/10+100)){ // Quitter
				if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					gc.setPaused(!gc.isPaused());
					game.enterState(0);
					gc.reinit();
				}
			
		}
		
		bleu_score=getPersonnageParType(TypePersonnage.BLEU).compterScore(map);
		rouge_score=getPersonnageParType(TypePersonnage.ROUGE).compterScore(map);
		bleu_score1=Integer.toString(bleu_score);
		rouge_score1=Integer.toString(rouge_score);
		
		
		if (TimerFin.getFinJeu() && fouad) {
			gc.setPaused(!gc.isPaused());
			fouad = false;
		}
		}

		}	
	
	public void mouseWheelMoved(int change) {
		if (change < 0) {
			Camera.cameraDezoom(map);
		} else {
			Camera.cameraZoom(map);
		}
	}

	// Arreter correctement le jeu en appuyant sur ECHAP
	@Override
	public void keyReleased(int key, char c) {

		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}

	public void changerEtatAutomate(Personnage p, int delta) {

		ArrayList<Integer> indexPossibles = new ArrayList<Integer>();
		int etatCourantId = p.getEtatCourant().getId();
		Random r = new Random();
		int indexChoisi = 0;

		for (int i = 0; i < p.getAutomate().getNbLignes(); i++) {
			Condition c = p.getAutomate().getTabCondition()[i][etatCourantId];
			if (c.nombreConditions() != 0 && c.estVerifiee(p, map)) {
				indexPossibles.add(i);
			}
		}

		if (!indexPossibles.isEmpty()) {
			// Prendre un index au hasard dans la liste
			indexChoisi = indexPossibles.get(r.nextInt(indexPossibles.size()));

			Case c1 = map.getCaseFromCoord((int) p.getX(), (int) p.getY());
			Decor d1 = c1.getDecor();
			p.getAutomate().getTabActionTransition()[indexChoisi][etatCourantId].executer(p,
					map.getCaseFromCoord((int) p.getX(), (int) p.getY()), this, 0);
			
			Case c2 = map.getCaseFromCoord((int) p.getX(), (int) p.getY());
			Decor d2 = c2.getDecor();
	
			if(!(d1.toString().equals(d2.toString())) && c1.estDansAutomate() && c2.estDansAutomate()){
				Automate a = null;
				for(Personnage pers : personnages){
					if(map.caseEstDansAutomate(c2, pers.getAutomate())){
						a = pers.getAutomate();
						if(a.getPosX()!=p.getAutomate().getPosX() && a.getPosY()!=p.getAutomate().getPosY()){
							pers.getAutomate().modifierTabActionTransition((c2.getIndexI()-a.getPosX()/64),(c2.getIndexJ()-a.getPosY()/64), c2.getDecor());
						}						
						break;
					}
				}				
			}
			p.setEtatCourant(p.getAutomate().getTabEtatSuivant()[indexChoisi][etatCourantId]);

		} else {
			p.setEtatCourant(p.getAutomate().getEtatInitial());
		}
		p.setDeplacementCourant(0);


	}

	public void deplacerPersonnage(Personnage p, int delta) {
		p.deplacer(delta, map.getLargeur(), map.getHauteur());
	}

	public Map getMap() {
		return map;
	}

	@Override
	public int getID() {
		return ID;
	}


    public List<Personnage> getPersonnages() {
        return personnages;
    }

    public void enter(GameContainer gc, StateBasedGame game) {
    	gc.getInput().clearMousePressedRecord();
    	if (MusicEnable) {
    		test2.loop();
    	}
    	//System.out.println(">>>>>>> ENTRER DANS JEU <<<<<<<"); 
    }
    
    public void leave(GameContainer gc, StateBasedGame game) {
    	//System.out.println(">>>>>>> SORTIE DE JEU <<<<<<<");
    }	
	public static void finDuJeu(){
	    t = new Timer();
	    t.schedule(new TimerFin(), 0, 1*1000);

	}


        public ArrayList<String> getFichiersAutomate() {
            ArrayList<String> noms = new ArrayList<String>();
            File repertoire = new File("../Ocaml/xml/");
            File[] fichiers = repertoire.listFiles();
            for(File f : fichiers) {
                noms.add(f.getName().substring(0, f.getName().lastIndexOf(".")));
                System.out.println(noms.get(noms.size()-1));
            }
            return noms;
        }

	public Personnage getPersonnageParType(TypePersonnage t) {
		for (Personnage p : personnages) {
			if (p.getTypePersonnage() == t)
				return p;
		}
		return null;
	}
        
        public int getNbPersonnagesParType(TypePersonnage t) {
            int nb = 0;
            for(Personnage p : personnages) {
                if(p.getTypePersonnage() == t) {
                    nb++;
                }
            }
            return nb;
        }


        	
}
