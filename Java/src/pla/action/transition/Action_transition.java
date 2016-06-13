package pla.action.transition;

// action li�e � la transition

import pla.Personnage;
import pla.action.Action;
import pla.ihm.Case;

public abstract class Action_transition extends Action{

	public abstract void executer(Personnage p, Case c, int delta);
}
