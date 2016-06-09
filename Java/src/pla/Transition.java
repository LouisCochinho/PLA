/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla;

import pla.action.transition.Action_transition;

/**
 *
 * @author antoi
 */
public class Transition {
    private final Etat etatDepart;
    private final Condition condition;
    private Action_transition actionTransition;
    private final Etat etatArrivee;
    
    public Transition(Etat ed, Condition c, Action_transition at, Etat ea) {
        etatDepart = ed;
        condition = c;
        actionTransition = at;
        etatArrivee = ea;
    }
    
    public void setActionTransition(Action_transition at) {
        actionTransition = at;
    }

    public Etat getEtatDepart() {
        return etatDepart;
    }

    public Condition getCondition() {
        return condition;
    }

    public Action_transition getActionTransition() {
        return actionTransition;
    }

    public Etat getEtatArrivee() {
        return etatArrivee;
    }
}
