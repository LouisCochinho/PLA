/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.decor;

import pla.action.transition.*;

/**
 *
 * @author antoi
 */
public class Muret extends Decor {
    
    public Muret() {
    	super(DecorSprite.MURET, 64, 64, 15);
        ajouterAction(new Construire());
        ajouterAction(new Demolir());
    }
    
}
