/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla;

/**
 *
 * @author antoi
 */
public class Etat {
    private final Action_etat actionEtat;
    
    public Etat(Action_etat actionEtat) {
        this.actionEtat = actionEtat;
    }
    
    public Action_etat getActionEtat() {
        return actionEtat;
    }
}
