package pla;

import pla.action.transition.Admirer;
import pla.action.transition.Action_transition;
import java.util.ArrayList;
import java.util.Arrays;

public class Automate {
	private Transition tabTransition[][];
	private Etat tabEtatSuivant[][]; // [Decor_id][etat_courant_id]
	private Action_transition tabActionTransition[][]; // [Decor_id][etat_courant_id]
	//private int tabActionTransition[][];
	private Condition tabCondition[][];
	private int nbLignes = 0;
	private int nbColonnes = 0;
	private int posX; // position en abcisse sur la grille
	private int posY; // position en ordonn�es sur la grille
	private ArrayList<Etat> etats;
	private ArrayList<Transition> transitions;
	private Transition transitionParDefaut;
	private Action_transition actionParDefaut;
	private Etat etatInitial;
	private Condition conditionParDefaut;
	private Etat etatCourant;

	// automate par d�faut
	public Automate() {
		this(5, 6);
	}

	public Automate(int posX, int posY) {
		this("../OCaml/test.xml", posX, posY);
	}

	public Automate(String fileName) {
		this(fileName, 5, 6);
	}

	// automate avec parsing
	public Automate(String fileName, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;

		etats = new ArrayList<Etat>();
		transitions = new ArrayList<Transition>();

		XMLParser.parse(this, fileName);

		nbLignes = getNbTransitionsMax();
		nbColonnes = etats.size();

		actionParDefaut = new Admirer();
		etatInitial = etats.get(0);
                etatCourant = etatInitial;
		conditionParDefaut = new Condition();
		transitionParDefaut = new Transition(etatInitial, conditionParDefaut, actionParDefaut, etatInitial);

		initTabTransition();
		initTabActionTransition();
		initTabEtatSuivant();
		initTabCondition();
	}

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
			max = Integer.max(max, tab[i]);
		}
		return max;
	}

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
		
		//tabActionTransition = new int[4][4];
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
		/*tabActionTransition[0][0] = 0;
		tabActionTransition[0][1] = 1;
		tabActionTransition[0][2] = 3;
		tabActionTransition[0][3] = 1;
		tabActionTransition[1][0] = 1;
		tabActionTransition[1][1] = 0;
		tabActionTransition[1][2] = 0;
		tabActionTransition[1][3] = 0;
		tabActionTransition[2][0] = 0;
		tabActionTransition[2][1] = 0;
		tabActionTransition[2][2] = 0;
		tabActionTransition[2][3] = 1;
		tabActionTransition[3][0] = 4;
		tabActionTransition[3][1] = 4;
		tabActionTransition[3][2] = 4;
		tabActionTransition[3][3] = 0;
		*/
	}

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

	public Action_transition[][] getTabActionTransition() {
		return tabActionTransition;
	}
	/*public int[][] getTabActionTransition(){
		return tabActionTransition;
	}*/

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
		// System.out.println(Arrays.deepToString(tabEtatSuivant));
	}

	public void addEtat(Etat e) {
		e.setId(etats.size());
		etats.add(e);
	}

	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}

	public void afficher() {
		System.out.println("Tableau état suivant :");
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

	public Etat getEtatCourant() {
		return etatCourant;
	}

	public void setEtatCourant(Etat etatCourant) {
		this.etatCourant = etatCourant;
	}
	
	public Etat getEtatSuivant(int i, int j){
		return tabEtatSuivant[i][j];
	}
}
