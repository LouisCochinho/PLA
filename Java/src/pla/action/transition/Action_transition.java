package pla.action.transition;

// action li�e � la transition

import pla.Jeu;
import pla.Personnage;
import pla.action.Action;
import pla.ihm.Case;
import pla.ihm.Map;

public abstract class Action_transition extends Action{

	public abstract void executer(Personnage p, Case c, Jeu j, int delta);
}
