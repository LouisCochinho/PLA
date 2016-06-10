package pla.action.etat;

// action associ� � l'�tat de l'automate

import pla.Personnage;
import pla.action.Action;

public abstract class Action_etat extends Action {

	public final static int MODULO_TORE_X = 640;
	public final static int MODULO_TORE_Y = 512;
	public abstract void executer(Personnage p, int delta);
}
