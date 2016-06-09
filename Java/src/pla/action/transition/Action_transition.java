package pla.action.transition;

// action li�e � la transition

import pla.Personnage;
import pla.action.Action;

public abstract class Action_transition extends Action{

	public abstract void executer(Personnage p);
}
