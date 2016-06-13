package pla.action.etat;

// action associ� � l'�tat de l'automate

import pla.Personnage;
import pla.action.Action;

public abstract class Action_etat extends Action {

	protected abstract void executer(Personnage p, int delta);
	public abstract void executer(Personnage p, int delta,int modulo_tore_x, int modulo_tore_y);
}
