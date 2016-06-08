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
    private int id;
    private final Action_etat actionEtat;
    
    public Etat(Action_etat actionEtat) {
        this.actionEtat = actionEtat;
    }
    
    public Etat(int id, Action_etat actionEtat) {
        this.id = id;
        this.actionEtat = actionEtat;
    }
    
    public Action_etat getActionEtat() {
        return actionEtat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Etat " + id;
    }
}
