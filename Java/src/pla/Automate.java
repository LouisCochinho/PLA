package pla;

import java.io.IOException;
import pla.action.transition.Admirer;
import pla.action.transition.Action_transition;
import java.util.ArrayList;
import java.util.Arrays;
import pla.action.transition.PeindreAmi;
import pla.action.transition.PeindreEnnemi;
import pla.decor.Decor;

public class Automate {
	private Transition tabTransition[][]; // Tableau des transitions 
	private Etat tabEtatSuivant[][]; // Tableau des etats suivants
	private Action_transition tabActionTransition[][]; // Tableau des actions-transition
	private Condition tabCondition[][]; // Tableau des conditions 
	private int nbLignes = 0; // nombre de lignes de l'automate
	private int nbColonnes = 0; // nombre de colonnes de l'automate
	private int posX; // Position en abcisse du coin supérieur gauche de l'automate
	private int posY; // Position en ordonnées du coin supérieur gauche de l'automate
	private ArrayList<Etat> etats; // Liste des états de l'automate
	private ArrayList<Transition> transitions; // Liste des transitions de l'automate
	private Transition transitionParDefaut; 
	private Action_transition actionParDefaut;
	private Etat etatInitial; 
	private Condition conditionParDefaut;


	//Constructeur qui parse le fichier filename passé en parametre et grâce à ce fichier, remplit les différents tableaux
	public Automate(String fileName, int posX, int posY) throws IOException {
		this.posX = posX;
		this.posY = posY;

		etats = new ArrayList<Etat>();
		transitions = new ArrayList<Transition>();

                XMLParser.parse(this, fileName);

		nbLignes = getNbTransitionsMax();
		nbColonnes = etats.size();

		actionParDefaut = new Admirer();
		etatInitial = etats.get(0);
		conditionParDefaut = new Condition();
		transitionParDefaut = new Transition(etatInitial, conditionParDefaut, actionParDefaut, etatInitial);

		initTabTransition();
		initTabActionTransition();
		initTabEtatSuivant();
		initTabCondition();
	}

	// Méthode retournant le nombre de transitions maximum
	private int getNbTransitionsMax() {
		int nbTransitions[] = new int[etats.size()];
		Arrays.fill(nbTransitions, 0); // Initialise le tableau avec la valeur 0
		for (Transition t : transitions) {
			nbTransitions[t.getEtatDepart().getId()]++;
		}
		return max(nbTransitions);
	}

	// Retourne le maximum d'un tableau d'entiers non vide
	private int max(int tab[]) {
		int max = tab[0];
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] > max) {
				max = tab[i];
			}
		}
		return max;
	}

	// Initialise le tableau des transitions 
	private void initTabTransition() {
		tabTransition = new Transition[nbLignes][nbColonnes];		
		for (Transition[] row : tabTransition) {
			Arrays.fill(row, transitionParDefaut);
		}
		int indiceCourant[] = new int[nbColonnes];
		Arrays.fill(indiceCourant, 0);
		for (Transition t : transitions) {
			int colonne = t.getEtatDepart().getId();
			tabTransition[indiceCourant[colonne]][colonne] = t;
			indiceCourant[colonne]++;
		}
	}

	// remplissage du tableau des actions transitions en dur => a changer
	private void initTabActionTransition() {

		// tabActionTransition = new int[4][4];
		tabActionTransition = new Action_transition[nbLignes][nbColonnes];
		for (Action_transition[] row : tabActionTransition) {
			Arrays.fill(row, actionParDefaut);
		}
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				if (tabTransition[i][j] == null) {
					tabActionTransition[i][j] = actionParDefaut;
				} else {
					tabActionTransition[i][j] = tabTransition[i][j].getActionTransition();
				}
			}
		}
	}
	// initialise le tableau des conditions
	private void initTabCondition() {
		tabCondition = new Condition[nbLignes][nbColonnes];
		for (Condition[] row : tabCondition) {
			Arrays.fill(row, conditionParDefaut);
		}
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				if (tabTransition[i][j] == null) {
					tabCondition[i][j] = conditionParDefaut;
				} else {
					tabCondition[i][j] = tabTransition[i][j].getCondition();
				}
			}
		}
	}
	
	// initialisation du tableau des etats suivants
		private void initTabEtatSuivant() {
			tabEtatSuivant = new Etat[nbLignes][nbColonnes];
			for (Etat[] row : tabEtatSuivant) {
				Arrays.fill(row, etatInitial);
			}
			for (int i = 0; i < nbLignes; i++) {
				for (int j = 0; j < nbColonnes; j++) {
					if (tabTransition[i][j] == null) {
						tabEtatSuivant[i][j] = etatInitial;
					} else {
						tabEtatSuivant[i][j] = tabTransition[i][j].getEtatArrivee();
					}
				}
			}
		}

	// GETTERS/SETTERS
	
	public Action_transition[][] getTabActionTransition() {
		return tabActionTransition;
	}

	public Etat[][] getTabEtatSuivant() {
		return tabEtatSuivant;
	}

	public Condition[][] getTabCondition() {
		return tabCondition;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}


	public void addEtat(Etat e) {
		e.setId(etats.size());
		etats.add(e);
	}

	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}


	public Etat getEtatSuivant(int i, int j) {
		return tabEtatSuivant[i][j];
	}

	public Etat getEtatInitial() {
		return etatInitial;
	}

	public void setEtatInitial(Etat etatInitial) {
		this.etatInitial = etatInitial;
	}

	public int getNbEtats() {
		return this.etats.size();
	}

	// Inverse SolAmi/SolEnnemi et PeindreAmi/PeindreEnnemi
	public void inverser() {
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				if (tabActionTransition[i][j] instanceof PeindreAmi) {
					tabActionTransition[i][j] = new PeindreEnnemi();
				} else if (tabActionTransition[i][j] instanceof PeindreEnnemi) {
					tabActionTransition[i][j] = new PeindreAmi();
				}
			}
		}
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				tabCondition[i][j].inverser();
			}
		}
	}
	
	// modifie l'action associée à la transition
	public void modifierTabActionTransition(int i, int j,Decor decor){
		tabActionTransition[i][j] = Association.getAction(decor);
	}
	
	// Affichage du contenu des différents tableaux
	public void afficher() {
		System.out.println("Tableau Ã©tat suivant :");
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				System.out.print(tabEtatSuivant[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	
		System.out.println("Tableau action transition :");
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				System.out.print(tabActionTransition[i][j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Tableau condition :");
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				System.out.print(tabCondition[i][j] + "\t");
			}
			System.out.println();
		}		
	}
}
