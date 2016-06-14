/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla;

import pla.decor.Decor;

/**
 *
 * @author antoi
 */
public class ConditionSimple {
    private Decor decor;
    private final Cellule cellule;

    public ConditionSimple(Decor decor, Cellule cellule) {
        this.decor = decor;
        this.cellule = cellule;
    }

    public Decor getDecor() {
        return decor;
    }

    public Cellule getCellule() {
        return cellule;
    }
    
    @Override
    public String toString() {
        return decor + "(" + cellule + ")";
    }
    
    public boolean estVerifiee(Decor d){
    	return decor.toString().equals(d.toString());
    }

    public void setDecor(Decor decor) {
        this.decor = decor;
    }
}
