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
public class SolNormal extends Decor {
    
    public SolNormal() {
    	super(DecorSprite.SOL_NORMAL, 64, 64, 92);
        ajouterAction(new Construire());
        ajouterAction(new PeindreAmi());
        ajouterAction(new PeindreEnnemi());
    }
    
}
