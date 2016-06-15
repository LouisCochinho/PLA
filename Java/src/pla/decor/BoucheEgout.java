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
public class BoucheEgout extends Decor {
    
    public BoucheEgout() {
    	super(DecorSprite.BOUCHE_EGOUT, 64, 64, 1);
        ajouterAction(new Voyager());
    }
    
}
