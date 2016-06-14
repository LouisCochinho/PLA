/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.decor;

import pla.action.transition.*;


public class Gendarmerie extends Decor {
    
    public Gendarmerie() {
    	super(DecorSprite.GENDARMERIE, 64, 64);
        ajouterAction(new Demolir());
        ajouterAction(new Dupliquer());
    }
    
}
