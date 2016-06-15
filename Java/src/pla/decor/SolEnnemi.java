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
public class SolEnnemi extends Decor {
    
    public SolEnnemi() {
    	super(DecorSprite.SOL_ENNEMI, 64, 64, 0);
        ajouterAction(new PeindreAmi());
        ajouterAction(new PeindreNeutre());
    }
    
}
