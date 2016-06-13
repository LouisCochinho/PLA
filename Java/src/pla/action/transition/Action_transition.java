package pla.action.transition;

// action li�e � la transition

import pla.Personnage;
import pla.action.Action;
import pla.ihm.Case;
import pla.ihm.Map;

public abstract class Action_transition extends Action{

	public abstract void executer(Personnage p, Case c, Map m, int delta);
}
